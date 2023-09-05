package com.example.demoperson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.example.demoperson.models.Person;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivityViewModel viewModel =
                new ViewModelProvider(this).get(MainActivityViewModel.class);

        for (Person person : viewModel.getPersons("i")) {
            Log.d("PhucDVb", "onCreate: " + person.getName());
        }
    }
}