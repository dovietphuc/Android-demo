package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.txt_hello).setOnClickListener(v -> {
            TransitionDrawable drawable =
                    (TransitionDrawable) v.getBackground();
            drawable.reverseTransition(500);
        });
    }
}