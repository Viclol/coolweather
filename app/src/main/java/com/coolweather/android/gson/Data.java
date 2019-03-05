package com.coolweather.android.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by li on 2019/3/4 0004.
 */

public class Data {
    @SerializedName("shidu")
    public String shidu;
    @SerializedName("pm25")
    public String pm25;
    @SerializedName("pm10")
    public String pm10;
    @SerializedName("quality")
    public String quality;
    @SerializedName("wendu")
    public String wenDu;
    @SerializedName("ganmao")
    public String ganMao;

//    @SerializedName("forecast")
//    public More more;

//    public class More{
//        @SerializedName("date")
//        public String date;
//        @SerializedName("ymd")
//        public String ymd;
//        @SerializedName("week")
//        public String week;
//        @SerializedName("sunrise")
//        public String sunRise;
//        @SerializedName("high")
//        public String max;
//        @SerializedName("low")
//        public String min;
//        @SerializedName("sunset")
//        public String sunSet;
//        @SerializedName("aqi")
//        public String aqi;
//        @SerializedName("fx")
//        public String fx;
//        @SerializedName("fl")
//        public String fl;
//        @SerializedName("type")
//        public String type;
//        @SerializedName("notice")
//        public String notice;
//    }
}
