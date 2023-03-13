package com.example.demoroomdatabase;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class StudentRepository {
    private StudentDAO mStudentDAO;

    public StudentRepository(Application application){
        DemoRoomDatabase db = DemoRoomDatabase.getInstance(application);
        mStudentDAO = db.studentDao();
    }

    public LiveData<List<Student>> getAllStudent(){
        return mStudentDAO.getAllStudent();
    }

    public void insert(Student student){
        new Thread(() -> {
            mStudentDAO.insert(student);
        }).start();
    }

    public void update(Student student){
        new Thread(() -> {
            mStudentDAO.update(student);
        }).start();
    }

    public void delete(Student student){
        new Thread(() -> {
            mStudentDAO.delete(student.getUid());
        }).start();
    }

    public void delete(int uid){
        new Thread(() -> {
            mStudentDAO.delete(uid);
        }).start();
    }
}
