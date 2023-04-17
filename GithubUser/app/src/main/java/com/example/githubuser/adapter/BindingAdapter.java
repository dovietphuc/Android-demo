package com.example.githubuser.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class BindingAdapter {

    @androidx.databinding.BindingAdapter(value = {"url"})
    public static void bindImageView(ImageView view, String url) {
        Glide.with(view)
                .load(url)
                .override(100, 200)
                .fitCenter()
                .into(view);
    }
}
