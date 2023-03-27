package com.example.demoroomdatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StudentDAO {

    @Insert
    public void insert(Student... student);

    @Update
    public void update(Student... student);

    @Query("SELECT * FROM student WHERE state = 1")
    public LiveData<List<Student>> getAllStudent();

    @Query("DELETE FROM student WHERE uid = :uid")
    public void delete(int uid);

    @Query("SELECT * FROM student WHERE state = 1")
    public List<Student> getAllStudentNoAsyn();
}





