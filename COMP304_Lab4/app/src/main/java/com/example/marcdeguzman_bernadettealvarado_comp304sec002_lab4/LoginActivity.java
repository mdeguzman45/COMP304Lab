package com.example.marcdeguzman_bernadettealvarado_comp304sec002_lab4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private SchoolViewModel schoolViewModel;
    private Button btnLogin;
    private EditText profIdEditview;
    private EditText profPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // back button
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        setTitle("Login");

        btnLogin = findViewById(R.id.loginBtn);
        profIdEditview = findViewById(R.id.profIdEditText);
        profPassword = findViewById(R.id.profPwordEditText);

        schoolViewModel = new ViewModelProvider(this).get(SchoolViewModel.class);

        schoolViewModel.getCurrentAuthenticatedProfessor().observe(this, new Observer<Professor>() {
            @Override
            public void onChanged(Professor professor) {
                btnLogin.setEnabled(true);
                if (professor != null) {
                    String welcomeText = "Welcome Professor " + professor.getFirstName() + " " + professor.getLastName();
                    Toast.makeText(LoginActivity.this, welcomeText, Toast.LENGTH_SHORT).show();

                    // create shared preference object for the Professor Information:
                    SharedPreferences sharedPreference = getSharedPreferences("professorInfo", 0);

                    // define shared preference editor object
                    SharedPreferences.Editor sharedPreferenceEditor = sharedPreference.edit();

                    // put values in the shared preference editor
                    sharedPreferenceEditor.putInt("professorID", professor.getProfessorId());
                    sharedPreferenceEditor.putString("professorFName", professor.getFirstName());
                    sharedPreferenceEditor.putString("professorLName", professor.getLastName());
                    sharedPreferenceEditor.commit();

                    /* End create shared preference */

                    //go to student activity
                    Intent intent = new Intent(getApplicationContext(), StudentActivity.class);
                    startActivity(intent);

                }
                else {
                    Toast.makeText(LoginActivity.this, "Invalid credentials. Please try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void authenticateProfessor(View view) {

        // Log.d("authenticateProfessor", "exception deleting professors" + );
        try {
            // parse the prof id as integer
            int profId = Integer.parseInt(profIdEditview.getText().toString());
            String pWord = profPassword.getText().toString();

             //Log.d("authenticateProfessor", "Password: " + pWord);
            schoolViewModel.authenticateProfessor(profId, pWord);

            // clear the password field
            profPassword.setText("");

            btnLogin.setEnabled(false);
        } catch (NumberFormatException ex) {
            Toast.makeText(LoginActivity.this, "Invalid Professor ID", Toast.LENGTH_SHORT).show();
            btnLogin.setEnabled(true);
        }
    }
}