package com.coolweather.android.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by li on 2019/3/4 0004.
 */

public class CityInfo {
    @SerializedName("city")
    public String cityName;

    @SerializedName("cityid")
    public String weatherId;

    @SerializedName("parent")
    public String parent;

    @SerializedName("updatetime")
    public String updateTime;
}
