package com.example.githubuser.repo;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.githubuser.api.GithubApi;
import com.example.githubuser.api.GithubApiProvider;
import com.example.githubuser.model.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GithubUserRepository {
    public LiveData<List<User>> getAllUsers(){
        MutableLiveData<List<User>> liveData
                = new MutableLiveData<>(new ArrayList<>());

        GithubApi githubApi = GithubApiProvider.getGithubApi();
        githubApi.getAllUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(response.code() == 200) {
                    liveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e("PhucDVb", "onFailure: ", t);
            }
        });

        return liveData;
    }
}
