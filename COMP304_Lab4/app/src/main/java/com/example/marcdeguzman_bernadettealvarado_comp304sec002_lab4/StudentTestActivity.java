package com.example.marcdeguzman_bernadettealvarado_comp304sec002_lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class StudentTestActivity extends AppCompatActivity {

    String studentFullName;
    String classroom;
    int studentId;
    int professorId;

    TextView studentFullNameTextView;
    //EditText studentClassroomTextView;

    //EditText testNameInput;
    //EditText testGradeInput;
    EditText floorInput;
    EditText airconInput;
    EditText classroomIdInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_test);

        setTitle("Add Classroom");
        // define an intent object to get the necessary data
        Intent intent = getIntent();

        studentFullName = intent.getStringExtra("studentFullName");
        classroom = intent.getStringExtra("classroom");
        studentId = intent.getIntExtra("studentId", 1); // default to 1 for now
        professorId = intent.getIntExtra("professorID", 1); // default to 1 for now

        studentFullNameTextView = findViewById(R.id.addStudentTestFullNameTextView);
        studentFullNameTextView.setText(studentFullName);

        //testNameInput = findViewById(R.id.addStudentTestNameEditView);
        //testGradeInput = findViewById(R.id.addStudentTestGradeEditView);

        floorInput = findViewById(R.id.addFloorEditView);
        airconInput = findViewById(R.id.addAirconditionEditView);
        classroomIdInput = findViewById(R.id.classroomIdEditText);
    }

    public void addStudentTest(View view) {
        //String testName = testNameInput.getText().toString();
        //String testGrade = testGradeInput.getText().toString();
        String floor = floorInput.getText().toString();
        String aircon = airconInput.getText().toString();
        String classroomId = classroomIdInput.getText().toString();
        String errorMessage = "";

/*        if (testName.trim().isEmpty()) {
            errorMessage = "Please enter a test name";
            Toast.makeText(StudentTestActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            return;
        }

        if (testGrade.trim().isEmpty()) {
            errorMessage = "Please enter test grade";
            Toast.makeText(StudentTestActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            return;
        }*/

        if (classroomId.trim().isEmpty()) {
            errorMessage = "Please enter classroom ID.";
            Toast.makeText(StudentTestActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            return;
        }

        if (floor.trim().isEmpty()) {
            errorMessage = "Please enter floor.";
            Toast.makeText(StudentTestActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            return;
        }

        if (aircon.trim().isEmpty()) {
            errorMessage = "Please enter airconditioned.";
            Toast.makeText(StudentTestActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            return;
        }

        // send data back to Student Activity
        Intent data = new Intent();
        data.putExtra("addTestStudentID", studentId);
        data.putExtra("addTestProfessorID", professorId);
        data.putExtra("addTestClassroom", classroomId);
        //data.putExtra("addTestName", testName);
        //data.putExtra("addTestGrade", testGrade);
        data.putExtra("addFloor", floor);
        data.putExtra("addAircon", aircon);

        // setResult to indicate if input was successful or not
        setResult(RESULT_OK, data);
        finish();
    }

    public void cancelAddTest(View view) {
        // setResult to indicate if input was successful or not
        setResult(RESULT_CANCELED);
        finish();
    }
}