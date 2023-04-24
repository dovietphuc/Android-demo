package com.example.githubuser.repo;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.githubuser.api.GithubApi;
import com.example.githubuser.api.GithubApiProvider;
import com.example.githubuser.model.User;
import com.example.githubuser.model.UserResults;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GithubUserRepository {
    public LiveData<List<User>> getAllUsers(){
        MutableLiveData<List<User>> liveData
                = new MutableLiveData<>(new ArrayList<>());

//        GithubApi githubApi = GithubApiProvider.getGithubApi();
//        githubApi.getAllUsers(2, 20, "stackoverflow").enqueue(new Callback<UserResults>() {
//            @Override
//            public void onResponse(Call<UserResults> call, Response<UserResults> response) {
//                if(response.code() == 200) {
//                    liveData.setValue(response.body().users);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<UserResults> call, Throwable t) {
//                Log.e("PhucDVb", "onFailure: ", t);
//            }
//        });

        return liveData;
    }
}
