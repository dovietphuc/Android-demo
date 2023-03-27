package com.example.demoservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.play_music).setOnClickListener(v -> {
            Intent intent = new Intent(this, DemoService.class);
            intent.setAction("PLAY_MUSIC");
            startForegroundService(intent);

        });
    }
}