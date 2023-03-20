package com.example.musicapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    MainActivityViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 999);
        } else {
            init();
        }

    }

    public void init(){
        mViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.rcv_list_song);
        SongListAdapter adapter = new SongListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mViewModel.getAllMusic().observe(this, new Observer<List<Song>>() {
            @Override
            public void onChanged(List<Song> songs) {
                adapter.setData(songs);
            }
        });

        adapter.setOnItemClickListener(new SongListAdapter.OnItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                if(position == mViewModel.getMusicController().getCurrentIndex()){
                    if(mViewModel.getMusicController().isPlaying()) {
                        adapter.status = SongListAdapter.STOP;
                        mViewModel.getMusicController().pause();
                        v.setBackgroundColor(Color.YELLOW);
                    } else {
                        adapter.status = SongListAdapter.PLAY;
                        mViewModel.getMusicController().start();
                        v.setBackgroundColor(Color.GREEN);
                    }
                } else {
                    adapter.status = SongListAdapter.PLAY;
                    adapter.setCurrentIndex(position);
                    adapter.notifyItemChanged(mViewModel.getMusicController().getCurrentIndex());
                    mViewModel.getMusicController().playSongAt(MainActivity.this, position);
                    v.setBackgroundColor(Color.GREEN);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 999) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                init();
            }
        }
    }


}