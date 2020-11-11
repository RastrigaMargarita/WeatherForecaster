package com.margretcraft.weatherforecaster;

import android.app.Application;

public class ApplicationClass extends Application {


    private static ApplicationClass INSTANCE;

    public static ApplicationClass getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;

    }


}
