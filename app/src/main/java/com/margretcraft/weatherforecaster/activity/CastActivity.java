package com.margretcraft.weatherforecaster.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.margretcraft.weatherforecaster.ApplicationClass;
import com.margretcraft.weatherforecaster.R;
import com.margretcraft.weatherforecaster.model.ActivityObserver;

public class CastActivity extends AppCompatActivity {
    private String AppName = ApplicationClass.getInstance().getPackageName();
    private RadioButton radioButtonMS;
    private RadioButton radioButtonKH;
    private RadioButton radioButtonC;
    private RadioButton radioButtonK;
    private RadioButton radioButtonV;
    private RadioButton radioButtonG;
    private Button buttonback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPref = getSharedPreferences(AppName, MODE_PRIVATE);
        if (sharedPref.getBoolean("theme", true)) {
            setTheme(R.style.AppTheme);
        } else {
            setTheme(R.style.AppThemeGreen);
        }

        setContentView(R.layout.activity_cast);

        getLifecycle().addObserver(new ActivityObserver());

        radioButtonMS = findViewById(R.id.radioButtonMS);
        radioButtonKH = findViewById(R.id.radioButtonKH);
        radioButtonC = findViewById(R.id.radioButtonC);
        radioButtonK = findViewById(R.id.radioButtonK);
        radioButtonV = findViewById(R.id.radioButtonV);
        radioButtonG = findViewById(R.id.radioButtonG);

        radioButtonMS.setChecked(sharedPref.getBoolean("windmes", true));
        radioButtonKH.setChecked(!sharedPref.getBoolean("windmes", true));
        radioButtonC.setChecked(sharedPref.getBoolean("tempmes", true));
        radioButtonK.setChecked(!sharedPref.getBoolean("tempmes", true));
        radioButtonV.setChecked(sharedPref.getBoolean("theme", true));
        radioButtonG.setChecked(!sharedPref.getBoolean("theme", true));

        buttonback = findViewById(R.id.buttonBack);
        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        radioButtonV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioButtonV.isChecked()) {
                    SharedPreferences sharedPref = getSharedPreferences(AppName, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putBoolean("theme", true);
                    editor.apply();
                }
                recreate();
            }
        });
        radioButtonG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioButtonG.isChecked()) {
                    SharedPreferences sharedPref = getSharedPreferences(AppName, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putBoolean("theme", false);
                    editor.apply();
                }
                recreate();
            }
        });
        radioButtonMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioButtonMS.isChecked()) {
                    SharedPreferences sharedPref = getSharedPreferences(AppName, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putBoolean("windmes", true);
                    editor.apply();
                }
            }
        });
        radioButtonKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioButtonKH.isChecked()) {
                    SharedPreferences sharedPref = getSharedPreferences(AppName, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putBoolean("windmes", false);
                    editor.apply();
                }
            }
        });
        radioButtonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioButtonC.isChecked()) {
                    SharedPreferences sharedPref = getSharedPreferences(AppName, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putBoolean("tempmes", true);
                    editor.apply();
                }
            }
        });
        radioButtonK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioButtonK.isChecked()) {
                    SharedPreferences sharedPref = getSharedPreferences(AppName, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putBoolean("tempmes", false);
                    editor.apply();
                }
            }
        });

    }
}