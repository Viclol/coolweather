package com.coolweather.android.util;

import android.text.TextUtils;
import android.util.Log;

import com.coolweather.android.db.City;
import com.coolweather.android.db.County;
import com.coolweather.android.db.Province;
import com.coolweather.android.gson.Forecast1;
import com.coolweather.android.gson.Weather;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by li on 2019/3/1 0001.
 */

public class Utility {
    /*
    * 解析和处理服务器返回的省级数据
    * */
    public  static boolean handleProvinceResponse(String response){
        if (!TextUtils.isEmpty(response)){
            try{
                JSONArray allProvinces = new JSONArray(response);
                for (int i=0 ;i<allProvinces.length(); i++){
                    JSONObject provinceObject = allProvinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }
    /*
    * 解析和处理服务器返回的市级数据
    * */
    public  static boolean handleCityResponse(String response, int provinceId){
        if (!TextUtils.isEmpty(response)){
            try{
                JSONArray allCities = new JSONArray(response);
                for (int i=0 ;i<allCities.length(); i++){
                    JSONObject cityObject = allCities.getJSONObject(i);
                    City city = new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }
    /*
    * 解析和处理服务器返回的县级数据
    * */
    public  static boolean handleCountryResponse(String response,int cityId){
        if (!TextUtils.isEmpty(response)){
            try{
                JSONArray allCounties = new JSONArray(response);
                for (int i=0 ;i<allCounties.length(); i++){
                    JSONObject countyObjest = allCounties.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(countyObjest.getString("name"));
                    county.setWeatherId(countyObjest.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }
    /*
    将返回的JSOn数据解析成Weather实体类
     */
    public static Weather handleWeatherResponse(String response){
        try {
            JSONObject jsonObject = new JSONObject(response);
            //JSONArray jsonArray = jsonObject.getJSONArray("");

           //String weatherContent = jsonArray.getJSONObject(0).toString();
            String weatherContent = jsonObject.toString();
            String jsonArray = jsonObject.getJSONObject("data").getString("forecast");
            Log.d("Utility", "jsonArray: "+jsonArray);
            //return new Gson().fromJson(weatherContent,Weather.class);

            Weather result = new Gson().fromJson(weatherContent,Weather.class);
            //List<Weather.Forecast1> forecast1List=result.getForecast1;
            //Weather weather = result+forecast1List;

            return result;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
