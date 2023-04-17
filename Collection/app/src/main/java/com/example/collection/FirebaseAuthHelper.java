package com.example.collection;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseAuthHelper {

    public static void signUp(String email, String password
                                , OnCompleteListener<AuthResult> listener){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(listener)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            FireStoreHelper.addUser(task.getResult().getUser().getUid(),
                                    task.getResult().getUser().getEmail());
                        }
                    }
                });
    }

    public static void signIn(String email, String password
            , OnCompleteListener<AuthResult> listener){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(listener);
    }

    public static void changePassword(String oldPassword, String newPassword
            , OnCompleteListener<Void> listener){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        AuthCredential credential = EmailAuthProvider
                .getCredential(firebaseUser.getEmail(), oldPassword);
        // Prompt the user to re-provide their sign-in credentials
        firebaseUser.reauthenticate(credential).addOnCompleteListener(
                new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    firebaseUser.updatePassword(newPassword)
                            .addOnCompleteListener(listener);
                } else {
                    listener.onComplete(task);
                }
            }
        });
    }

    public static void forgotPassword(String email, OnCompleteListener<Void> listener){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(listener);
    }

    public static void logout(){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        firebaseAuth.signOut();
    }

    public static FirebaseUser getCurrentUser(){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        return firebaseUser;
    }
}
