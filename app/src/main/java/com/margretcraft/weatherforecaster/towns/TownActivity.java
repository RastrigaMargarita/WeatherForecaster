package com.margretcraft.weatherforecaster.towns;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.margretcraft.weatherforecaster.R;
import com.margretcraft.weatherforecaster.TownClass;

import java.util.ArrayList;

public class TownActivity extends AppCompatActivity {
    private TownAdapter arrayAdapter;
    private String[] listTown;
    private String[] listTownPoint;
    private String[] listTimeZone;
    private ArrayList<TownClass> listTownClass;
    private RecyclerView listViewTown;
    private TextView textViewTown;
    private ImageButton buttonSearch;
    private Button buttonback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_town);
        listViewTown = findViewById(R.id.ListViewTown);
        ititTownClassList();
        listViewTown.setHasFixedSize(true);
        listViewTown.setLayoutManager(new LinearLayoutManager(this));
        arrayAdapter = new TownAdapter(listTownClass);

        arrayAdapter.SetOnItemClickListener(new TownAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent answer = new Intent();
                answer.putExtra("town", listTownClass.get(position));
                setResult(RESULT_OK, answer);
                finish();
            }
        });
        listViewTown.setAdapter(arrayAdapter);
        textViewTown = findViewById(R.id.textViewTown);
        buttonSearch = findViewById(R.id.buttonSearchTown);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchingTown = textViewTown.getText().toString().trim().toLowerCase();
                boolean findtown = false;
                for (int i = 0; i < listTown.length; i++) {

                    if (listTown[i].toLowerCase().contains(searchingTown)) {
                        findtown = true;
                        listViewTown.smoothScrollToPosition(i);
                        break;
                    }
                }
                if (!findtown) {
                    showToast();
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

    private void ititTownClassList() {
        listTownClass = new ArrayList<>();
        listTown = getResources().getStringArray(R.array.towns);
        listTownPoint = getResources().getStringArray(R.array.points);
        listTimeZone = getResources().getStringArray(R.array.zones);
        for (int i = 0; i < listTown.length; i++) {
            listTownClass.add(new TownClass(listTown[i], listTownPoint[i], listTimeZone[i]));
        }
    }

    private void showToast() {
        Toast.makeText(this, R.string.notFound, Toast.LENGTH_LONG).show();
    }
}