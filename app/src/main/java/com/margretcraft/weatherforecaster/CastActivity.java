package com.margretcraft.weatherforecaster;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

public class CastActivity extends AppCompatActivity {

    private RadioButton radioButtonMS;
    private RadioButton radioButtonKH;
    private RadioButton radioButtonC;
    private RadioButton radioButtonK;
    private Button buttonback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cast);

        radioButtonMS = findViewById(R.id.radioButtonMS);
        radioButtonKH = findViewById(R.id.radioButtonKH);
        radioButtonC = findViewById(R.id.radioButtonC);
        radioButtonK = findViewById(R.id.radioButtonK);

        if (savedInstanceState != null) {
            if (savedInstanceState.getBoolean("windmes")) {
                radioButtonMS.setChecked(true);
            } else {
                radioButtonKH.setChecked(true);
            }

            if (savedInstanceState.getBoolean("tempmes")) {
                radioButtonC.setChecked(true);
            } else {
                radioButtonK.setChecked(true);
            }
        } else {

            Intent intent = getIntent();
            radioButtonMS.setChecked(intent.getBooleanExtra("windmes", true));
            radioButtonKH.setChecked(!intent.getBooleanExtra("windmes", false));
            radioButtonC.setChecked(intent.getBooleanExtra("tempmes", true));
            radioButtonK.setChecked(!intent.getBooleanExtra("tempmes", false));
        }

        buttonback = findViewById(R.id.buttonBack);
        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent answer = new Intent();
                answer.putExtra("windmes", radioButtonMS.isChecked());
                answer.putExtra("tempmes", radioButtonC.isChecked());
                setResult(RESULT_OK, answer);

                finish();
            }
        });
    }
}