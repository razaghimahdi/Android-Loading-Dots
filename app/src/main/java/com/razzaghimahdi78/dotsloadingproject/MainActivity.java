package com.razzaghimahdi78.dotsloadingproject;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.razzaghimahdi78.dotsloading.core.DotSize;
import com.razzaghimahdi78.dotsloading.linear.LoadingWavy;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        LoadingWavy LoadingWavy = findViewById(R.id.loadingWavy);
        LoadingWavy.setSize(30);
        LoadingWavy.setDotsCount(3);
        LoadingWavy.setDuration(400);
        LoadingWavy.setColor(Color.parseColor("#FF3700B3"));


    }
}