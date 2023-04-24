package com.example.githubuser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.githubuser.adapter.UserDiff;
import com.example.githubuser.adapter.UserListAdapter;
import com.example.githubuser.databinding.ActivityMainBinding;
import com.example.githubuser.model.User;

import java.util.List;

import autodispose2.AutoDispose;
import autodispose2.androidx.lifecycle.AndroidLifecycleScopeProvider;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mBinding;

    MainActivityViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mViewModel
                = new ViewModelProvider(this).get(MainActivityViewModel.class);

        UserListAdapter adapter = new UserListAdapter(new UserDiff());
        mBinding.setUserAdapter(adapter);

        mViewModel.flowable.to(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider
                        .from(this)))
                .subscribe(userPagingData -> {
                    adapter.submitData(getLifecycle(), userPagingData);
                });

    }
}