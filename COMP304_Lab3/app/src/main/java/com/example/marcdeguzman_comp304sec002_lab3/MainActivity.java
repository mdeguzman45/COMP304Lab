package com.example.marcdeguzman_comp304sec002_lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] taskViewItems = getResources().getStringArray(R.array.taskActivities);

        ListView taskListView = (ListView) findViewById(R.id.taskListView);

        // need array adapter to set items in listview
        ArrayAdapter<String> taskListViewAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                taskViewItems
        );

        taskListView.setAdapter(taskListViewAdapter);

        // add a click listener
        taskListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                switch (position) {
                    case 0:
                        intent = new Intent(MainActivity.this, Exercise1.class);
                        break;
                    case 1:
                        intent = new Intent(MainActivity.this, Exercise2.class);
                        break;
                    case 2:
                        intent = new Intent(MainActivity.this, Exercise3.class);
                        break;
                }
                startActivity(intent);
            }
        });
    }
}