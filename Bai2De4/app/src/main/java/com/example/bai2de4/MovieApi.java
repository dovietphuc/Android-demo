package com.example.bai2de4;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieApi {
    @GET("json/movies.json")
    Call<List<Movie>> getAllMovies();
}
