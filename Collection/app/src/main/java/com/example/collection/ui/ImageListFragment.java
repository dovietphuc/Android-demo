package com.example.collection.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.collection.MainActivityViewModel;
import com.example.collection.R;
import com.example.collection.adapter.ImageAdapter;
import com.example.collection.model.Image;

import java.util.List;

public class ImageListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MainActivityViewModel viewModel = new ViewModelProvider(requireActivity())
                .get(MainActivityViewModel.class);

        RecyclerView recyclerView = view.findViewById(R.id.rcv_image);
        ImageAdapter adapter = new ImageAdapter();
        recyclerView.setAdapter(adapter);

        viewModel.getAllImage().observe(getViewLifecycleOwner(), new Observer<List<Image>>() {
            @Override
            public void onChanged(List<Image> images) {
                adapter.setData(images);
            }
        });

        NavController navController =
                Navigation.findNavController(requireActivity(), R.id.nav_host);
        adapter.setOnItemClickListener(new ImageAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, Image image, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt(ImageSlideFragment.EXTRA_START_PAGE, position);
                navController
                        .navigate(R.id.action_imageListFragment_to_imageSlideFragment, bundle);
            }
        });
    }
}