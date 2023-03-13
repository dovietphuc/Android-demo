package com.example.demoroomdatabase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.StudentViewHolder> {
    private List<Student> mStudents;

    public StudentListAdapter(){
        mStudents = new ArrayList<>();
    }

    public void setStudents(List<Student> students){
        mStudents = students;
        notifyDataSetChanged();
    }

    public List<Student> getStudents(){
        return mStudents;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StudentViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.student_list_item,
                                parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        holder.bind(mStudents.get(position));
    }

    @Override
    public int getItemCount() {
        return mStudents.size();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder{

        TextView mTxtName;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            mTxtName = itemView.findViewById(R.id.txt_student_name);
        }

        public void bind(Student student){
            mTxtName.setText(student.getUid() + " - " + student.getName());
        }
    }
}
