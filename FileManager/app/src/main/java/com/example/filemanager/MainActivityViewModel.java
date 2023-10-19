package com.example.filemanager;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.io.File;
import java.util.List;

public class MainActivityViewModel extends ViewModel {
    private final FileRepository fileRepository;

    public MainActivityViewModel(){
        fileRepository = new FileRepository();
    }

    public LiveData<List<File>> getFiles(File parent) {
        return fileRepository.getFilesOfParent(parent);
    }
}
