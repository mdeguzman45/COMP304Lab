package com.example.marcdeguzman_bernadettealvarado_comp304sec002_lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class GreekActivity extends AppCompatActivity {

    ListView listView;
    ImageView restaurantLogo;
    Button mapsButton;
    String restaurant;
    TextView nameTextView;
    TextView locTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greek);

        setTitle("Greek Cuisine");

        listView = findViewById(R.id.listview);
        restaurantLogo = findViewById(R.id.restaurantImageView);
        mapsButton = findViewById(R.id.mapsButton);
        nameTextView = findViewById(R.id.nameTextView);
        locTextView = findViewById(R.id.locTextView);

        //contents of the listview
        String[] values = new String[] {
                "Messini", "Jimmy the Greek", "Athena"
        };

        //setting up and using an array adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0)
                {
                    restaurant = "messini";
                    Toast.makeText(getApplicationContext(),"Messini",Toast.LENGTH_SHORT).show();
                    restaurantLogo.setImageResource(R.drawable.messini);
                    nameTextView.setText("Messini");
                    locTextView.setText("445 Danforth Ave, Toronto, ON M4K 1P2");

                }
                else if (position == 1)
                {
                    restaurant = "jimmy";
                    Toast.makeText(getApplicationContext(),"Jimmy the Greek",Toast.LENGTH_SHORT).show();
                    restaurantLogo.setImageResource(R.drawable.jimmy);
                    nameTextView.setText("Jimmy the Greek");
                    locTextView.setText("555 University Ave, Suite #M416, Toronto, ON M5G 1X8");
                }
                else if (position == 2)
                {
                    restaurant = "athena";
                    Toast.makeText(getApplicationContext(),"Athena",Toast.LENGTH_SHORT).show();
                    restaurantLogo.setImageResource(R.drawable.athena);
                    nameTextView.setText("Athena");
                    locTextView.setText("707 Danforth Ave, Toronto, ON M4J 1L2");
                }
            }
        });

        mapsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (restaurant == "messini")
                {
                    //Intent to start messini map activity
                    Intent mapsButtonIntent = new Intent(GreekActivity.this, MessiniActivity.class);
                    startActivity(mapsButtonIntent);
                }
                else if (restaurant == "jimmy")
                {
                    //Intent to start jimmy map activity
                    Intent mapsButtonIntent = new Intent(GreekActivity.this, JimmyActivity.class);
                    startActivity(mapsButtonIntent);
                }
                else if (restaurant == "athena")
                {
                    //Intent to start athena map activity
                    Intent mapsButtonIntent = new Intent(GreekActivity.this, AthenaActivity.class);
                    startActivity(mapsButtonIntent);
                }

            }
        });
    }
}