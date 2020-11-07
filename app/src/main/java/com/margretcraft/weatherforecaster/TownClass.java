package com.margretcraft.weatherforecaster;

public class TownClass {
    private String name;
    private String point;

    public TownClass(String name, String point) {
        this.name = name;
        this.point = point;
    }

    public String getName() {
        return name;
    }

    public String getPoint() {
        return point;
    }
}
