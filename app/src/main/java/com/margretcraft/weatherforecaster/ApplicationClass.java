package com.margretcraft.weatherforecaster;

import android.app.Application;

public class ApplicationClass extends Application {


    private static ApplicationClass INSTANCE;

    private String town;
    private boolean windmes;
    private boolean tempmes;
    private final String LOGTAG = "ActivityState";

    public static ApplicationClass getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        town = getResources().getString(R.string.town);
        windmes = true;
        tempmes = true;
    }

    public String getLOGTAG() {
        return LOGTAG;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public boolean isWindmes() {
        return windmes;
    }

    public void setWindmes(boolean windmes) {
        this.windmes = windmes;
    }

    public boolean isTempmes() {
        return tempmes;
    }

    public void setTempmes(boolean tempmes) {
        this.tempmes = tempmes;
    }


}
