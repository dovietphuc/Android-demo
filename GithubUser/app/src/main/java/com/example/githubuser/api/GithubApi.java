package com.example.githubuser.api;

import com.example.githubuser.model.User;
import com.example.githubuser.model.UserResults;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GithubApi {

    @GET("users")
    Single<UserResults> getAllUsers(@Query("page") int page,
                                    @Query("pagesize") int pageSize,
                                    @Query("site") String site);
}
