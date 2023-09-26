package com.example.bai1de2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;

public class ListSongFragment extends Fragment {

    public static final int TYPE_ALL_SONGS = 0;
    public static final int TYPE_FAVORISTED_SONGS = 1;

    private int type = TYPE_ALL_SONGS;

    public ListSongFragment(int type){
        this.type = type;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_song, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.rcv_list_song);
        ListSongAdapter adapter = new ListSongAdapter();
        recyclerView.setAdapter(adapter);

        if(type == TYPE_ALL_SONGS){
            adapter.data.addAll(Arrays.asList(DummyData.allSongs));
            DummyData.registerFavoritesChange((song, isFavorite) -> {
                adapter.notifyItemChanged(adapter.data.indexOf(song));
            });
        } else if(type == TYPE_FAVORISTED_SONGS) {
            adapter.data.addAll(Arrays.asList(DummyData.getFavorites()));
            DummyData.registerFavoritesChange((song, isFavorite) -> {
                if(isFavorite) {
                    adapter.data.add(song);
                    adapter.notifyItemInserted(adapter.data.indexOf(song));
                } else {
                    int index = adapter.data.indexOf(song);
                    adapter.data.remove(song);
                    adapter.notifyItemRemoved(index);
                }
            });
        }
    }
}