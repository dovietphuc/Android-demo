package com.example.demoroomdatabase;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ListStudentFragment extends Fragment {
    public static final String ACTION_ADD_STUDENT = "com.example.demoroomdatabase.action.ACTION_ADD_STUDENT";
    public static final String EXTRA_STUDENT_NAME = "com.example.demoroomdatabase.extras.EXTRA_STUDENT_NAME";
    public static final String EXTRA_STUDENT_CLASS_NAME = "com.example.demoroomdatabase.extras.EXTRA_STUDENT_CLASS_NAME";
    public static final String EXTRA_STUDENT_GENDER = "com.example.demoroomdatabase.extras.EXTRA_STUDENT_GENDER";

    private RecyclerView mRecyclerView;
    private StudentListAdapter mAdapter;
    ListStudentViewModel viewModel;

    BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(ACTION_ADD_STUDENT.equals(action)){
                String name = intent.getStringExtra(EXTRA_STUDENT_NAME);
                String className = intent.getStringExtra(EXTRA_STUDENT_CLASS_NAME);
                String gender = intent.getStringExtra(EXTRA_STUDENT_GENDER);

                Student student = new Student(true, name, gender, className);
                viewModel.insert(student);
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_student, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel =
                new ViewModelProvider(this).get(ListStudentViewModel.class);

        mRecyclerView = view.findViewById(R.id.list_student_view);
        mAdapter = new StudentListAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModel.getAllStudent().observe(getViewLifecycleOwner(), new Observer<List<Student>>() {
            @Override
            public void onChanged(List<Student> students) {
                mAdapter.setStudents(students);
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if(direction == ItemTouchHelper.LEFT){
                    Student student = mAdapter.getStudents().get(viewHolder.getAdapterPosition());
                    viewModel.delete(student);
                    Toast.makeText(getContext(), "Da xoa", Toast.LENGTH_SHORT).show();
                }
            }
        });

        itemTouchHelper.attachToRecyclerView(mRecyclerView);

        FloatingActionButton fab = view.findViewById(R.id.fab_add_student);

        NavController navController = Navigation.findNavController(requireActivity(), R.id.host_fragment);

        fab.setOnClickListener(v -> {
            navController.navigate(R.id.action_listStudentFragment_to_addStudentFragment);
        });

        requireActivity().registerReceiver(mReceiver, new IntentFilter(ACTION_ADD_STUDENT));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        requireActivity().unregisterReceiver(mReceiver);
    }
}