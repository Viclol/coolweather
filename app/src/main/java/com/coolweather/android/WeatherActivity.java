package com.coolweather.android;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.coolweather.android.gson.Data;
import com.coolweather.android.gson.Forecast;
import com.coolweather.android.gson.Forecast1;
import com.coolweather.android.gson.Weather;
import com.coolweather.android.util.HttpUtil;
import com.coolweather.android.util.Utility;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WeatherActivity extends AppCompatActivity {

    private ScrollView weatherLayout;
    private TextView titleCity;
    private TextView titleUpdateTime;
    private TextView degreeText;
    private TextView weatherInfoText;
    private LinearLayout forecastLayout;
    private TextView aqiText;
    private TextView pm25Text;
    private TextView comfortText;
    private TextView carWashText;
    private TextView sportText;
    private ImageView bingPicImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21){
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_weather);
        //初始化各控件
        weatherLayout = (ScrollView) findViewById(R.id.weather_layout);
        titleCity = (TextView) findViewById(R.id.title_city);
        titleUpdateTime = (TextView)findViewById(R.id.title_update_time);
        degreeText = (TextView)findViewById(R.id.degree_text);
        weatherInfoText = (TextView)findViewById(R.id.weather_info_text);
        forecastLayout = (LinearLayout) findViewById(R.id.forecast_layout);
        aqiText = (TextView)findViewById(R.id.aqi_text);
        pm25Text = (TextView)findViewById(R.id.pm25_text);
        comfortText = (TextView)findViewById(R.id.comfort_text);
        carWashText = (TextView)findViewById(R.id.car_wash_text);
        sportText = (TextView)findViewById(R.id.sport_text);
        bingPicImg = (ImageView) findViewById(R.id.bing_pic_img);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String weatherString = prefs.getString("weather",null);
        if (weatherString != null){
            //有缓存时直接解析天气数据
            Weather weather = Utility.handleWeatherResponse(weatherString);
            showWeathererInfo(weather);
        }else{
            //无缓存时去服务器查询天气
            String weatherId1 = getIntent().getStringExtra("weather_id");
            String weatherId = weatherId1.substring(2);
            Log.d("weather","weatherId"+weatherId);
            weatherLayout.setVisibility(View.INVISIBLE);
            requestWeather(weatherId);
        }

        String bingPic = prefs.getString("bing_pic",null);
        if (bingPic != null){
            Glide.with(this).load(bingPic).into(bingPicImg);
        }else {
            loadBingPic();
        }
    }
    /*
    根据天气id请求城市天气信息
     */
    public void requestWeather(final String weatherId){

        Toast.makeText(this,"weatherId"+weatherId,Toast.LENGTH_SHORT).show();
        String weatherUrl = "http://t.weather.sojson.com/api/weather/city/"+weatherId;
        HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(WeatherActivity.this,"获取天气信息失败22",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                final Weather weather = Utility.handleWeatherResponse(responseText);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (weather != null && weather.status == 200){
                            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this).edit();
                            editor.putString("weather",responseText);
                            editor.apply();
                            showWeathererInfo(weather);
                        }else{
                            Toast.makeText(WeatherActivity.this,"获取天气信息失败11",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
        loadBingPic();
    }
    /*
    加载必应每日一图
     */
    private void loadBingPic(){
        String requestBingPic = "http://guolin.tech/api/bing_pic";
        HttpUtil.sendOkHttpRequest(requestBingPic, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String bingPic = response.body().string();
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this).edit();
                editor.putString("bing_pic",bingPic);
                editor.apply();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(WeatherActivity.this).load(bingPic).into(bingPicImg);
                    }
                });

            }
        });
    }
    /*
    处理并展示Weather 实体类中的数据
     */
    private void showWeathererInfo(Weather weather){
        //String cityName = weather.basic.cityName;

        String cityName = weather.cityInfo.cityName;
        //String updateTime = weather.basic.updatel.updateTime.split(" ")[1];
        String updateTime = weather.cityInfo.updateTime;
        //String degree = weather.now.temperature+"℃";
        String degree = weather.data.wenDu+"℃";
        Log.d("Weather","cityinfo"+weather.data.wenDu);
        String weatherInfo = weather.data.quality;
        titleCity.setText(cityName);
        titleUpdateTime.setText(updateTime);
        degreeText.setText(degree);
        weatherInfoText.setText(weatherInfo);
        forecastLayout.removeAllViews();
        Log.d("Weather","list"+weather.forecast1List);
//        for (Weather.Forecast1 forecast1:weather.forecast1){
//            View view = LayoutInflater.from(this).inflate(R.layout.forecast_item,forecastLayout,false);
//            TextView dataText = (TextView) view.findViewById(R.id.data_text);
//            TextView infoText = (TextView) view.findViewById(R.id.info_text);
//            TextView maxText = (TextView) view.findViewById(R.id.max_text);
//            TextView minText = (TextView) view.findViewById(R.id.min_text);
//            dataText.setText(forecast1.ymd);
//            infoText.setText(forecast1.type);
//            maxText.setText(forecast1.max);
//            minText.setText(forecast1.min);
//            forecastLayout.addView(view);
//        }
        if (weather.data != null){
            aqiText.setText(weather.data.quality);
            pm25Text.setText(weather.data.pm25);
        }
        String comfort = "舒适度："+weather.data.ganMao;
        String carWash = "洗车指数：" + weather.data.ganMao;
        String sport = "运动建议："+weather.data.ganMao;
        comfortText.setText(comfort);
        carWashText.setText(carWash);
        sportText.setText(sport);
        weatherLayout.setVisibility(View.VISIBLE);
    }

}
