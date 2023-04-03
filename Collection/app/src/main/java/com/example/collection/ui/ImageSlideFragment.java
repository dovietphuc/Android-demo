package com.example.collection.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.collection.MainActivityViewModel;
import com.example.collection.R;
import com.example.collection.adapter.ImageAdapter;
import com.example.collection.model.Image;

import java.util.List;

public class ImageSlideFragment extends Fragment {
    public static final String EXTRA_START_PAGE = "com.example.collection.extras.START_PAGE";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image_slide, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int startPage = requireArguments().getInt(EXTRA_START_PAGE, 0);

        MainActivityViewModel viewModel = new ViewModelProvider(requireActivity())
                .get(MainActivityViewModel.class);

        ViewPager2 viewPager2 = view.findViewById(R.id.viewPage_image);
        ImageAdapter adapter = new ImageAdapter();
        adapter.setItemType(ImageAdapter.TYPE_PAGE_ITEM);
        viewPager2.setAdapter(adapter);

        viewModel.getAllImage().observe(getViewLifecycleOwner(), new Observer<List<Image>>() {
            @Override
            public void onChanged(List<Image> images) {
                adapter.setData(images);
                viewPager2.setCurrentItem(startPage, false);
            }
        });
    }
}