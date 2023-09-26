package com.example.bai2de2;

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

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {

    public List<Article> data = new ArrayList<>();

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ArticleViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.article_item, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder {
        ImageView imgBanner;
        TextView txtTitle;
        TextView txtAuthor;
        TextView txtDescription;

        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBanner = (ImageView) itemView.findViewById(R.id.img_banner);
            txtTitle = (TextView) itemView.findViewById(R.id.txt_title);
            txtAuthor = (TextView) itemView.findViewById(R.id.txt_author);
            txtDescription = (TextView) itemView.findViewById(R.id.txt_description);
        }

        public void bind(Article article) {
            txtAuthor.setText(article.author);
            txtTitle.setText(article.title);
            txtDescription.setText(article.description);
            Picasso.get()
                    .load(article.urlToImage)
                    .into(imgBanner);
        }
    }
}
