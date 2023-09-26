package com.example.bai2de2;

import com.google.gson.annotations.SerializedName;

public class Article {
    @SerializedName("title")
    public String title;
    @SerializedName("author")
    public String author;
    @SerializedName("description")
    public String description;
    @SerializedName("urlToImage")
    public String urlToImage;
}
