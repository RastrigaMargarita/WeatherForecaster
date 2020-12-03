package com.margretcraft.weatherforecaster.model;

import com.google.gson.Gson;
import com.margretcraft.weatherforecaster.BuildConfig;
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

    private final String TOWN_COORDINATES;
    private final Observer observer;

    private final String lat;
    private final String lon;

    public GetWeather(Observer observer, String lat, String lon) {

        this.observer = observer;
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
            weatherRequest.setGettingSuccess(true);
            in.close();

            ((MainActivity) observer).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    setDataToObserver(weatherRequest);
                }
            });


        } catch (Exception e) {

            WeatherRequest weatherRequest = new WeatherRequest();
            weatherRequest.setGettingSuccess(false);
            observer.update(this, weatherRequest);
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

            ((MainActivity) observer).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    setDataToObserver(listRequest);
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != urlConnection) {
                urlConnection.disconnect();
            }
        }
    }

    private void setDataToObserver(Object rr) {
        observer.update(this, rr);
    }

    private String getLines(BufferedReader in) {
        return in.lines().collect(Collectors.joining("\n"));
    }

}
