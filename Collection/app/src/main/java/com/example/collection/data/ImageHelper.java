package com.example.collection.data;

import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.provider.MediaStore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.example.collection.model.Image;

import java.util.ArrayList;
import java.util.List;

public class ImageHelper {
    public void loadAllImage(Context context, Callback callback){
        new LoadImageAsyncTaskLoader(context, callback).forceLoad();
    }

    public interface Callback {
        void onFinish(List<Image> images);
    }
    private class LoadImageAsyncTaskLoader extends AsyncTaskLoader<List<Image>> {
        private Callback callback;

        public LoadImageAsyncTaskLoader(@NonNull Context context, Callback callback) {
            super(context);
            this.callback = callback;
        }

        @Nullable
        @Override
        public List<Image> loadInBackground() {
            return getAllImages(getContext());
        }

        @Override
        public void deliverResult(@Nullable List<Image> data) {
            super.deliverResult(data);
            if(callback != null){
                callback.onFinish(data);
            }
        }
    }

    private List<Image> getAllImages(Context context){
        List<Image> list = new ArrayList<>();

        String[] projection = {
                MediaStore.Images.ImageColumns._ID,
                MediaStore.Images.ImageColumns.TITLE,
                MediaStore.Images.ImageColumns.DATA
        };

        String where = null;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            where = MediaStore.Images.ImageColumns.IS_TRASHED + " = 0";
        }

        Cursor cursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection,
                where,
                null,
                MediaStore.Images.ImageColumns.DATE_ADDED + " DESC"
        );

        if(cursor != null){
            int idIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns._ID);
            int nameIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.TITLE);
            int dataIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DATA);

            while (cursor.moveToNext()){
                long id = cursor.getLong(idIndex);
                String name = cursor.getString(nameIndex);
                String data = cursor.getString(dataIndex);

                Image image = new Image(id, name, data);
                list.add(image);
            }
        }

        return list;
    }
}
