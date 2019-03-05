package com.coolweather.android;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by li on 2019/3/3 0003.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        Toast.makeText(this,"111",Toast.LENGTH_SHORT).show();
        if (prefs.getString("weather",null) != null){
            Toast.makeText(this,"3",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,WeatherActivity.class);
            startActivity(intent);
            Toast.makeText(this,"跳往weahterActivit",Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
