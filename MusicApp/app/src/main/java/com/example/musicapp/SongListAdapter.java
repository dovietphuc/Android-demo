package com.example.musicapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SongListAdapter extends RecyclerView.Adapter<SongListAdapter.SongViewHolder> {
    public static final int NONE = 0;
    public static final int PLAY = 1;
    public static final int STOP = 2;



    private List<Song> mData;
    private LayoutInflater layoutInflater;
    private OnItemClickListener mOnItemClickListener;
    private int mCurrentIndex = -1;
    public int status;

    public void setCurrentIndex(int index){
        mCurrentIndex = index;
    }

    public interface OnItemClickListener {
        void onClick(View v, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mOnItemClickListener = listener;
    }

    public void setData(List<Song> data){
        mData = data;
        notifyDataSetChanged();
    }

    public SongListAdapter(Context context){
        mData = new ArrayList<>();
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SongViewHolder(layoutInflater.inflate(R.layout.song_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SongViewHolder holder, int position) {
        holder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class SongViewHolder extends RecyclerView.ViewHolder{

        View mItemView;
        TextView mTxtTitle;
        TextView mTxtDuration;

        public SongViewHolder(@NonNull View itemView) {
            super(itemView);
            mItemView = itemView;
            mTxtTitle = itemView.findViewById(R.id.txt_title);
            mTxtDuration = itemView.findViewById(R.id.txt_duration);

            itemView.setOnClickListener(v -> {
                if(mOnItemClickListener != null){
                    mOnItemClickListener.onClick(itemView, getAdapterPosition());
                }
            });
        }

        public void bind(Song song){
            if(mCurrentIndex == getAdapterPosition()) {
                switch (status) {
                    case STOP:
                        mItemView.setBackgroundColor(Color.parseColor("#ffff00"));
                        break;
                    case PLAY:
                        mItemView.setBackgroundColor(Color.parseColor("#00ff00"));
                        break;
                    case NONE:
                        mItemView.setBackgroundColor(Color.parseColor("#f1f1f1"));
                        break;
                }
            } else {
                mItemView.setBackgroundColor(Color.parseColor("#f1f1f1"));
            }
            mTxtTitle.setText(song.getTitle());
            mTxtDuration.setText(song.getFormatTimes());
        }
    }

}
