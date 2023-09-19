package com.example.bai2de1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {

    ArrayList<Message> data = new ArrayList<>();

    @Override
    public int getItemViewType(int position) {
        if(data.get(position).id == 0){
            return 1;
        }
        return 0;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == 0){
            return new MessageViewHolder(
                    LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.message_item, parent, false));
        }
        return new MessageViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.my_message_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MessageViewHolder extends RecyclerView.ViewHolder {

        TextView txtMessage;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMessage = (TextView) itemView.findViewById(R.id.txt_message);
        }

        public void bind(Message message) {
            txtMessage.setText(message.message);
        }
    }
}
