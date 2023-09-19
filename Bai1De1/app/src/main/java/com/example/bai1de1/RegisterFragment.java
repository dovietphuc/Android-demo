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

public class RegisterFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MainActivityViewModel viewModel =
                new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);

        Button register = (Button) view.findViewById(R.id.btn_reigster);
        EditText username = view.findViewById(R.id.edt_username);
        EditText password = (EditText) view.findViewById(R.id.edt_password);
        EditText confirmPassword = (EditText) view.findViewById(R.id.edt_confirm_password);

        register.setOnClickListener(v -> {
            String strPassword = password.getText().toString();
            String strConfirmPassword = confirmPassword.getText().toString();

            if(!strPassword.equals(strConfirmPassword)) {
                Toast.makeText(getContext(), "Incorrect confirm password"
                        , Toast.LENGTH_SHORT).show();
                return;
            }

            if(strPassword.length() < 6) {
                Toast.makeText(getContext(),
                        "Password length must be greater then 5 character",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            viewModel.currentPage.postValue(0);
            viewModel.username.postValue(username.getText().toString());
            viewModel.password.postValue(password.getText().toString());
        });

    }
}