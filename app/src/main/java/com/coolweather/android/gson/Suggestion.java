package com.coolweather.android.gson;

import android.widget.Space;

import com.google.gson.annotations.SerializedName;

import junit.framework.ComparisonFailure;

/**
 * Created by li on 2019/3/2 0002.
 */

public class Suggestion {
    @SerializedName("comf")
    public Comfort comfort;

    @SerializedName("cw")
    public CarWash carWash;

    public Sport sport;

    public class Comfort{
        @SerializedName("txt")
        public String info;
    }
    public  class CarWash{
        @SerializedName("txt")
        public  String info;
    }
    public class Sport{
        @SerializedName("txt")
        public String info;
    }
}

