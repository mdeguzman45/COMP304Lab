package com.example.marcdeguzman_bernadettealvarado_comp304sec002_lab4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Arrays;
import java.util.List;

public class StudentActivity extends AppCompatActivity {

    public static final int ADD_STUDENT_REQUEST = 1;
    public static final int ADD_STUDENT_TEST_REQUEST = 2;
    public static final int VIEW_STUDENT_TEST = 3;
    public static final int UPDATE_STUDENT_TEST = 4;

    private StudentListAdapter studentListAdapter;
    private SchoolViewModel schoolViewModel;

    private int professorID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        // back button
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        setTitle("Student Record");

        FloatingActionButton buttonAddStudent = findViewById(R.id.button_add_student);
        buttonAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentActivity.this, AddNewStudentActivity.class);
                // use startActivityForResult instead of StartActivity to get input back
                // ADD_STUDENT_REQUEST is hard-coded as 1
                startActivityForResult(intent, ADD_STUDENT_REQUEST);
            }
        });

        // get shared preference data of professor
        SharedPreferences sharedPreference = getSharedPreferences("professorInfo", MODE_PRIVATE);

        professorID = sharedPreference.getInt("professorID", 1); // default to 1 for now

        // initialize recyclerview
        initRecyclerView();
    }

    private void initRecyclerView() {
        // retrieve recyclerview from layout
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // separator in the recyclerview
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        studentListAdapter = new StudentListAdapter(this, professorID);
        // set adapter to recyclerView
        recyclerView.setAdapter(studentListAdapter);

        schoolViewModel = new ViewModelProvider(this).get(SchoolViewModel.class);
        schoolViewModel.getAllStudent().observe(this, new Observer<List<Student>>() {
            @Override
            public void onChanged(@Nullable List<Student> students) {
                studentListAdapter.setStudents(students);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_STUDENT_REQUEST) {
            handleAddStudentResult(resultCode, data);
        }

        if (requestCode == ADD_STUDENT_TEST_REQUEST) {
            handleAddStudentTestResult(resultCode, data);
        }

        if (requestCode == UPDATE_STUDENT_TEST) {
            handleUpdateStudentResult(resultCode, data);
        }
    }

    private void handleUpdateStudentResult(int resultCode, Intent data) {
        // resultCode == RESULT_OK from the saveStudent method of UpdateStudentActivity.java
        if (resultCode == RESULT_OK) {
            int studentId = data.getIntExtra(UpdateStudentActivity.EXTRA_STUDENTID, 1);
            String firstName = data.getStringExtra(UpdateStudentActivity.EXTRA_FIRSTNAME);
            String lastName = data.getStringExtra(UpdateStudentActivity.EXTRA_LASTNAME);
            String department = data.getStringExtra(UpdateStudentActivity.EXTRA_DEPARTMENT);
            int professorId = data.getIntExtra(UpdateStudentActivity.EXTRA_PROFESSORID, 1);
            String classroom = data.getStringExtra(UpdateStudentActivity.EXTRA_CLASSROOM);

            Student student = new Student(studentId, firstName, lastName, department, professorId, classroom);
            schoolViewModel.update(student);

            Toast.makeText(this, "Student Saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Student Not Saved", Toast.LENGTH_SHORT).show();
        }
    }

    private void handleAddStudentTestResult(int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {

            // handle the test insert
            int studentId = data.getIntExtra("addTestStudentID", 1); // lets default to 1
            int professorId = data.getIntExtra("addTestProfessorID", 1); // lets default to 1
            String classroomId = data.getStringExtra("addTestClassroom");
            String floor = data.getStringExtra("addFloor");
            String aircondition = data.getStringExtra("addAircon");
            //int testGradeInt = Integer.parseInt(testGrade);

            Log.d("Student.AddStudentTest", "student id: " + studentId);
            Log.d("Student.AddStudentTest", "professor id: " + professorId);
            Log.d("Student.AddStudentTest", "classroom: " + classroomId);
            Log.d("Student.AddStudentTest", "floor: " + floor);
            Log.d("Student.AddStudentTest", "aircondition: " + aircondition);

            // get classroom info for insert
//            String[] classRoomIdArray = getResources().getStringArray(R.array.classRoomID);
//            int classRoomIndexOnList = Arrays.asList(classRoomIdArray).indexOf(classroomId);
//
//            if (classRoomIndexOnList < 0) {
//                // default to 0 for now to go thru the mapping of other classroom info
//                classRoomIndexOnList = 0;
//            }
//
//            String[] classRoomArrayFloorInfo = getResources().getStringArray(R.array.classRoomFloor);
//            String[] classRoomArrayVentInfo = getResources().getStringArray(R.array.classRoomAirConditioned);
//
//            String classroomFloor = classRoomArrayFloorInfo[classRoomIndexOnList];
//            String classroomAirConditioned = classRoomArrayVentInfo[classRoomIndexOnList];
//
//            Log.d("Student.AddStudentTest", "classroom floor: " + classroomFloor);
//            Log.d("Student.AddStudentTest", "classroom airConditioned: " + classroomAirConditioned);
//            Log.d("Student.AddStudentTest", "classroom index in array: " + classRoomIndexOnList);

            Classroom classroom = new Classroom(classroomId, studentId, professorId, floor, aircondition);
            schoolViewModel.insert(classroom);

            Toast.makeText(this, "Student Classroom Saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Student Classroom Saved", Toast.LENGTH_SHORT).show();
        }
    }

    private void handleAddStudentResult(int resultCode, Intent data) {
        // resultCode == RESULT_OK from the saveStudent method of AddNewStudentActivity.java
        if (resultCode == RESULT_OK) {
            int studentId = data.getIntExtra(AddNewStudentActivity.EXTRA_STUDENTID, 1);
            String firstName = data.getStringExtra(AddNewStudentActivity.EXTRA_FIRSTNAME);
            String lastName = data.getStringExtra(AddNewStudentActivity.EXTRA_LASTNAME);
            String department = data.getStringExtra(AddNewStudentActivity.EXTRA_DEPARTMENT);
            int professorId = data.getIntExtra(AddNewStudentActivity.EXTRA_PROFESSORID, 1);
            String classroom = data.getStringExtra(AddNewStudentActivity.EXTRA_CLASSROOM);

            Student student = new Student(studentId, firstName, lastName, department, professorId, classroom);
            schoolViewModel.insert(student);

            Toast.makeText(this, "Student Saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Student Not Saved", Toast.LENGTH_SHORT).show();
        }
    }
}