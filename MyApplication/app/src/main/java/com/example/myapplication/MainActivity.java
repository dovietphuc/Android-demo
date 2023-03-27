package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Cursor cursor = getContentResolver().query(
                Uri.parse("content://com.example.demoroomdatabase/student"),
                null,
                null,
                null,
                null
        );

        Log.d("PhucDVb", "onCreate: " + cursor.getCount());

    }
}