package com.example.filemanager;

import android.os.Environment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileRepository {
    public LiveData<List<File>> getFilesOfParent(File parent) {
        MutableLiveData<List<File>> files = new MutableLiveData<>(new ArrayList<>());
        if (parent == null) {
            parent = Environment.getExternalStorageDirectory();
        }
        File finalParent = parent;
        File[] listFiles = finalParent.listFiles();
        if (listFiles != null) {
            files.postValue(Arrays.asList(listFiles));
        }

        return files;
    }
}
