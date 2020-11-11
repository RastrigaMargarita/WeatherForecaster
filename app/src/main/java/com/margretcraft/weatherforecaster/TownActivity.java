package com.margretcraft.weatherforecaster;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TownActivity extends AppCompatActivity {
    private ArrayAdapter arrayAdapter;
    private String[] listTown;
    private String[] listTownPoint;
    private ListView listViewTown;
    private TextView textViewTown;
    private ImageButton buttonSearch;
    private Button buttonback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_town);
        listViewTown = findViewById(R.id.ListViewTown);
        listTown = getResources().getStringArray(R.array.towns);
        listTownPoint = getResources().getStringArray(R.array.points);

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listTown);

        listViewTown.setAdapter(arrayAdapter);
        listViewTown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent answer = new Intent();
                answer.putExtra("town", new TownClass(listTown[position], listTownPoint[position]));
                setResult(RESULT_OK, answer);
                finish();
            }
        });
        textViewTown = findViewById(R.id.textViewTown);
        buttonSearch = findViewById(R.id.buttonSearchTown);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchingTown = textViewTown.getText().toString().trim().toLowerCase();
                listViewTown.setSelection(0);
                for (int i = 0; i < listTown.length; i++) {
                    boolean findtown = false;
                    if (listTown[i].toLowerCase().contains(searchingTown)) {
                        findtown = true;
                        listViewTown.smoothScrollToPosition(i, 0);
                        listViewTown.setSelection(i);
                        return;
                    }
                    if (!findtown) {
                        showToast();
                    }
                }
            }
        });

        buttonback = findViewById(R.id.buttonBack2);
        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void showToast() {
        Toast.makeText(this, R.string.notFound, Toast.LENGTH_LONG).show();
    }
}