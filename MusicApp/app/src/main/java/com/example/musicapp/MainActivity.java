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
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
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
    SongListAdapter mAdapter;
    private MusicService.MusicBinder mBinder;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d("PhucDVb", "onServiceConnected: ");
            mBinder = (MusicService.MusicBinder) iBinder;
            mBinder.getMusics().observe(MainActivity.this, new Observer<List<Song>>() {
                @Override
                public void onChanged(List<Song> songs) {
                    if(mBinder.getMusicController().isPlaying()) {
                        mAdapter.status = SongListAdapter.PLAY;
                    } else {
                        mAdapter.status = SongListAdapter.STOP;
                    }
                    mAdapter.setCurrentIndex(mBinder.getMusicController().getCurrentIndex());
                    mAdapter.setData(songs);
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d("PhucDVb", "onServiceDisconnected: ");
        }
    };

    public void init(){
        mViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.rcv_list_song);
        mAdapter = new SongListAdapter(this);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = new Intent(this, MusicService.class);
        startForegroundService(intent);
        bindService(intent, mConnection, Service.BIND_AUTO_CREATE);

        mAdapter.setOnItemClickListener(new SongListAdapter.OnItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                if(position == mBinder.getMusicController().getCurrentIndex()){
                    if(mBinder.getMusicController().isPlaying()) {
                        mAdapter.status = SongListAdapter.STOP;
                        mBinder.pause();
                        v.setBackgroundColor(Color.YELLOW);
                    } else {
                        mAdapter.status = SongListAdapter.PLAY;
                        mBinder.getMusicController().start();
                        v.setBackgroundColor(Color.GREEN);
                    }
                } else {
                    mAdapter.status = SongListAdapter.PLAY;
                    mAdapter.setCurrentIndex(position);
                    mAdapter.notifyItemChanged(mBinder.getMusicController().getCurrentIndex());
                    mBinder.playSongAt(position);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mConnection);
    }
}