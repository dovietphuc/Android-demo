package com.example.githubuser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.githubuser.adapter.UserListAdapter;
import com.example.githubuser.databinding.ActivityMainBinding;
import com.example.githubuser.model.User;

import java.util.List;

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

        UserListAdapter adapter = new UserListAdapter();
        mBinding.setUserAdapter(adapter);

        mViewModel.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                adapter.setData(users);
            }
        });
    }
}