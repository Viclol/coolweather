package com.coolweather.android.gson;

/**
 * Created by li on 2019/3/2 0002.
 */

public class AQI {
    public AQICity city;

    public class AQICity{
        public String aqi;
        public String pm25;
    }
}
