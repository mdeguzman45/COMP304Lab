package com.example.marcdeguzman_comp304sec002_lab2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class OrderConfirmation extends AppCompatActivity {
    ArrayList pizzaCheckOutList;
    LinearLayout linearPizzaView;
    Customer customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);

        pizzaCheckOutList = new ArrayList<Pizza>();
        linearPizzaView = (LinearLayout) findViewById(R.id.confirmedPizzaListLinearLayout);

        // define an intent object to get the checkout list
        Intent intent = getIntent();

        // Get the list from the intent object
        pizzaCheckOutList = intent.getParcelableArrayListExtra("pizzaList");

        // Get customer information
        customer = (Customer) intent.getParcelableExtra("customerInfo");

        Double totalPrice = 0.0;

        // set pizza ordered
        for (int i = 0; i < pizzaCheckOutList.size(); i++) {
            Pizza itemPizza = (Pizza) pizzaCheckOutList.get(i);
            Log.d("Checkout.onCreate", "pizza to order: " + itemPizza.toStringWithPrice());
            totalPrice = totalPrice + itemPizza.getPrice();

            TextView pizzaDetails = new TextView(this);
            pizzaDetails.setTextAppearance(R.style.pizza_heading_6pt_left_bold);

            pizzaDetails.setText(itemPizza.toString());

            // to add spacing in between pizza details
            pizzaDetails.setPadding(0,0,0, 20);

            linearPizzaView.addView(pizzaDetails);
        }

        TextView totalPriceView = new TextView(this);
        totalPriceView.setTextAppearance(R.style.pizza_heading_6pt_right_bold);

        String totalPriceString = "Total: $" + Double.toString(totalPrice);
        totalPriceView.setPadding(0, 10, 0, 0);
        totalPriceView.setText(totalPriceString);

        totalPriceView.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_END);
        linearPizzaView.addView(totalPriceView);

        // set customer info view
        TextView customerNameTextView = (TextView) findViewById(R.id.customerNameTextView);
        customerNameTextView.setText(customer.getName());

        TextView customerAddressTextView = (TextView) findViewById(R.id.customerAddressTextView);
        String customerAddress = customer.getStreetAddress() + ", " + customer.getCity() + ", " + customer.getState() + ", " + customer.getPostalCode();
        customerAddressTextView.setText(customerAddress);

        TextView customerPaymentTypeTextView = (TextView) findViewById(R.id.customerPaymentTypeTextView);
        String paymentType = "Payment Type: " + intent.getStringExtra("paymentType");
        customerPaymentTypeTextView.setText(paymentType);
    }

    public void goBackToHome(View view) {
        // go back to main activity
        Intent intent = new Intent(OrderConfirmation.this, MainActivity.class);

        startActivity(intent);
    }
}