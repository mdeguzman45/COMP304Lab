package com.example.marcdeguzman_comp304sec002_lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // defined event for order button
    public void goToPizzaSelection(View view) {
        Intent intent = new Intent(MainActivity.this, Pizza_Selection.class);

        // lets go to the pizza selection
        startActivity(intent);
    }
}