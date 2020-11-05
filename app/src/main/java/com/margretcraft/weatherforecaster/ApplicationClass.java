package com.margretcraft.weatherforecaster;

import android.app.Application;

public class ApplicationClass extends Application {


    private static ApplicationClass INSTANCE;

    private TownClass town;
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
        town = new TownClass(getResources().getString(R.string.Moscow), getResources().getString(R.string.MoscowPoint));
        windmes = true;
        tempmes = true;
    }

    public String getLOGTAG() {
        return LOGTAG;
    }

    public TownClass getTown() {
        return town;
    }

    public void setTown(TownClass town) {
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
