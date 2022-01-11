package com.example.marcdeguzman_comp304sec002_lab1_ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // defined event for Send Message button
    public void send_message(View view) {
        // checking whether the send message button is clicked or not
        if (view.getId() == R.id.sendButton) {
            Intent intent = new Intent(MainActivity.this, SubActivity.class);

            // initializing an edit text object
            editText = findViewById(R.id.editText1);

            // getting an input value from edit text
            String message = editText.getText().toString();

            // store a value in an intent object using putExtra()
            // intent.putExtra("Message", message); // key and value pair

            /* creating shared preference */
            // define a preference object
            SharedPreferences sharedPreference = getSharedPreferences("mySharedPreference", 0);

            // define shared preference editor object
            SharedPreferences.Editor sharedPreferenceEditor = sharedPreference.edit();

            // put values in the shared preference editor
            sharedPreferenceEditor.putString("textMesssage", message);
            sharedPreferenceEditor.commit();

            /* end create shared preference */

            // moving to the sub activity
            startActivity(intent);
        }
    }
}