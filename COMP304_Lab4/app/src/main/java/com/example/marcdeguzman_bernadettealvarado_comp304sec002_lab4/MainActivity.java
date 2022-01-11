package com.example.marcdeguzman_bernadettealvarado_comp304sec002_lab4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private Button goToLoginBtn;
    private SchoolViewModel schoolViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Mobile App - Lab 4");

        goToLoginBtn = findViewById(R.id.goToLoginBtn);

        schoolViewModel = new ViewModelProvider(this).get(SchoolViewModel.class);

        populateProfessorData();
        populateStudentData();
    }

    private void populateProfessorData() {
        // schoolViewModel.deleteAll();
        schoolViewModel.insert(new Professor(1, "Jonah", "Simms", "Software Engineering", "12345"));
        schoolViewModel.insert(new Professor(2, "Amy", "Sosa", "Software Engineering", "12345"));
        schoolViewModel.insert(new Professor(3, "Glenn", "Sturgis", "Hospitality Management", "12345"));
    }

    private void populateStudentData() {
        schoolViewModel.insert(new Student(1, "Marc", "De Guzman", "Software Engineering", 1, "CLS404"));
        schoolViewModel.insert(new Student(2, "Bernadette", "Alvarado", "Software Engineering", 2, "CLS505"));
        schoolViewModel.insert(new Student(3, "John", "Doe", "Hospitality Management", 3, "CLS101"));
    }

    public void goToLogin(View view) {
        // go to login activity
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);

        startActivity(intent);
    }
}