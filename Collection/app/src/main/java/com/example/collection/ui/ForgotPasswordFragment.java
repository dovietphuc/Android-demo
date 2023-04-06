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
import android.widget.Toast;

import com.example.collection.FirebaseAuthHelper;
import com.example.collection.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class ForgotPasswordFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_forgot_password, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText email = view.findViewById(R.id.email);
        Button resetPassword = view.findViewById(R.id.send_reset_password);

        resetPassword.setOnClickListener(v -> {
            FirebaseAuthHelper.forgotPassword(email.getText().toString(), new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(getContext(),
                                "We send a link to your email." +
                                        " You can use it to change your password",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(),
                                task.getException().getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });
    }
}