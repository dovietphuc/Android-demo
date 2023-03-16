package com.example.demoroomdatabase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class AddStudentViewModel extends AndroidViewModel {
    private StudentRepository mRepository;


    public AddStudentViewModel(@NonNull Application application) {
        super(application);
        mRepository = new StudentRepository(application);
    }

    public void addStudent(Student student){
        mRepository.insert(student);
    }
}
