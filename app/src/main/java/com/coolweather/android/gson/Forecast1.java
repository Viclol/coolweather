package com.coolweather.android.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by li on 2019/3/4 0004.
 */

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
