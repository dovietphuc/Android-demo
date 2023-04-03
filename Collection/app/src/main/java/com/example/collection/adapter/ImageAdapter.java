package com.example.collection.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.collection.R;
import com.example.collection.model.Image;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    public static final int TYPE_LIST_ITEM = 0;
    public static final int TYPE_PAGE_ITEM = 1;

    private int mViewType = TYPE_LIST_ITEM;

    private List<Image> mData;
    private OnItemClickListener mListener;

    public void setItemType(int type){
        mViewType = type;
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public ImageAdapter(){
        mData = new ArrayList<>();
    }

    public void setData(List<Image> data){
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return mViewType;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == TYPE_LIST_ITEM) {
            return new ImageViewHolder(
                    LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.image_item, parent, false)
            );
        } else if(viewType == TYPE_PAGE_ITEM){
            return new ImageViewHolder(
                    LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.image_page, parent, false)
            );
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        holder.bindView(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);

            itemView.setOnClickListener(v -> {
                if(mListener != null){
                    mListener.onClick(v, mData.get(getAdapterPosition()), getAdapterPosition());
                }
            });
        }

        public void bindView(Image image){
            if(getItemViewType() == TYPE_LIST_ITEM) {
                Glide.with(imageView.getContext())
                        .load(image.getData())
                        .centerCrop()
                        .placeholder(R.drawable.ic_baseline_image_24)
                        .error(R.drawable.ic_baseline_broken_image_24)
                        .into(imageView);
            } else {
                Glide.with(imageView.getContext())
                        .load(image.getData())
                        .placeholder(R.drawable.ic_baseline_image_24)
                        .error(R.drawable.ic_baseline_broken_image_24)
                        .into(imageView);
            }
        }
    }

    public interface OnItemClickListener {
        void onClick(View view, Image image, int position);
    }
}
