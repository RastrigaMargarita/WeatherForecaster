package com.margretcraft.weatherforecaster;

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
        final ApplicationClass consts = ApplicationClass.getInstance();

        if (consts.isWindmes()) {
            radioButtonMS.setChecked(true);
        } else {
            radioButtonKH.setChecked(true);
        }

        if (consts.isTempmes()) {
            radioButtonC.setChecked(true);
        } else {
            radioButtonK.setChecked(true);
        }

        radioButtonMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consts.setWindmes(true);
            }
        });
        radioButtonKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consts.setWindmes(false);
            }
        });
        radioButtonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consts.setTempmes(true);
            }
        });
        radioButtonK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consts.setTempmes(false);
            }
        });

        buttonback = findViewById(R.id.buttonBack);
        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}