package com.example.marcdeguzman_bernadettealvarado_comp304sec002_lab4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class ViewClassroomInfo extends AppCompatActivity {
    String studentFullName;
    String classroom;
    int studentId;
    int professorId;

    TextView studentNameHeader;
    TextView testCountHeader;

    private SchoolViewModel schoolViewModel;
    private ClassroomListAdapter classroomListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_classroom_info);

        // back button
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        setTitle("View Classroom Info");
        // define an intent object to get the necessary data
        Intent intent = getIntent();

        studentFullName = intent.getStringExtra("studentFullName");
        classroom = intent.getStringExtra("classroom");
        studentId = intent.getIntExtra("studentId", 1); // default to 1 for now
        professorId = intent.getIntExtra("professorID", 1); // default to 1 for now

        studentNameHeader = findViewById(R.id.viewTestStudentNameTextView);
        String studentTestViewText = studentFullName + "'s Classroom Information";
        studentNameHeader.setText(studentTestViewText);

        testCountHeader = findViewById(R.id.testCountTextView);

        RecyclerView testListRecylerView = findViewById(R.id.testListRecylerView);
        testListRecylerView.setLayoutManager(new LinearLayoutManager(this));

        // separator in the recyclerview
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        testListRecylerView.addItemDecoration(dividerItemDecoration);

        classroomListAdapter = new ClassroomListAdapter(this);
        // set recyclerview adapter
        testListRecylerView.setAdapter(classroomListAdapter);

        schoolViewModel = new ViewModelProvider(this).get(SchoolViewModel.class);
        schoolViewModel.getClassroomTestList().observe(this, new Observer<List<Classroom>>() {
            @Override
            public void onChanged(@Nullable List<Classroom> classrooms) {
                Log.d("ViewClassroomInfo", "returned classroom list: " + classrooms.size());

                if (classrooms.size() > 0) {
                    String testCountString = "Classroom Count: " + classrooms.size();
                    testCountHeader.setText(testCountString);
                }

                classroomListAdapter.setClassrooms(classrooms);

                for(int i = 0; i < classrooms.size(); i++) {
                    Classroom classroom = classrooms.get(i);

                    Log.d("ViewClassroomInfo", "student id " + classroom.getStudentId());
                    Log.d("ViewClassroomInfo", "professor id: " + classroom.getProfessorId());
                    Log.d("ViewClassroomInfo", "classroom id: " + classroom.getClassroomId());
                    Log.d("ViewClassroomInfo", "floor: " + classroom.getFloor());
                    Log.d("ViewClassroomInfo", "air conditioned : " + classroom.getAirConditioned());
//                    Log.d("ViewClassroomInfo", "test name: " + classroom.getTestName());
//                    Log.d("ViewClassroomInfo", "test grade: " + classroom.getTestGrade());
                }
            }
        });

        // schoolViewModel.queryClassroomTestList(studentId, professorId, classroom);
        schoolViewModel.queryClassroomTestList(studentId, professorId);
    }

    public void backToStudentList(View view) {
        finish();
    }
}

