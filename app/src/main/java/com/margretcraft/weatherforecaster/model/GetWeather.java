package com.margretcraft.weatherforecaster.model;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.margretcraft.weatherforecaster.BuildConfig;
import com.margretcraft.weatherforecaster.R;
import com.margretcraft.weatherforecaster.activity.MainActivity;
import com.margretcraft.weatherforecaster.model.jsonmodel.ListRequest;
import com.margretcraft.weatherforecaster.model.jsonmodel.WeatherRequest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

public class GetWeather extends Observable implements Runnable {
    private static final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather?";
    private static final String WEATHER_5_URL = "https://api.openweathermap.org/data/2.5/onecall?";

    private static String TOWN_COORDINATES;
    private Observer observer;

    public void setObserver(Observer observer) {
        this.observer = observer;
    }

    private String lat;
    private String lon;

    public GetWeather(String lat, String lon) {
        this.lat = lat;
        this.lon = lon;
        this.TOWN_COORDINATES = new StringBuilder().append("lat=").append(lat).append("&lon=").append(lon).toString();
    }

    public void run() {
        StringBuilder sb = new StringBuilder(WEATHER_URL);

        sb.append(TOWN_COORDINATES).append("&lang=").append(Locale.getDefault().getLanguage()).append("&appid=").append(BuildConfig.WEATHER_API_KEY);
        HttpsURLConnection urlConnection = null;
        try {

            URL uri = new URL(sb.toString());
            urlConnection = (HttpsURLConnection) uri.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000);
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String result = getLines(in);

            Gson gson = new Gson();
            final WeatherRequest weatherRequest = gson.fromJson(result, WeatherRequest.class);
            in.close();
            observer.update(this, weatherRequest);

        } catch (Exception e) {
            Snackbar.make(((MainActivity) observer).findViewById(R.id.buttonTiming), "Недоступны данные с сервера, попробуйте позднее.", BaseTransientBottomBar.LENGTH_LONG).show();
            e.printStackTrace();
        } finally {
            if (null != urlConnection) {
                urlConnection.disconnect();
            }
        }


        sb = new StringBuilder(WEATHER_5_URL);

        sb.append(TOWN_COORDINATES).append("&exclude=").append("minutely,hourly").append("&appid=").append(BuildConfig.WEATHER_API_KEY);
        urlConnection = null;
        try {

            URL uri = new URL(sb.toString());
            urlConnection = (HttpsURLConnection) uri.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000);
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String result = getLines(in);

            Gson gson = new Gson();
            final ListRequest listRequest = gson.fromJson(result, ListRequest.class);
            in.close();
            observer.update(this, listRequest);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != urlConnection) {
                urlConnection.disconnect();
            }
        }
    }

    private String getLines(BufferedReader in) {
        return in.lines().collect(Collectors.joining("\n"));
    }

}
