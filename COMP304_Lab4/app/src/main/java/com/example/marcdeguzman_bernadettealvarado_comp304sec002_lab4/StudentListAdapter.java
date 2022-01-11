package com.example.marcdeguzman_bernadettealvarado_comp304sec002_lab4;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.MyViewHolder> {

    private Context context;
    //private LiveData<List<Student>> studentList;
    private List<Student> students = new ArrayList<>();

    // professor id
    private int professorID;

    public StudentListAdapter(Context context, int profId) {
        //initialize the context
        this.context = context;

        this.professorID = profId;
    }


    @NonNull
    @Override
    public StudentListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyler_row, parent, false); //inflate layout created for recyclerview

        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {

        /*int size = studentList.getValue().size();
        return size;     //returns size of the studentlist*/

        //return studentList == null ? 0 : studentList.size();
        return students.size();
    }

    @Override
    public void onBindViewHolder(@NonNull StudentListAdapter.MyViewHolder holder, int position) {

        Student currentStudent = students.get(position);
        holder.tvStudentId.setText(String.valueOf(currentStudent.getStudentId()));
        holder.tvFirstName.setText(currentStudent.getFirstName());
        holder.tvLastName.setText(currentStudent.getLastName());
        holder.tvDepartment.setText(currentStudent.getDepartment());
        holder.tvProfessor.setText(String.valueOf(currentStudent.getProfessorId()));
        //holder.tvClassroom.setText(currentStudent.getClassroom());

        holder.setListeners(position);
    }

    public void setStudents(List<Student> students) {
        this.students = students;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        //TextView tvFirstName, tvLastName, tvDepartment, tvProfessor, tvClassroom, tvStudentId;
        TextView tvFirstName, tvLastName, tvDepartment, tvProfessor, tvStudentId;
        ImageView ivRowEdit, ivRowTest, ivRowClassroom;

        public MyViewHolder(View view) {
            super(view);

            tvStudentId = view.findViewById(R.id.tvStudentId);
            tvFirstName  = view.findViewById(R.id.tvFirstName);
            tvLastName  = view.findViewById(R.id.tvLastName);
            tvDepartment  = view.findViewById(R.id.tvDepartment);
            tvProfessor  = view.findViewById(R.id.tvProfessorId);
            //tvClassroom  = view.findViewById(R.id.tvClassroom);
            /*updateStudentBtn = view.findViewById(R.id.updateStudentBtn);
            addTestBtn = view.findViewById(R.id.addTestBtn);
            viewTestStudentBtn = view.findViewById(R.id.viewTestBtn);*/
            ivRowEdit = view.findViewById(R.id.ivRowEdit);
            ivRowTest = view.findViewById(R.id.ivRowTest);
            ivRowClassroom = view.findViewById(R.id.ivRowClassroom);
        }

        public void setListeners(int position) {
            Student currentStudent = students.get(position);
            String studentString = currentStudent.getStudentId() + ": " + currentStudent.getFirstName() + " " + currentStudent.getLastName();
            int studentId = currentStudent.getStudentId();
            int professorId = currentStudent.getProfessorId();
            String firstName = currentStudent.getFirstName();
            String lastName = currentStudent.getLastName();
            String department = currentStudent.getDepartment();
            String studentFullName = currentStudent.getFirstName() + " " + currentStudent.getLastName();
            String classroom = currentStudent.getClassroom();

            ivRowEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, UpdateStudentActivity.class);
                    intent.putExtra("studentId", studentId);
                    intent.putExtra("firstName", firstName);
                    intent.putExtra("lastName", lastName);
                    intent.putExtra("department", department);
                    intent.putExtra("professorId", professorId);
                    intent.putExtra("classroom", classroom);

                    Log.d("StudentList.updateStBtn", "update student: " + studentString);

                    // start the update student activity
                    ((Activity)context).startActivityForResult(intent, StudentActivity.UPDATE_STUDENT_TEST);

                }
            });

            ivRowTest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, StudentTestActivity.class);
                    intent.putExtra("studentFullName", studentFullName);
                    intent.putExtra("studentId", studentId);
                    intent.putExtra("professorID", professorID);
                    intent.putExtra("classroom", classroom);

                    Log.d("StudentList.addTestBtn", "add test for student: " + studentString);

                    // start the add student test activity
                    ((Activity)context).startActivityForResult(intent, StudentActivity.ADD_STUDENT_TEST_REQUEST);
                }
            });

            ivRowClassroom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("StudentList.viewTestBtn", "view test for student: " + studentString);
                    Intent intent = new Intent(context, ViewClassroomInfo.class);

                    intent.putExtra("studentFullName", studentFullName);
                    intent.putExtra("studentId", studentId);
                    intent.putExtra("professorID", professorID);
                    intent.putExtra("classroom", classroom);

                    // start the view student test activity
                    ((Activity)context).startActivityForResult(intent, StudentActivity.VIEW_STUDENT_TEST);
                }
            });
        }
    }
}
