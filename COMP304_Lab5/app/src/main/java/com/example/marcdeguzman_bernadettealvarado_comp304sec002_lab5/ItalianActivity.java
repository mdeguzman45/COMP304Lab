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

public class ItalianActivity extends AppCompatActivity {

    ListView listView;
    ImageView restaurantLogo;
    Button mapsButton;
    String restaurant;
    TextView nameTextView;
    TextView locTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_italian);

        setTitle("Italian Cuisine");

        listView = findViewById(R.id.listview);
        restaurantLogo = findViewById(R.id.restaurantImageView);
        mapsButton = findViewById(R.id.mapsButton);
        nameTextView = findViewById(R.id.nameTextView);
        locTextView = findViewById(R.id.locTextView);

        //contents of the listview
        String[] values = new String[] {
                "Cibo", "Panago Pizza", "Trattoria Nervosa"
        };

        //setting up and using an array adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0)
                {
                    restaurant = "cibo";
                    Toast.makeText(getApplicationContext(),"Cibo",Toast.LENGTH_SHORT).show();
                    restaurantLogo.setImageResource(R.drawable.cibo);
                    nameTextView.setText("Cibo");
                    locTextView.setText("133 Yorkville Ave, Toronto, ON M5R 1C4");

                }
                else if (position == 1)
                {
                    restaurant = "panago";
                    Toast.makeText(getApplicationContext(),"Panago Pizza",Toast.LENGTH_SHORT).show();
                    restaurantLogo.setImageResource(R.drawable.panago);
                    nameTextView.setText("Panago Pizza");
                    locTextView.setText("44 Gerrard St W, Toronto, ON M5G 2K2");
                }
                else if (position == 2)
                {
                    restaurant = "trattoria";
                    Toast.makeText(getApplicationContext(),"Trattoria Nervosa",Toast.LENGTH_SHORT).show();
                    restaurantLogo.setImageResource(R.drawable.trattoria);
                    nameTextView.setText("Trattoria Nervosa");
                    locTextView.setText("75 Yorkville Ave, Toronto, ON M5R 1B8");
                }
            }
        });

        mapsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (restaurant == "cibo")
                {
                    //Intent to start cibo map activity
                    Intent mapsButtonIntent = new Intent(ItalianActivity.this, CiboActivity.class);
                    startActivity(mapsButtonIntent);
                }
                else if (restaurant == "panago")
                {
                    //Intent to start panago map activity
                    Intent mapsButtonIntent = new Intent(ItalianActivity.this, PanagoActivity.class);
                    startActivity(mapsButtonIntent);
                }
                else if (restaurant == "trattoria")
                {
                    //Intent to start trattoria map activity
                    Intent mapsButtonIntent = new Intent(ItalianActivity.this, TrattoriaActivity.class);
                    startActivity(mapsButtonIntent);
                }

            }
        });
    }
}