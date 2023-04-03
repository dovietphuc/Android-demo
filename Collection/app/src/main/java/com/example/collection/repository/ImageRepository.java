package com.example.collection.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.collection.data.ImageHelper;
import com.example.collection.model.Image;

import java.util.ArrayList;
import java.util.List;

public class ImageRepository {
    public LiveData<List<Image>> getAllImage(Context context){
        MutableLiveData<List<Image>> data = new MutableLiveData<>(new ArrayList<>());

        ImageHelper imageHelper = new ImageHelper();
        imageHelper.loadAllImage(context, new ImageHelper.Callback() {
            @Override
            public void onFinish(List<Image> images) {
                data.setValue(images);
            }
        });

        return data;
    }
}
