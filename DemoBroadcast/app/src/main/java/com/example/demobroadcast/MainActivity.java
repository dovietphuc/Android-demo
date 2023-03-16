package com.example.demobroadcast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static final String ACTION_ADD_STUDENT = "com.example.demoroomdatabase.action.ACTION_ADD_STUDENT";
    public static final String EXTRA_STUDENT_NAME = "com.example.demoroomdatabase.extras.EXTRA_STUDENT_NAME";
    public static final String EXTRA_STUDENT_CLASS_NAME = "com.example.demoroomdatabase.extras.EXTRA_STUDENT_CLASS_NAME";
    public static final String EXTRA_STUDENT_GENDER = "com.example.demoroomdatabase.extras.EXTRA_STUDENT_GENDER";

    View mRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRoot = findViewById(R.id.root);

        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);

        registerReceiver(mReceiver, filter);

        findViewById(R.id.btn_add_student).setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setAction(ACTION_ADD_STUDENT);
            intent.putExtra(EXTRA_STUDENT_NAME, "Kanh");
            intent.putExtra(EXTRA_STUDENT_CLASS_NAME, "K34dl");
            intent.putExtra(EXTRA_STUDENT_GENDER, "Male");

            sendBroadcast(intent);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action){
                case Intent.ACTION_POWER_CONNECTED:
                    mRoot.setBackgroundColor(Color.GREEN);
                    break;
                case Intent.ACTION_POWER_DISCONNECTED:
                    mRoot.setBackgroundColor(Color.RED);
            }
        }
    };
}