package com.example.demoroomdatabase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ListStudentViewModel extends AndroidViewModel {
    private LiveData<List<Student>> mData;
    private StudentRepository mRepository;

    public ListStudentViewModel(@NonNull Application application) {
        super(application);
        mRepository = new StudentRepository(application);
        mData = mRepository.getAllStudent();
    }

    public LiveData<List<Student>> getAllStudent(){
        return mData;
    }

    public void insert(Student student){
        mRepository.insert(student);
    }

    public void update(Student student){
        mRepository.update(student);
    }

    public void delete(Student student){
        mRepository.delete(student);
    }

    public void delete(int uid){
        mRepository.delete(uid);
    }

}
