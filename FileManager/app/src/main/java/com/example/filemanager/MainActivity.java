package com.example.filemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        File file = Environment.getExternalStorageDirectory();
        logChild(file, 0);
    }

    public void logChild(File file, int level) {
        StringBuilder tab = new StringBuilder();
        for(int i = 0; i < level; i++) {
            tab.append("\t");
        }
        Log.d("PhucDVb", tab + "|" + file.getName());
        if(file.listFiles() != null) {
            for (File child : file.listFiles()) {
                logChild(child, level + 1);
            }
        }
    }

//
//    public File getRootFile(){
//        Uri uri = MediaStore.Files.getContentUri("external");
//        ContentResolver resolver = getContentResolver();
//        Cursor cursor = resolver.query(uri,
//                new String[]{MediaStore.Files.FileColumns.DATA},
//                MediaStore.Files.FileColumns.PARENT + "=-1", null, null);
//
//        if(cursor != null) {
//            while (cursor.moveToNext()) {
//                int dataIndex = cursor.getColumnIndex(MediaStore.Files.FileColumns.DATA);
//                String data = cursor.getString(dataIndex);
//                return new File(data);
//            }
//            cursor.close();
//        }
//
//        return null;
//    }
}