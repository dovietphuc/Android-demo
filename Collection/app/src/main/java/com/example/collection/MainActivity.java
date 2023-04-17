package com.example.collection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.collection.model.Image;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    MainActivityViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        FirebaseUser firebaseUser = FirebaseAuthHelper.getCurrentUser();
        if (firebaseUser == null) {
            getMenuInflater().inflate(R.menu.no_auth_main_menu, menu);
        } else {
            getMenuInflater().inflate(R.menu.auth_main_menu, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        NavController navController = Navigation.findNavController(this, R.id.nav_host);
        switch (item.getItemId()) {
            case R.id.action_login:
                navController.navigate(R.id.loginFragment);
                return true;
            case R.id.action_logout:
                FirebaseAuthHelper.logout();
                Toast.makeText(this,
                        "Sign out",
                        Toast.LENGTH_SHORT).show();
                invalidateOptionsMenu();
                return true;
            case R.id.action_change_password:
                navController.navigate(R.id.changePasswordFragment);
                return true;
            case R.id.action_sync:
                if(mViewModel.getAllImage().getValue() != null) {
                    for (Image image : mViewModel.getAllImage().getValue()) {
                        FireStoreHelper.addImageInfo(image);
                    }
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}