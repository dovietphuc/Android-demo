package com.example.bai2de4;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {

    public List<Movie> mData = new ArrayList<>();
    public List<Movie> mFilters = new ArrayList<>();

    public void setData(List<Movie> data) {
        mData = data;
        mFilters = new ArrayList<>(data);
        notifyDataSetChanged();
    }

    public void filter(String key) {
        mFilters.clear();
        if(TextUtils.isEmpty(key)) {
            mFilters.addAll(mData);
        } else {
            for (Movie item : mData) {
                if (item.title.toLowerCase().contains(key.toLowerCase().trim())) {
                    mFilters.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MoviesViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesViewHolder holder, int position) {
        Picasso.get()
                .load(mFilters.get(position).image)
                .into(holder.imageView);
        holder.textView.setText(mFilters.get(position).title);
    }

    @Override
    public int getItemCount() {
        return mFilters.size();
    }


    public class MoviesViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public MoviesViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.img_banner);
            textView = (TextView) itemView.findViewById(R.id.txt_title);
        }
    }
}
