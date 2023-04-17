package com.example.githubuser;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.githubuser.model.User;
import com.example.githubuser.repo.GithubUserRepository;

import java.util.List;

public class MainActivityViewModel extends ViewModel {
    private GithubUserRepository mRepository;

    public MainActivityViewModel(){
        mRepository = new GithubUserRepository();
    }

    public LiveData<List<User>> getAllUsers(){
        return mRepository.getAllUsers();
    }
}
