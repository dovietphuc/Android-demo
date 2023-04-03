package com.example.collection;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.collection.model.Image;
import com.example.collection.repository.ImageRepository;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
    private ImageRepository mRepository;
    private LiveData<List<Image>> mListImage;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        mRepository = new ImageRepository();
        mListImage = mRepository.getAllImage(application);
    }

    public LiveData<List<Image>> getAllImage(){
        return mListImage;
    }
}
