package com.example.githubuser.adapter;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.githubuser.model.User;

public class UserDiff extends DiffUtil.ItemCallback<User> {

    @Override
    public boolean areItemsTheSame(@NonNull User oldItem, @NonNull User newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull User oldItem, @NonNull User newItem) {
        return TextUtils.equals(oldItem.getAvatarUrl(), newItem.getAvatarUrl())
                && TextUtils.equals(oldItem.getUsername(), newItem.getUsername());
    }
}
