package com.example.marcdeguzman_bernadettealvarado_comp304sec002_lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Restaurant Map");

        listView = findViewById(R.id.listview);

        //contents of the listview
        String[] values = new String[] {
                "Italian", "Greek", "Filipino"
        };

        //setting up and using an array adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0)
                {
                    Intent italian_intent = new Intent(view.getContext(), ItalianActivity.class);
                    startActivity(italian_intent);
                    Toast.makeText(getApplicationContext(),"Italian",Toast.LENGTH_SHORT).show();
                }
                else if (position == 1)
                {
                    Intent greek_intent = new Intent(view.getContext(), GreekActivity.class);
                    startActivity(greek_intent);
                    Toast.makeText(getApplicationContext(),"Greek",Toast.LENGTH_SHORT).show();
                }
                else if (position == 2)
                {
                    Intent filipino_intent = new Intent(view.getContext(), FilipinoActivity.class);
                    startActivity(filipino_intent);
                    Toast.makeText(getApplicationContext(),"Filipino",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}