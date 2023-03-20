package com.example.musicapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
    private MusicRepository mMusicRepository;
    private LiveData<List<Song>> mListSong;
    private MusicController mMusicController;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        mMusicRepository = new MusicRepository(application);
        mListSong = mMusicRepository.getAllMusic();

        mMusicController = new MusicController(application, new MusicController.MusicSource() {
            @Override
            public int getSize() {
                return mListSong.getValue().size();
            }

            @Override
            public Song getAtIndex(int index) {
                return mListSong.getValue().get(index);
            }
        });
    }

    public MusicController getMusicController(){
        return mMusicController;
    }

    public LiveData<List<Song>> getAllMusic(){
        return mListSong;
    }

}
