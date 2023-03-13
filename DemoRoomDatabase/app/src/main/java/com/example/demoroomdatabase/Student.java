package com.example.demoroomdatabase;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "student")
public class Student {
    @PrimaryKey(autoGenerate = true)
    private int uid;

    @NonNull
    private boolean state;

    @NonNull
    private String name;

    private String gender;

    @ColumnInfo(name = "class_name")
    private String className;

    public Student() {
    }

    public Student(boolean state, @NonNull String name, String gender, String className) {
        this.state = state;
        this.name = name;
        this.gender = gender;
        this.className = className;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Student{" +
                "uid=" + uid +
                ", state=" + state +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", className='" + className + '\'' +
                '}';
    }
}
