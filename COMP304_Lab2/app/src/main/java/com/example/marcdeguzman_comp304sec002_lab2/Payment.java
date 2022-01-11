package com.example.marcdeguzman_comp304sec002_lab2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class Payment extends AppCompatActivity {

    ConstraintLayout cardDetailsLayout;
    Button confirmPaymentButton;
    ArrayList pizzaCheckOutList;
    String paymentType = "";

    // text fields for card details
    EditText cardNumberEditView;
    EditText cardExpiryDateEditView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        pizzaCheckOutList = new ArrayList<Pizza>();
        cardNumberEditView = (EditText) findViewById(R.id.cardNumberEditText);
        cardExpiryDateEditView = (EditText) findViewById(R.id.cardExpiryDateEditText);

        cardNumberEditView.setText("");
        cardExpiryDateEditView.setText("");

        // define an intent object to get the checkout list
        Intent intent = getIntent();

        // Get the list from the intent object
        pizzaCheckOutList = intent.getParcelableArrayListExtra("pizzaList");

        // don't show card details unless user selects credit/debit
        cardDetailsLayout = (ConstraintLayout) findViewById(R.id.cardDetailsConstraintLayout);
        cardDetailsLayout.setVisibility(View.GONE);

        // Checkout button will be available once user select at least one item for checkout
        confirmPaymentButton = (Button) findViewById(R.id.confirmPaymentButton);
        confirmPaymentButton.setVisibility(View.GONE);

        // setup payment radio button handler
        setupPaymentOption();
    }

    public void goToCustomerInformation(View view) {
        // lets check first if date and expiry date is not null
        // add other validations in the future
        String cardNumberValue = cardNumberEditView.getText().toString();
        String expiryDateValue = cardExpiryDateEditView.getText().toString();

        // validate card details only
        if (!paymentType.equals("Cash")) {
            if (Helper.Validate.isBlank(cardNumberValue)) {
                Toast.makeText(getApplicationContext(), "Card Number is blank", Toast.LENGTH_SHORT).show();
                cardNumberEditView.requestFocus();
                return;
            }

            if (Helper.Validate.isBlank(expiryDateValue)) {
                Toast.makeText(getApplicationContext(), "Card Expiry date is blank", Toast.LENGTH_SHORT).show();
                cardExpiryDateEditView.requestFocus();
                return;
            }
        }

        // go to customer information
        Intent intent = new Intent(Payment.this, CustomerInformation.class);

        // pass the checkout list to the CustomerInformation activity
        intent.putParcelableArrayListExtra("pizzaList", pizzaCheckOutList);

        // pass the payment type and values if applicable;
        intent.putExtra("paymentType", paymentType);

        if (paymentType.equals("Debit") || paymentType.equals("Credit")) {
            // pass the card details
            intent.putExtra("cardNumber", cardNumberValue);
            intent.putExtra("cardExpiry", expiryDateValue);
        }

        // startActivity
        startActivity(intent);
    }

    private void setupPaymentOption() {
        // create the radio group
        RadioGroup radioGroup = findViewById(R.id.paymentRadioGroupButton);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rbCash = findViewById(R.id.cashRadioButton);
                RadioButton rbCredit = findViewById(R.id.creditRadioButton);
                RadioButton rbDebit = findViewById(R.id.debitRadioButton);

                // show proceed payment button when a payment type is selected
                confirmPaymentButton.setVisibility(View.VISIBLE);

                if (rbCash.isChecked()) {
                    cardDetailsLayout.setVisibility(View.GONE);
                    paymentType = "Cash";
                } else if (rbCredit.isChecked()){
                    cardDetailsLayout.setVisibility(View.VISIBLE);
                    paymentType = "Credit";
                } else {
                    // debit payment
                    paymentType = "Debit";
                    cardDetailsLayout.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}