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

    public class Forecast1{
        @SerializedName("date")
        public String date;
        @SerializedName("ymd")
        public String ymd;
        @SerializedName("week")
        public String week;
        @SerializedName("sunrise")
        public String sunRise;
        @SerializedName("high")
        public String max;
        @SerializedName("low")
        public String min;
        @SerializedName("sunset")
        public String sunSet;
        @SerializedName("aqi")
        public String aqi;
        @SerializedName("fx")
        public String fx;
        @SerializedName("fl")
        public String fl;
        @SerializedName("type")
        public String type;
        @SerializedName("notice")
        public String notice;
    }

}
