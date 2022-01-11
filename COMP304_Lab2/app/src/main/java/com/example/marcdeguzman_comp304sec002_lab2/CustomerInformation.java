package com.example.marcdeguzman_comp304sec002_lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomerInformation extends AppCompatActivity {

    ArrayList pizzaCheckOutList;

    // text fields for card details
    String cardNumber = "";
    String cardExpiryDate = "";
    String paymentType = "";

    // edit view fields
    EditText fullNameEditText;
    EditText streetAddressEditText;
    EditText cityEditText;
    EditText stateEditText;
    EditText postalCodeEditText;
    EditText phoneEditView;
    EditText emailEditView;
    EditText otherInfo1EditView;
    EditText otherInfo2EditView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_information);

        pizzaCheckOutList = new ArrayList<Pizza>();

        // define an intent object to get the checkout list and the payment information
        Intent intent = getIntent();

        // Get the list from the intent object
        pizzaCheckOutList = intent.getParcelableArrayListExtra("pizzaList");

        // get the payment type
        paymentType = intent.getStringExtra("paymentType");

        if (paymentType.equals("Debit") || paymentType.equals("Credit")) {
            // get the card details
            cardNumber = intent.getStringExtra("cardNumber");
            cardExpiryDate = intent.getStringExtra("cardExpiry");
        }

        setupFieldReference();
    }

    public void confirmOrder(View view) {
        String fullName = fullNameEditText.getText().toString();
        String address = streetAddressEditText.getText().toString();
        String city = cityEditText.getText().toString();
        String state = stateEditText.getText().toString();
        String postalCode = postalCodeEditText.getText().toString();
        String phoneNumber = phoneEditView.getText().toString();
        String email = emailEditView.getText().toString();
        String otherInfo1 = otherInfo1EditView.getText().toString();
        String otherInfo2 = otherInfo2EditView.getText().toString();

        // for now validate if required fields are blank
        if (Helper.Validate.isBlank(fullName)) {
            Toast.makeText(getApplicationContext(), "Full name is blank", Toast.LENGTH_SHORT).show();
            fullNameEditText.requestFocus();
            return;
        }

        if (Helper.Validate.isBlank(address)) {
            Toast.makeText(getApplicationContext(), "Street Address is blank", Toast.LENGTH_SHORT).show();
            streetAddressEditText.requestFocus();
            return;
        }

        if (Helper.Validate.isBlank(city)) {
            Toast.makeText(getApplicationContext(), "City is blank", Toast.LENGTH_SHORT).show();
            cityEditText.requestFocus();
            return;
        }

        if (Helper.Validate.isBlank(state)) {
            Toast.makeText(getApplicationContext(), "State/Province is blank", Toast.LENGTH_SHORT).show();
            stateEditText.requestFocus();
            return;
        }

        if (Helper.Validate.isBlank(postalCode)) {
            Toast.makeText(getApplicationContext(), "Postal Code is blank", Toast.LENGTH_SHORT).show();
            postalCodeEditText.requestFocus();
            return;
        }

        if (Helper.Validate.isBlank(phoneNumber)) {
            Toast.makeText(getApplicationContext(), "Phone number is blank", Toast.LENGTH_SHORT).show();
            phoneEditView.requestFocus();
            return;
        }

        if (Helper.Validate.isBlank(email)) {
            Toast.makeText(getApplicationContext(), "Email address is blank", Toast.LENGTH_SHORT).show();
            emailEditView.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(getApplicationContext(), "Email address is invalid", Toast.LENGTH_SHORT).show();
            emailEditView.requestFocus();
            return;
        }

        Customer customer = new Customer(fullName, address, city, state, postalCode, phoneNumber, email);

        // add other info if user entered any
        if (!Helper.Validate.isBlank(otherInfo1)) {
            customer.setOtherInfo1(otherInfo1);
        }

        if (!Helper.Validate.isBlank(otherInfo2)) {
            customer.setOtherInfo2(otherInfo2);
        }

        // go to confirmation order
        Intent intent = new Intent(CustomerInformation.this, OrderConfirmation.class);

        // pass the checkout list to the Confirmation Page activity
        intent.putParcelableArrayListExtra("pizzaList", pizzaCheckOutList);

        // pass the payment type and values if applicable;
        intent.putExtra("paymentType", paymentType);

        if (paymentType.equals("Debit") || paymentType.equals("Credit")) {
            // pass the card details
            intent.putExtra("cardNumber", cardNumber);
            intent.putExtra("cardExpiry", cardExpiryDate);
        }

        // pass the customer information
        intent.putExtra("customerInfo", customer);

        // startActivity
        startActivity(intent);
    }

    private void setupFieldReference() {
        fullNameEditText = (EditText) findViewById(R.id.fullNameEditText);
        streetAddressEditText = (EditText) findViewById(R.id.streetAddressEditText);
        cityEditText = (EditText) findViewById(R.id.cityEditText);
        stateEditText = (EditText) findViewById(R.id.stateEditText);
        postalCodeEditText = (EditText) findViewById(R.id.postalCodeEditText);
        phoneEditView = (EditText) findViewById(R.id.phoneEditView);
        emailEditView = (EditText) findViewById(R.id.emailEditView);
        otherInfo1EditView = (EditText) findViewById(R.id.otherCustomerInfo1EditView);
        otherInfo2EditView = (EditText) findViewById(R.id.otherCustomerInfo2EditView);

        fullNameEditText.setText("");
        streetAddressEditText.setText("");
        cityEditText.setText("");
        stateEditText.setText("");
        postalCodeEditText.setText("");
        phoneEditView.setText("");
        emailEditView.setText("");
        otherInfo1EditView.setText("");
        otherInfo2EditView.setText("");
    }

}