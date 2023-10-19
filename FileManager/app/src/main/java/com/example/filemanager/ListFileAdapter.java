package com.example.filemanager;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ListFileAdapter extends RecyclerView.Adapter<ListFileAdapter.FileViewHolder> {

    public List<File> files = new ArrayList<>();

    OnItemClickListener listener = new OnItemClickListener() {
        @Override
        public void onItemClick(View v, int position) {
        }
    };

    @NonNull
    @Override
    public FileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FileViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.file_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FileViewHolder holder, int position) {
        File file = files.get(position);
        holder.txtName.setText(file.getName());
        String size = file.length() / 1024f + " KB";
        holder.txtSize.setText(size);
        holder.itemView.setOnClickListener(v -> {
            listener.onItemClick(v, position);
        });
        if(file.isDirectory()) {
            holder.itemView.setBackgroundColor(Color.YELLOW);
        } else if(file.isFile()) {
            holder.itemView.setBackgroundColor(Color.BLUE);
        } else {
            holder.itemView.setBackgroundColor(Color.CYAN);
        }
    }

    @Override
    public int getItemCount() {
        return files.size();
    }

    public class FileViewHolder extends RecyclerView.ViewHolder {
        TextView txtName;
        TextView txtSize;
        public FileViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.txt_name);
            txtSize = (TextView) itemView.findViewById(R.id.txt_size);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }
}
