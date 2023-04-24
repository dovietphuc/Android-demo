package com.example.githubuser;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.rxjava3.PagingRx;

import com.example.githubuser.model.User;
import com.example.githubuser.paging.UserPageSource;
import com.example.githubuser.repo.GithubUserRepository;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import kotlinx.coroutines.CoroutineScope;

public class MainActivityViewModel extends ViewModel {
    Pager<Integer, User> pager = new Pager<>(
            new PagingConfig(/* pageSize = */ 50),
            () -> new UserPageSource());

    Flowable<PagingData<User>> flowable;

    public MainActivityViewModel(){
        CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(this);
        flowable = PagingRx.getFlowable(pager);
        {
            PagingRx.cachedIn(flowable, viewModelScope);
        }
    }
}
