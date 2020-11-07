package com.margretcraft.weatherforecaster;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private TextView textViewData;
    private TextView textViewMinMax;
    private TextView textViewState;
    private TextView textViewTemperature;
    private TextView textViewTown;
    private TextView textViewWind;
    private Button buttonleft;
    private Button buttonright;
    private ImageButton imageButtonTown;
    private ImageButton imageButtonConf;
    private ApplicationClass consts;
    private String[] listTownPoint;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        consts = ApplicationClass.getInstance();

        Log.i(consts.getLOGTAG(), "onCreate");
        Toast.makeText(this, R.string.onCreate, Toast.LENGTH_LONG).show();

        textViewData = findViewById(R.id.textViewData);
        textViewMinMax = findViewById(R.id.textViewMinMax);
        textViewState = findViewById(R.id.textViewState);
        textViewTemperature = findViewById(R.id.textViewTemperature);
        textViewTown = findViewById(R.id.TextViewTown);
        textViewTown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeTown();
            }
        });
        textViewWind = findViewById(R.id.textViewWind);
        buttonleft = findViewById(R.id.button);
        buttonright = findViewById(R.id.button2);
        imageButtonTown = findViewById(R.id.imageViewTown);
        imageButtonTown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goWebSite();
            }
        });
        imageButtonConf = findViewById(R.id.buttonCast);
        imageButtonConf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeConf();
            }
        });

        setValues();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(consts.getLOGTAG(), "onStart");
        Toast.makeText(this, R.string.onStart, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(consts.getLOGTAG(), "onStop");
        Toast.makeText(this, R.string.onStop, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(consts.getLOGTAG(), "onDestroy");
        Toast.makeText(this, R.string.onDestroy, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(consts.getLOGTAG(), "onPause");
        Toast.makeText(this, R.string.onPause, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(consts.getLOGTAG(), "onResume");
        Toast.makeText(this, R.string.onResume, Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(consts.getLOGTAG(), "onRestart");
        Toast.makeText(this, R.string.onRestart, Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onRestoreInstanceState(Bundle saveInstanceState) {
        super.onRestoreInstanceState(saveInstanceState);
        Log.i(consts.getLOGTAG(), "onRestoreInstanceState");
        Toast.makeText(this, R.string.onRestoreInstanceState, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onSaveInstanceState(Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);
        Log.i(consts.getLOGTAG(), "onSaveInstanceState");
        Toast.makeText(this, R.string.onSaveInstanceState, Toast.LENGTH_LONG).show();
    }


    private void setValues() {

        int temperature = 18;
        int windSpeedmin = 5;
        int windSpeedmax = 7;
        Date showDay = new Date();
        Date showDayPlus = new Date(showDay.getTime() + 24 * 60 * 60 * 1000);
        Date showDayMinus = new Date(showDay.getTime() - 24 * 60 * 60 * 1000);

        SimpleDateFormat sdf = new SimpleDateFormat("d MMM yyyy");
        SimpleDateFormat sdfOneDay = new SimpleDateFormat("d");

        textViewTown.setText(consts.getTown().getName().replaceFirst(" ", "\n"));
        textViewData.setText(sdf.format(showDay));
        textViewTemperature.setText("" + temperature + "°С");
        StringBuilder sb = new StringBuilder();
        if (consts.isTempmes()) {
            sb.append("%d").append(getResources().getString(R.string.tempmes1)).append("-%d").append(getResources().getString(R.string.tempmes1));
            textViewMinMax.setText(String.format(sb.toString(), (temperature - 3), (temperature + 5)));
        } else {
            sb.append("%d").append(getResources().getString(R.string.tempmes2)).append("-%d").append(getResources().getString(R.string.tempmes2));
            textViewMinMax.setText(String.format(sb.toString(), (temperature - 3) + getResources().getInteger(R.integer.transferT), (temperature + 5) + getResources().getInteger(R.integer.transferT)));
        }
        textViewState.setText(R.string.state);
        sb.delete(0, sb.length());
        if (consts.isWindmes()) {
            textViewWind.setText(String.format(sb.append("%d-%d ").append(getResources().getString(R.string.windmes1)).toString(), windSpeedmin, windSpeedmax));
        } else {
            textViewWind.setText(String.format(sb.append("%.1f-%.1f\n").append(getResources().getString(R.string.windmes2)).toString(), windSpeedmin * 0.10 * getResources().getInteger(R.integer.transferW), windSpeedmax * 0.10 * getResources().getInteger(R.integer.transferW)));
        }
        sb.delete(0, sb.length());
        buttonleft.setText(sdfOneDay.format(showDayMinus));
        buttonright.setText(sdfOneDay.format(showDayPlus));

    }

    public void changeTown() {
        Intent intent = new Intent(MainActivity.this, TownActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
           setValues();
    }

    public void changeConf() {
        Intent intent = new Intent(MainActivity.this, CastActivity.class);
        startActivityForResult(intent, 2);
    }

    private void goWebSite() {
        ApplicationClass.getInstance().getTown();
        StringBuilder sb = new StringBuilder();
        sb.append(getString(R.string.map_upl)).append(ApplicationClass.getInstance().getTown().getPoint()).append(getString(R.string.map_size)).append(getString(R.string.map_pres));
        Uri uri = Uri.parse(sb.toString());

        Intent browser = new Intent(Intent.ACTION_VIEW, uri);
        Intent shooser = Intent.createChooser(browser, getString(R.string.Choose_app));
        if (browser.resolveActivity(getPackageManager()) != null) {
            startActivity(shooser);
        }

    }

}