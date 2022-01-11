package com.example.marcdeguzman_comp304sec002_lab2;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Pizza_Selection extends AppCompatActivity {

    LinearLayout pizzaList;

    ActivityResultLauncher<Intent> addPizzaActivityResultLauncher;

    ArrayList pizzaCart = new ArrayList<Pizza>();
    ArrayList pizzaCheckOutList = new ArrayList<Pizza>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_selection);

        // Checkout button will be available once user select at least one item for checkout
        Button checkoutButton = (Button) findViewById(R.id.checkoutButton);
        checkoutButton.setVisibility(View.GONE);

        setupAddPizzaResultLauncher();
    }

    // attached the menu to the Pizza Selection layout
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pizza_selection, menu);

        return true;
    }

    // handle pizza selected
    public boolean onOptionsItemSelected(MenuItem item) {
        // create an Intent to send to Pizza setup
        Intent intent = new Intent(Pizza_Selection.this, Pizza_Setup.class);

        // store the pizza value in the intent object using putExtra()
        intent.putExtra("pizzaType", item.getItemId()); // key and value pair

        // move to pizza setup, we are expecting to get a pizza object
        addPizzaActivityResultLauncher.launch(intent);

        return true;
    }

    // proceed to checkout screen
    public void proceedToCheckout(View view) {
        Intent intent = new Intent(Pizza_Selection.this, Checkout.class);

        // pass the checkout list to the Checkout activity
        intent.putParcelableArrayListExtra("pizzaList", pizzaCheckOutList);

        // startActivity
        startActivity(intent);
    }

    private void setupAddPizzaResultLauncher() {
        addPizzaActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == 999) {
                            // process data
                            Intent intentData = result.getData();
                            Pizza aPizza = (Pizza) intentData.getParcelableExtra("newPizza");
                            // Toast.makeText(getApplicationContext(), aPizza.toString(), Toast.LENGTH_SHORT).show();
                            addPizzaToList(aPizza);
                        }
                    }
                });
    }

    // show the list of pizza selected
    // create a checkbox for each pizza added
    private void addPizzaToList(Pizza aPizza) {
        pizzaList = (LinearLayout) findViewById(R.id.pizzaList);
        TextView emptyCartText = (TextView) findViewById(R.id.cartEmptyText);
        emptyCartText.setVisibility(View.GONE);

        // set id for this pizza
        aPizza.setId(PizzaUtility.PizzaIDGenerator.GetNext());

        CheckBox checkBox = new CheckBox(this);
        checkBox.setId(aPizza.getId());
        checkBox.setText(aPizza.toString());

        // add pizza to cart only, not yet for checkout
        pizzaCart.add(aPizza);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    addPizzaToCheckout(buttonView.getId());
                } else {
                    removePizzaToCheckout(buttonView.getId());
                }
            }
        });

        pizzaList.addView(checkBox);
    }

    // remove the pizza on the checkout selection
    private void removePizzaToCheckout(int id) {
        Pizza pizzaToRemove = getPizzaObject(id);

        if (pizzaToRemove != null) {
            pizzaCheckOutList.remove(pizzaToRemove);
        }

        showCheckoutButton();
    }

    // adds the pizza to the checkout list
    private void addPizzaToCheckout(int id) {
        Pizza pizzaToAdd = getPizzaObject(id);

        if (pizzaToAdd != null) {
            pizzaCheckOutList.add(pizzaToAdd);
        }

        showCheckoutButton();
    }

    // lets check if we need to show the checkout button
    private void showCheckoutButton() {
        Button checkoutButton = (Button) findViewById(R.id.checkoutButton);

        if (pizzaCheckOutList.size() > 0) {
            checkoutButton.setVisibility(View.VISIBLE);
        } else {
            checkoutButton.setVisibility(View.GONE);
        }
    }

    // get the pizza object
    private Pizza getPizzaObject(int id) {
        Pizza aPizza = null;
        for (int i = 0; i < pizzaCart.size(); i++) {
            Pizza itemPizza = (Pizza) pizzaCart.get(i);
            if (itemPizza.getId() == id) {
                aPizza = itemPizza;
                break;
            }
        }

        return aPizza;
    }
}