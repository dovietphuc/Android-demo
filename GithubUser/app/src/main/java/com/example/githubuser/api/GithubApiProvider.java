package com.example.githubuser.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GithubApiProvider {
    public static final String BASE_URL = "https://api.github.com";

    private static GithubApi mGithubApi;
    private static Retrofit mRetrofit;

    public static GithubApi getGithubApi(){
        if(mGithubApi == null){
            mGithubApi = getRetrofit().create(GithubApi.class);
        }
        return mGithubApi;
    }

    public static Retrofit getRetrofit(){
        if(mRetrofit == null){
            OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
            mRetrofit = new  Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }
}
