package com.example.bai1de1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MainActivityViewModel viewModel = new ViewModelProvider(getActivity())
                .get(MainActivityViewModel.class);

        EditText username = view.findViewById(R.id.edt_username);
        EditText password = view.findViewById(R.id.edt_password);
        Button login = view.findViewById(R.id.btn_login);

        viewModel.username.observe(getViewLifecycleOwner(), data -> {
            username.setText(data);
        });

        viewModel.password.observe(getViewLifecycleOwner(), data -> {
            password.setText(data);
        });
        
        login.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Login Successful", Toast.LENGTH_SHORT).show();
        });
    }
}