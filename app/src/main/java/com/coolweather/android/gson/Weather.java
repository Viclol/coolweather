package com.coolweather.android.gson;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by li on 2019/3/2 0002.
 */

public class Weather {
//    public String status;
//    public Basic basic;
//    public AQI aqi;
//    public Now now;
//    public Suggestion suggestion;
//    @SerializedName("daily_forecase")
//    public List<Forecast> forecastList;

    public String time;
    public CityInfo cityInfo;
    public String date;
    public String message;
    public int status;
    public Data data;
    @SerializedName("data.forecast")
    public List<Forecast1> forecast1List;


}
