package com.xbtx.mallmodel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WeatherChartView line_char = findViewById(R.id.line_char);
        // 设置白天温度曲线
        line_char .setTempDay(new int[]{10,30,20,14,28,33});
        // 设置夜间温度曲线
        line_char .setTempNight(new int[]{7,11,19,22,4,10});
        line_char .invalidate();

    }
}