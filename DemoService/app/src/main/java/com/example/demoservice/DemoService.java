package com.example.demoservice;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class DemoService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("PhucDVb", "onCreate: ");
        createNotificationChannel();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                startForeground(999, createNotification());
                int count = 0;
                while (count <= 100) {
                    count ++;
                    Log.d("PhucDVb", "Count = " + count);
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                stopForeground(false);
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is not in the Support Library.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Demo channel";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel =
                    new NotificationChannel("demo_channel_id", name, importance);
            // Register the channel with the system. You can't change the importance
            // or other notification behaviors after this.
            NotificationManager notificationManager =
                    getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private Notification createNotification(){
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, "demo_channel_id")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Foreground service is running")
                .setContentText("This is content")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        return builder.build();
    }
}
