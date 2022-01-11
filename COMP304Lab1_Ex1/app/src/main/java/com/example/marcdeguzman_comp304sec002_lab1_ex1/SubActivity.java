package com.example.marcdeguzman_comp304sec002_lab1_ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        // define an intent object to get a value
        Intent i = getIntent();

        // Get value from an intent object
        // String message = i.getStringExtra("Message");

        /* accessing shared preference */
        // define a preference object
        SharedPreferences sharedPreference = getSharedPreferences("mySharedPreference", MODE_PRIVATE);
        String message = sharedPreference.getString("textMesssage", "");

        /* end access shared preference */

        // define a text view java reference object
        TextView textView = findViewById(R.id.textView1);

        textView.setText(message);
    }
}