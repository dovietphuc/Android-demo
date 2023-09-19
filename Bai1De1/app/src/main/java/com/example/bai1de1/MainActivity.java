package com.example.bai1de1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivityViewModel viewModel =
                new ViewModelProvider(this).get(MainActivityViewModel.class);

        ViewPagerAdapter adapter = new ViewPagerAdapter(this);

        ViewPager2 viewPager = (ViewPager2) findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    if(position == 0) {
                        tab.setText("Login");
                    } else if(position == 1) {
                        tab.setText("Register");
                    }
                }
        ).attach();

        viewModel.currentPage.observe(this, data -> {
            viewPager.setCurrentItem(data);
        });

    }
}