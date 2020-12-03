package com.margretcraft.weatherforecaster.model.jsonmodel;


import androidx.lifecycle.ViewModel;

public class ListRequest {
    private Daily[] daily;

    public Daily[] getDaily() {
        return daily;
    }

    public void setDaily(Daily[] daily) {
        this.daily = daily;
    }

}
