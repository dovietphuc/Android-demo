package com.example.collection.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.collection.FirebaseAuthHelper;
import com.example.collection.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class LoginFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_login, container, false);
        return root;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavController navController =
                Navigation.findNavController(requireActivity(), R.id.nav_host);
        view.findViewById(R.id.txt_sign_up).setOnClickListener(v -> {
            navController.navigate(R.id.signUpFragment);
        });
        view.findViewById(R.id.txt_forgot_password).setOnClickListener(v -> {
            navController.navigate(R.id.forgotPasswordFragment);
        });

        EditText email = view.findViewById(R.id.username);
        EditText password = view.findViewById(R.id.password);
        Button login = view.findViewById(R.id.login);
        ProgressBar progressBar = view.findViewById(R.id.loading);

        login.setOnClickListener(v -> {
            progressBar.setVisibility(View.VISIBLE);
            FirebaseAuthHelper.signIn(email.getText().toString(), password.getText().toString(),
                    new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.GONE);
                            if(task.isSuccessful()){
                                requireActivity().onBackPressed();
                                Toast.makeText(getContext(),
                                        "Login success",
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