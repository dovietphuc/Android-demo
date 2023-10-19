package com.example.filemanager;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.R;

import java.io.File;
import java.util.List;

public class ListFileFragment extends Fragment {

    public int level = 0;

    ListFileAdapter adapter = new ListFileAdapter();

    public void setFiles(List<File> files) {
        adapter.files = files;
        adapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_file, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MainActivityViewModel viewModel =
                new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rcv_files);
        recyclerView.setAdapter(adapter);

        adapter.listener = (v, position) -> {
            ListFileFragment fragment = new ListFileFragment();
            fragment.level++;
            viewModel.getFiles(adapter.files.get(position))
                    .observe(getViewLifecycleOwner(), files -> {
                        fragment.setFiles(files);
                    });
            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_host,
                            fragment, "sub_files_" + fragment.level)
                    .addToBackStack("stack_files_" + fragment.level)
                    .commit();
        };
    }
}