package com.example.bai1de3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.bai1de3.databinding.ActivityMainBinding;
import com.example.bai1de3.databinding.SignUpDialogBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnRegister.setOnClickListener(v -> {
            startRegister();
        });

        binding.btnLogin.setOnClickListener(v -> {
            String username = binding.inputUsername.getText().toString();
            String password = binding.inputPassword.getText().toString();
            if(TextUtils.isEmpty(username) && TextUtils.isEmpty(password)){
                SignUpDialogBinding signUpDialogBinding = SignUpDialogBinding.inflate(getLayoutInflater());
                AlertDialog alertDialog =
                        new AlertDialog.Builder(this)
                                .setView(signUpDialogBinding.getRoot())
                                .create();

                alertDialog.show();

                signUpDialogBinding.btnCancel.setOnClickListener(v1 -> {
                    alertDialog.dismiss();
                });
                signUpDialogBinding.btnOk.setOnClickListener(v1 -> {
                    startRegister();
                    alertDialog.dismiss();
                });
            } else {
                Toast.makeText(this, username + " - " + password, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void startRegister(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivityForResult(intent, 999);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 999) {
            if(resultCode == RESULT_OK) {
                String userName = data.getStringExtra("USERNAME");
                String password = data.getStringExtra("PASSWORD");
                binding.inputUsername.setText(userName);
                binding.inputPassword.setText(password);
            }
        }
    }
}