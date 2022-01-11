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

public class FilipinoActivity extends AppCompatActivity {

    ListView listView;
    ImageView restaurantLogo;
    Button mapsButton;
    String restaurant;
    TextView nameTextView;
    TextView locTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filipino);

        setTitle("Filipino Cuisine");

        listView = findViewById(R.id.listview);
        restaurantLogo = findViewById(R.id.restaurantImageView);
        mapsButton = findViewById(R.id.mapsButton);
        nameTextView = findViewById(R.id.nameTextView);
        locTextView = findViewById(R.id.locTextView);

        //contents of the listview
        String[] values = new String[] {
                "Lamesa", "Bagnet Bros", "Casa Manila"
        };

        //setting up and using an array adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0)
                {
                    restaurant = "lamesa";
                    Toast.makeText(getApplicationContext(),"Lamesa",Toast.LENGTH_SHORT).show();
                    restaurantLogo.setImageResource(R.drawable.lamesa);
                    nameTextView.setText("Lamesa");
                    locTextView.setText("634 St Clair Ave W, Toronto, ON M6C 1A9");

                }
                else if (position == 1)
                {
                    restaurant = "bagnetbros";
                    Toast.makeText(getApplicationContext(),"Bagnet Bros",Toast.LENGTH_SHORT).show();
                    restaurantLogo.setImageResource(R.drawable.bagnetbros);
                    nameTextView.setText("Bagnet Bros");
                    locTextView.setText("3522 Bathurst St, North York, ON M6A 2C7");
                }
                else if (position == 2)
                {
                    restaurant = "casamanila";
                    Toast.makeText(getApplicationContext(),"Casa Manila",Toast.LENGTH_SHORT).show();
                    restaurantLogo.setImageResource(R.drawable.casamanila);
                    nameTextView.setText("Casa Manila");
                    locTextView.setText("879 York Mills Rd Unit #1, North York, ON M3B 1Y5");
                }
            }
        });

        mapsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (restaurant == "lamesa")
                {
                    //Intent to start lamesa map activity
                    Intent mapsButtonIntent = new Intent(FilipinoActivity.this, LamesaActivity.class);
                    startActivity(mapsButtonIntent);
                }
                else if (restaurant == "bagnetbros")
                {
                    //Intent to start bagnetbros map activity
                    Intent mapsButtonIntent = new Intent(FilipinoActivity.this, BagnetBrosActivity.class);
                    startActivity(mapsButtonIntent);
                }
                else if (restaurant == "casamanila")
                {
                    //Intent to start casamanila map activity
                    Intent mapsButtonIntent = new Intent(FilipinoActivity.this, CasaManilaActivity.class);
                    startActivity(mapsButtonIntent);
                }

            }
        });
    }
}