package com.margretcraft.weatherforecaster;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainFragment extends Fragment {

    private TextView textViewData;
    private TextView textViewMinMax;
    private TextView textViewState;
    private TextView textViewTemperature;
    private TextView textViewTown;
    private TextView textViewWind;
    private ImageButton imageButtonTown;
    private ImageButton imageButtonConf;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        textViewData = rootView.findViewById(R.id.textViewData);
        textViewMinMax = rootView.findViewById(R.id.textViewMinMax);
        textViewState = rootView.findViewById(R.id.textViewState);
        textViewTemperature = rootView.findViewById(R.id.textViewTemperature);
        textViewTown = rootView.findViewById(R.id.TextViewTown);
        textViewTown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).changeTown();
            }
        });
        textViewWind = rootView.findViewById(R.id.textViewWind);

        imageButtonTown = rootView.findViewById(R.id.imageViewTown);
        imageButtonTown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).goWebSite();
            }
        });
        imageButtonConf = rootView.findViewById(R.id.buttonCast);
        imageButtonConf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).changeConf();
            }
        });

        setValues();

        return rootView;
    }


    private void setValues() {

        //Hardcode временный. До API
        int temperature = 18;
        int windSpeedmin = 5;
        int windSpeedmax = 7;

        Date showDay = new Date();
        Date showDayPlus = new Date(showDay.getTime() + 24 * 60 * 60 * 1000);
        Date showDayMinus = new Date(showDay.getTime() - 24 * 60 * 60 * 1000);

        SimpleDateFormat sdf = new SimpleDateFormat("d MMM yyyy");
        SimpleDateFormat sdfOneDay = new SimpleDateFormat("d");

        textViewTown.setText(((MainActivity) getActivity()).getCurrentTown().getName().replaceFirst(" ", "\n"));
        textViewData.setText(sdf.format(showDay));

        StringBuilder sb = new StringBuilder();
        if (((MainActivity) getActivity()).isTempmes()) {
            sb.append("%d").append(getString(R.string.tempmes1)).append("-%d").append(getString(R.string.tempmes1));
            textViewMinMax.setText(String.format(sb.toString(), (temperature - 3), (temperature + 5)));
            textViewTemperature.setText("" + temperature + getString(R.string.tempmes1));
        } else {
            sb.append("%d").append(getString(R.string.tempmes2)).append("-%d").append(getString(R.string.tempmes2));
            textViewMinMax.setText(String.format(sb.toString(), (temperature - 3) + getResources().getInteger(R.integer.transferT), (temperature + 5) + getResources().getInteger(R.integer.transferT)));
            textViewTemperature.setText("" + temperature + getString(R.string.tempmes2));
        }
        textViewState.setText(R.string.state);
        sb.delete(0, sb.length());
        if (((MainActivity) getActivity()).isWindmes()) {
            textViewWind.setText(String.format(sb.append("%d-%d ").append(getString(R.string.windmes1)).toString(), windSpeedmin, windSpeedmax));
        } else {
            textViewWind.setText(String.format(sb.append("%.1f-%.1f\n").append(getString(R.string.windmes2)).toString(), windSpeedmin * 0.10 * getResources().getInteger(R.integer.transferW), windSpeedmax * 0.10 * getResources().getInteger(R.integer.transferW)));
        }
        sb.delete(0, sb.length());

    }

}
