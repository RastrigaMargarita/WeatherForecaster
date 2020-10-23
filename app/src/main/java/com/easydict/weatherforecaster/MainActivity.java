package com.easydict.weatherforecaster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    TextView textViewData;
    TextView textViewMinMax;
    TextView textViewState;
    TextView textViewTemperature;
    TextView TextViewTown;
    TextView textViewWind;
    Button buttonleft;
    Button buttonright;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewData = findViewById(R.id.textViewData);
        textViewMinMax = findViewById(R.id.textViewMinMax);
        textViewState = findViewById(R.id.textViewState);
        textViewTemperature = findViewById(R.id.textViewTemperature);
        TextViewTown = findViewById(R.id.TextViewTown);
        textViewWind = findViewById(R.id.textViewWind);
        buttonleft = findViewById(R.id.button);
        buttonright = findViewById(R.id.button2);;

        setValues();
    }

    private void setValues() {

        int temperature = 18;
        int windSpeedmin = 5;
        int windSpeedmax = 7;
        Date showDay = new Date();
        Date showDayPlus = new Date(showDay.getTime() +24*60*60*1000);
        Date showDayMinus = new Date(showDay.getTime() -24*60*60*1000);

        SimpleDateFormat sdf = new SimpleDateFormat("d MMM yyyy");
        SimpleDateFormat sdfOneDay = new SimpleDateFormat("d");

        TextViewTown.setText(R.string.town);
        textViewData.setText(sdf.format(showDay));
        textViewTemperature.setText("" + temperature + "°С");
        textViewMinMax.setText("" + (temperature - 3) + "°С-" + (temperature + 5) + "°С");
        textViewState.setText(R.string.state);
        textViewWind.setText("" + windSpeedmin + "-" + windSpeedmax + " м/с");
        buttonleft.setText(sdfOneDay.format(showDayMinus));
        buttonright.setText(sdfOneDay.format(showDayPlus));

    }
}