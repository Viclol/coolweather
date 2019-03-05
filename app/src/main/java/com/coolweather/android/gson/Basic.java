package com.coolweather.android.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by li on 2019/3/2 0002.
 */

public class Basic {
    @SerializedName("city")
    public String cityName;

    @SerializedName("id")
    public String weatherId;
    public Update updatel;

    public class Update{
        @SerializedName("loc")
        public String updateTime;
    }
}
