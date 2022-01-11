package com.example.marcdeguzman_comp304sec002_lab1_ex2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class AIActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aiactivity);

        // set text view
        TextView textView = findViewById(R.id.AIOnCreatedText);
        textView.setText(getString(R.string.activityOnCreateText));
    }

    @Override
    protected void onStart() {
        super.onStart();

        // set text view
        TextView textView = findViewById(R.id.AIOnStartText);
        textView.setText(getString(R.string.activityOnStartText));
    }

    @Override
    protected void onStop() {
        super.onStop();

        // set text view
        TextView textView = findViewById(R.id.AIOnStopText);
        textView.setText(getString(R.string.activityOnStopText));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // set text view
        TextView textView = findViewById(R.id.AIOnDestroyText);
        textView.setText(getString(R.string.activityOnDestroyText));
    }
}