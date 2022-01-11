package com.example.marcdeguzman_comp304sec002_lab1_ex2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class VRActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vractivity);

        // set text view
        TextView textView = findViewById(R.id.VROnCreatedText);
        textView.setText(getString(R.string.activityOnCreateText));
    }

    @Override
    protected void onStart() {
        super.onStart();

        // set text view
        TextView textView = findViewById(R.id.VROnStartText);
        textView.setText(getString(R.string.activityOnStartText));
    }

    @Override
    protected void onStop() {
        super.onStop();

        // set text view
        TextView textView = findViewById(R.id.VROnStopText);
        textView.setText(getString(R.string.activityOnStopText));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // set text view
        TextView textView = findViewById(R.id.VROnDestroyText);
        textView.setText(getString(R.string.activityOnDestroyText));
    }
}