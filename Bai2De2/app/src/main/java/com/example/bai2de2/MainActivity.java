package com.example.bai2de2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            Gson gson = new Gson();
            String stringData = convertStreamToString(getAssets().open("data.json"));
            Data data = gson.fromJson(stringData, Data.class);

            RecyclerView rcv = (RecyclerView) findViewById(R.id.rcv_articles);
            ArticleAdapter adapter = new ArticleAdapter();
            adapter.data.addAll(Arrays.asList(data.articles));
            rcv.setAdapter(adapter);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }
}