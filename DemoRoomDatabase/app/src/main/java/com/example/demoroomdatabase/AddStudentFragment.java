package com.example.demoroomdatabase;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class AddStudentFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_student, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AddStudentViewModel viewModel = new ViewModelProvider(this).get(AddStudentViewModel.class);

        EditText edtName = view.findViewById(R.id.edt_name);
        EditText edtClass = view.findViewById(R.id.edt_class);
        RadioGroup genderGroup = view.findViewById(R.id.gender_group);

        Button btnSubmit = view.findViewById(R.id.btn_create);
        btnSubmit.setOnClickListener(v -> {
            Student student = new Student();

            student.setName(edtName.getText().toString());
            RadioButton checkedBtn = view.findViewById(genderGroup.getCheckedRadioButtonId());
            student.setGender(checkedBtn.getText().toString());
            student.setClassName(edtClass.getText().toString());
            student.setState(true);

            viewModel.addStudent(student);
        });
    }
}