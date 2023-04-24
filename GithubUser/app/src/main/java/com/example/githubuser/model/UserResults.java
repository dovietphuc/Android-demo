package com.example.githubuser.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserResults {
    @SerializedName("items")
    @Expose
    public List<User> users;

    @SerializedName("has_more")
    @Expose
    public boolean hasMore;
}
