package com.example.marcdeguzman_comp304sec002_lab2;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Pizza_Setup extends AppCompatActivity {
    int pizzaType;
    String pizzaText;
    String pizzaToppings;
    String pizzaCrust;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_setup);

        // define an intent object to get a value
        Intent i = getIntent();

        // reset the variables
        pizzaText = "";
        pizzaToppings = "";
        pizzaCrust = "";

        // Get the pizza type selected
        pizzaType = i.getIntExtra("pizzaType", R.id.canadian_pizza_type);
        setupPizzaView(pizzaType);

        // setup radio button group for crust selection
        setupCrustSelection();
    }

    public void addPizzaSelection(View view) {
        Spinner mySpinner = (Spinner) findViewById(R.id.pizzaSizeSpinner);
        String pizzaSize = mySpinner.getSelectedItem().toString();


        if (pizzaCrust == "") {
            Toast.makeText(getApplicationContext(), "Please select crust type before adding to cart.", Toast.LENGTH_SHORT).show();
        } else {
            // create the Pizza object

            Pizza aPizza = new Pizza(pizzaText, pizzaToppings, pizzaSize, pizzaCrust);

            Intent addPizzaIntent = new Intent();
            addPizzaIntent.putExtra("newPizza", aPizza);
            setResult(999, addPizzaIntent);

            // go back to pizza selection page
            backToCart(view);
        }
    }

    public void backToCart(View view) {
        // go back to pizza selection page
        finish();
    }

    // set image and toppings to be shown
    private void setupPizzaView(int pizzaType) {

        ImageView pizzaImage = (ImageView) findViewById(R.id.selectedPizzaImg);
        switch(pizzaType) {
            case R.id.canadian_pizza_type:
                pizzaText = getString(R.string.canadian_text);
                pizzaToppings = getString(R.string.canadian_toppings);
                pizzaImage.setImageResource(R.drawable.canadian_pizza);
                break;
            case R.id.chicken_caesar_pizza_type:
                pizzaText = getString(R.string.chicken_caesar_text);
                pizzaToppings = getString(R.string.chicken_caesar_toppings);
                pizzaImage.setImageResource(R.drawable.chicken_caesar);
                break;
            case R.id.hawaiian_pizza_type:
                pizzaText = getString(R.string.hawaiian_text);
                pizzaToppings = getString(R.string.hawaiian_toppings);
                pizzaImage.setImageResource(R.drawable.hawaiian);
                break;
            case R.id.maple_bacon_pizza_type:
                pizzaText = getString(R.string.smokey_maple_bacon_text);
                pizzaToppings = getString(R.string.smokey_maple_bacon_toppings);
                pizzaImage.setImageResource(R.drawable.smokey_maple_bacon);
                break;
            case R.id.veggie_lovers_pizza_type:
                pizzaText = getString(R.string.veggie_lovers_text);
                pizzaToppings = getString(R.string.veggie_lovers_toppings);
                pizzaImage.setImageResource(R.drawable.veggie_lovers);
                break;
        }

        TextView pizzaTextView = findViewById(R.id.pizzaType);
        TextView toppingsTextView = findViewById(R.id.pizzaTopping);

        pizzaTextView.setText(pizzaText);
        toppingsTextView.setText(pizzaToppings);
    }

    private void setupCrustSelection() {
        // create the radio group
        RadioGroup radioGroup = findViewById(R.id.crustGroupButton);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rbThinCrust = findViewById(R.id.thinCrustButton);
                RadioButton rbThickCrust = findViewById(R.id.thickCrustButton);

                if (rbThinCrust.isChecked()) {
                    pizzaCrust = "Thin";
                } else {
                    pizzaCrust = "Thick";
                }
            }
        });
    }

}