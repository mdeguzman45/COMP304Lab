package com.example.marcdeguzman_bernadettealvarado_comp304sec002_lab4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class UpdateStudentActivity extends AppCompatActivity {

    public static final String EXTRA_STUDENTID =
            "com.example.marcdeguzman_bernadettealvarado_comp304sec002_lab4.EXTRA_STUDENTID";
    public static final String EXTRA_FIRSTNAME =
            "com.example.marcdeguzman_bernadettealvarado_comp304sec002_lab4.EXTRA_FIRSTNAME";
    public static final String EXTRA_LASTNAME =
            "com.example.marcdeguzman_bernadettealvarado_comp304sec002_lab4.EXTRA_LASTNAME";
    public static final String EXTRA_DEPARTMENT =
            "com.example.marcdeguzman_bernadettealvarado_comp304sec002_lab4.EXTRA_DEPARTMENT";
    public static final String EXTRA_PROFESSORID =
            "com.example.marcdeguzman_bernadettealvarado_comp304sec002_lab4.EXTRA_PROFESSORID";
    public static final String EXTRA_CLASSROOM =
            "com.example.marcdeguzman_bernadettealvarado_comp304sec002_lab4.EXTRA_CLASSROOM";

    private EditText studentIdInput;
    private EditText firstNameInput;
    private EditText lastNameInput;
    private EditText departmentInput;
    private EditText professorIdInput;
    //private EditText classroomInput;

    private Bundle bundle;

    private int studentId, professorId;
    private String firstName, lastName, department;

    // professor details
    private int professorID;
    private String professorFName;
    private String professorLName;

    SchoolViewModel studentModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);

        // close button
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Update Student");

        // get shared preference data of professor
        SharedPreferences sharedPreference = getSharedPreferences("professorInfo", MODE_PRIVATE);

        professorID = sharedPreference.getInt("professorID", 1); // default to 1 for now
        professorFName = sharedPreference.getString("professorFName", "");
        professorLName = sharedPreference.getString("professorLName", "");

        Log.d("AddNewStudentActivity", "professor ID: " + professorID);
        Log.d("AddNewStudentActivity", "professor first name: " + professorFName);
        Log.d("AddNewStudentActivity", "professor last name: " + professorLName);

        String professorFullName = professorFName + " " + professorLName;
        //professorIdInput.setText(professorFullName);

        /* end access shared preference */

        studentIdInput = findViewById(R.id.studentIdInput);
        firstNameInput = findViewById(R.id.firstNameInput);
        lastNameInput = findViewById(R.id.lastNameInput);
        departmentInput = findViewById(R.id.departmentInput);
        professorIdInput = findViewById(R.id.professorIdInput);
        //classroomInput = findViewById(R.id.classroomInput);

        bundle = getIntent().getExtras();

        if (bundle != null) {
            // fetch studentId from bundle

            studentId = bundle.getInt("studentId");
            firstName = bundle.getString("firstName");
            lastName = bundle.getString("lastName");
            department = bundle.getString("department");
            professorId = bundle.getInt("professorId");
            //classroom = bundle.getString("classroom");

            studentIdInput.setText(String.valueOf(studentId));
            firstNameInput.setText(firstName);
            lastNameInput.setText(lastName);
            departmentInput.setText(department);
            professorIdInput.setText(professorFullName);
            //classroomInput.setText(classroom);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        // pass the menu created
        menuInflater.inflate(R.menu.add_student_menu, menu);
        // return true to display menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_student:
                saveStudent();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveStudent() {
        // convert studentId from integer to string before passing as an argument
        String studentId = studentIdInput.getText().toString();
        int studentIdInt = Integer.parseInt(studentId);

        String firstName = firstNameInput.getText().toString();
        String lastName = lastNameInput.getText().toString();
        String department = departmentInput.getText().toString();
        //String classroom = classroomInput.getText().toString();

/*        // get spinner from classroom
        Spinner classroomSpinner = (Spinner) findViewById(R.id.classRoomSpinner);
        String classroom = classroomSpinner.getSelectedItem().toString();*/

        // check if student id and professor id is not empty before proceeding to saveNewStudent method
        if (!studentId.trim().isEmpty()) {// call saveNewStudent method

            // send data back to Student Activity
            Intent data = new Intent();
            data.putExtra(EXTRA_STUDENTID, studentIdInt);
            data.putExtra(EXTRA_FIRSTNAME, firstName);
            data.putExtra(EXTRA_LASTNAME, lastName);
            data.putExtra(EXTRA_DEPARTMENT, department);
            // data.putExtra(EXTRA_PROFESSORID, professorIdInt);
            Log.d("UpdateSt.saveStudent", "professor ID: " + professorID);
            //Log.d("UpdateSt.saveStudent", "classroom id: " + classroom);
            data.putExtra(EXTRA_PROFESSORID, professorID);
            // setting default value of classroom to CL301 so the required entity fields will
            // still have classroom as per requirement
            data.putExtra(EXTRA_CLASSROOM, "CL301");

            // setResult to indicate if input was successful or not
            setResult(RESULT_OK, data);
            finish();
        } else {

            // String errorMessage = "Please insert a Student ID and Professor ID";
            String errorMessage = "Please insert a Student ID";
            Toast.makeText(UpdateStudentActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
        }
    }
}