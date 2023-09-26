package com.example.bai1de2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListSongAdapter extends RecyclerView.Adapter<ListSongAdapter.SongViewHolder> {

    public List<Song> data = new ArrayList<>();

    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SongViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.song_item, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull SongViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class SongViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView artist;
        public ImageView favorite;

        public SongViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.song_name);
            artist = itemView.findViewById(R.id.song_artist);
            favorite = itemView.findViewById(R.id.img_favorite);

            itemView.setOnClickListener(v -> {
                boolean isFavorite = data.get(getAdapterPosition()).isFavorite;
                DummyData.favoriteSong(data.get(getAdapterPosition()), !isFavorite);
            });
        }

        public void bind(Song song){
            name.setText(song.name);
            artist.setText(song.artist);
            if(song.isFavorite){
                favorite.setImageResource(R.drawable.baseline_favorite_24);
            } else {
                favorite.setImageResource(R.drawable.baseline_favorite_border_24);
            }
        }

    }

}
