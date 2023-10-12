package com.example.bai1de3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;

import com.example.bai1de3.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnCancel.setOnClickListener(v -> {
            setResult(RESULT_CANCELED);
            finish();
        });

        binding.btnSignIn.setOnClickListener(v -> {
            if(validate()) {
                Intent intent = new Intent();
                intent.putExtra("USERNAME", binding.inputUsername.getText().toString());
                intent.putExtra("PASSWORD", binding.inputPassword.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    public boolean validate(){
        boolean isValid = true;

        if(!isValidEmail(binding.inputEmail.getText().toString())){
            binding.inputEmail.setError("Please enter a valid email");
            isValid = false;
        }

        String rgx = "[a-z]+";
        if(!binding.inputUsername.getText().toString().matches(rgx)) {
            binding.inputUsername.setError("Invalid");
            isValid = false;
        }

        if(!binding.inputPassword.getText().toString()
                .equals(binding.inputCfPassword.getText().toString())){
            binding.inputCfPassword.setError("Invalid input confirm password");
            isValid = false;
        }

        return isValid;
    }

    public boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}