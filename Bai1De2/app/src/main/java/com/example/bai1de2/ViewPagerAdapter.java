package com.example.bai1de2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        int type = -1;
        if(position == 0) {
            type = ListSongFragment.TYPE_ALL_SONGS;
        } else if(position == 1) {
            type = ListSongFragment.TYPE_FAVORISTED_SONGS;
        }
        return new ListSongFragment(type);
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
