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
import com.example.collection.databinding.FragmentChangePasswordBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class ChangePasswordFragment extends Fragment {
    FragmentChangePasswordBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentChangePasswordBinding.inflate(inflater);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.changePassword.setOnClickListener(v -> {
            String newPwd = mBinding.newPassword.getText().toString();
            String oldPwd = mBinding.oldPassword.getText().toString();
            String confirmNewPwd = mBinding.confirmNewPassword.getText().toString();
            if(newPwd.equals(confirmNewPwd)){
                mBinding.loading.setVisibility(View.VISIBLE);
                FirebaseAuthHelper.changePassword(oldPwd, newPwd, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        mBinding.loading.setVisibility(View.GONE);
                        if(task.isSuccessful()){
                            Toast.makeText(getContext(),
                                    "Change password successfully",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(),
                                    task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } else {
                Toast.makeText(getContext(),
                        "Invalid confirm new password", Toast.LENGTH_SHORT).show();
            }
        });
    }
}