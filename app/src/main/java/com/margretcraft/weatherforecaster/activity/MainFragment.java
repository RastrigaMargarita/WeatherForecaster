package com.margretcraft.weatherforecaster.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.margretcraft.weatherforecaster.R;
import com.margretcraft.weatherforecaster.model.jsonmodel.WeatherRequest;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainFragment extends Fragment {

    private TextView textViewData;
    private TextView textViewMinMax;
    private TextView textViewState;
    private TextView textViewTemperature;
    private TextView textViewTown;
    private TextView textViewWind;
    private ImageView imageViewWind;
    private Button imageButtonTown;
    private Button imageButtonConf;

    @Nullable
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
        imageViewWind = rootView.findViewById(R.id.imageViewWind);
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

    public void setValues() {
        WeatherRequest wr = ((MainActivity) getActivity()).getWeatherRequest();
        if (wr != null) {
            Date showDay = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("d MMM yyyy");

            textViewTown.setText(((MainActivity) getActivity()).getCurrentTown().getName().replaceFirst(" ", "\n"));
            textViewData.setText(sdf.format(showDay));

            StringBuilder sb = new StringBuilder();
            if (((MainActivity) getActivity()).isTempmes()) {
                sb.append("%d").append(getString(R.string.tempmes1)).append("...%d").append(getString(R.string.tempmes1));
                textViewMinMax.setText(String.format(sb.toString(), (int) ((wr.getMain().getTemp_min()) - getResources().getInteger(R.integer.transferT)), (int) ((wr.getMain().getTemp_max()) - getResources().getInteger(R.integer.transferT))));
                textViewTemperature.setText("" + (int) (wr.getMain().getTemp() - getResources().getInteger(R.integer.transferT)) + getString(R.string.tempmes1));
            } else {
                sb.append("%f").append(getString(R.string.tempmes2)).append("...%f").append(getString(R.string.tempmes2));
                textViewMinMax.setText(String.format(sb.toString(), (wr.getMain().getTemp_min()), (wr.getMain().getTemp_max())));
                textViewTemperature.setText("" + wr.getMain().getTemp() + getString(R.string.tempmes2));
            }

            sb.delete(0, sb.length());
            if (((MainActivity) getActivity()).isWindmes()) {
                textViewWind.setText(String.format(sb.append("%.1f ").append(getString(R.string.windmes1)).toString(), wr.getWind().getSpeed()));
            } else {
                textViewWind.setText(String.format(sb.append("%.1f\n").append(getString(R.string.windmes2)).toString(), wr.getWind().getSpeed() * 0.10 * getResources().getInteger(R.integer.transferW)));
            }
            sb.delete(0, sb.length());

            imageViewWind.setImageResource(getResources().getIdentifier("wd" + (Math.round(wr.getWind().getDeg() * 1.0 / 45) + 1), "drawable", getActivity().getPackageName()));
            textViewState.setText(wr.getWeather()[0].getMain() + "\n" + wr.getWeather()[0].getDescription());

        }
    }


}
