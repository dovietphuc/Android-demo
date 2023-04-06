package com.example.collection.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.collection.FirebaseAuthHelper;
import com.example.collection.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class SignUpFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText email = view.findViewById(R.id.email);
        EditText password = view.findViewById(R.id.password);
        EditText confirmPassword = view.findViewById(R.id.confirm_password);
        Button signUp = view.findViewById(R.id.btn_sign_up);
        ProgressBar progressBar = view.findViewById(R.id.loading);

        signUp.setOnClickListener(v -> {
            if(password.getText().toString().equals(confirmPassword.getText().toString())){
                progressBar.setVisibility(View.VISIBLE);
                FirebaseAuthHelper.signUp(email.getText().toString(), password.getText().toString(),
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if(task.isSuccessful()){
                                    Toast.makeText(getContext(),
                                            "Sign up successfully for email "
                                                    + email.getText().toString()
                                            , Toast.LENGTH_SHORT).show();
                                    requireActivity().onBackPressed();
                                } else {
                                    Toast.makeText(getContext(),
                                            task.getException().getMessage(),
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            } else {
                Toast.makeText(getContext(), "Invalid confirm password", Toast.LENGTH_SHORT).show();
            }
        });
    }
}