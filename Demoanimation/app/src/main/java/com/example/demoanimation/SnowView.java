package com.example.demoanimation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.Random;

public class SnowView extends View {
    public SnowView(Context context) {
        super(context);
        initSnow();
    }

    public SnowView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initSnow();
    }

    public SnowView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initSnow();
    }

    public SnowView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initSnow();
    }

    int MAX_SNOW = 100;
    int MAX_Z = 30;
    int SNOW_SIZE = 1;

    public class Snow {
        int x;
        int y;
        int z;
    }

    Snow[] snows = new Snow[MAX_SNOW];

    Paint paint;

    public void initSnow(){
        paint = new Paint();
        paint.setColor(Color.WHITE);

        Random random = new Random();
        for(int i = 0; i < MAX_SNOW; i++){
            Snow snow = new Snow();
            snow.x = random.nextInt(1080);
            snow.y = random.nextInt(2400);
            snow.z = random.nextInt(MAX_Z);
            snows[i] = snow;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < snows.length; i++) {
            int r = SNOW_SIZE * snows[i].z;
            int a = (int)(255 * ((float)snows[i].z / MAX_Z));
            paint.setARGB(a, 255, 255, 255);
            canvas.drawCircle(snows[i].x, snows[i].y, r, paint);
        }
    }
}
