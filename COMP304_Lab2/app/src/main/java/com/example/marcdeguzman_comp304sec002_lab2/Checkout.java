package com.example.marcdeguzman_comp304sec002_lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Checkout extends AppCompatActivity {

    ArrayList pizzaCheckOutList;
    LinearLayout linearPizzaView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        pizzaCheckOutList = new ArrayList<Pizza>();

        // define an intent object to get the checkout list
        Intent intent = getIntent();

        // Get the list from the intent object
        pizzaCheckOutList = intent.getParcelableArrayListExtra("pizzaList");

        linearPizzaView = findViewById(R.id.linearViewPizza);

        Double totalPrice = 0.0;

        for (int i = 0; i < pizzaCheckOutList.size(); i++) {
            Pizza itemPizza = (Pizza) pizzaCheckOutList.get(i);
            Log.d("Checkout.onCreate", "pizza to checkout: " + itemPizza.toStringWithPrice());
            totalPrice = totalPrice + itemPizza.getPrice();

            TextView pizzaPrice = new TextView(this);
            TextView pizzaToppings = new TextView(this);

            TextView pizzaDetails = new TextView(this);
            pizzaDetails.setTextAppearance(R.style.pizza_heading_6pt_left_bold);
            pizzaPrice.setTextAppearance(R.style.pizza_heading_6pt_left_bold);

            pizzaDetails.setText(itemPizza.toStringWithPrice());
            pizzaPrice.setText(Double.toString(itemPizza.getPrice()));
            pizzaToppings.setText(itemPizza.getToppings());

            // to add spacing in between pizza details
            pizzaToppings.setPadding(0,0,0, 20);

            linearPizzaView.addView(pizzaDetails);
            linearPizzaView.addView(pizzaToppings);
        }

        TextView totalPriceView = new TextView(this);
        totalPriceView.setTextAppearance(R.style.pizza_heading_6pt_right_bold);

        String totalPriceString = "Total: $" + Double.toString(totalPrice);
        totalPriceView.setPadding(0, 10, 0, 0);
        totalPriceView.setText(totalPriceString);

        totalPriceView.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
        linearPizzaView.addView(totalPriceView);
    }

    public void goToPayment(View view) {
        Intent intent = new Intent(Checkout.this, Payment.class);

        // pass the checkout list to the Payment activity
        intent.putParcelableArrayListExtra("pizzaList", pizzaCheckOutList);

        // startActivity
        startActivity(intent);
    }
}