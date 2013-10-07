package com.newboston.tutorial;

/**
 * Created by Chris on 01.10.13.
 */
public class XMLDataCollected {
    double temp = 0.0;
    String city = null;

    public void setCity(String c) {
        city = c;
    }

    public void setTemp(double t) {
        temp = t;
    }

    public String dataToString() {
        return "In " + city + " the Current Temperature is " + temp + " degrees";
    }
}
