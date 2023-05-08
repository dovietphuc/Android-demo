package com.example.cameraxdemo;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageProxy;

import java.nio.Buffer;
import java.nio.ByteBuffer;

public class LuminosityAnalyzer implements ImageAnalysis.Analyzer {
    private LumaListener listener;
    public interface LumaListener{
        void onImage(int avg);
    }
    public LuminosityAnalyzer(LumaListener listener){
        this.listener = listener;
    }

    @Override
    public void analyze(@NonNull ImageProxy image) {
        ByteBuffer buffer = image.getPlanes()[0].getBuffer();
        byte[] data = toByteArrays(buffer);

        for(int i = 0; i < data.length; i++){
            data[i] = (byte) ((int)(data[i]) & 0xFF);
        }

        int avg = 0;
        int sum = 0;
        for(int i = 0; i < data.length; i++){
            sum += data[i];
        }

        avg = sum / data.length;
        listener.onImage(avg);
        image.close();
    }

    private byte[] toByteArrays(ByteBuffer buffer){
        buffer.rewind();    // Rewind the buffer to zero
        byte[] data = new byte[buffer.remaining()];
        buffer.get(data);   // Copy the buffer into a byte array
        return data; // Return the byte array
    }
}
