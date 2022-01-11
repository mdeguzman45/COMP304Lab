package com.example.marcdeguzman_comp304sec002_lab2;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

// implementing this as parcelable to be able to pass it between activities
public class Pizza implements Parcelable {

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Pizza createFromParcel(Parcel in) {
            return new Pizza(in);
        }

        public Pizza[] newArray(int size) {
            return new Pizza[size];
        }
    };

    private int id;
    private String name;
    private String toppings;
    private String size;
    private String crust;
    private double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToppings() {
        return toppings;
    }

    public void setToppings(String toppings) {
        this.toppings = toppings;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCrust() {
        return crust;
    }

    public void setCrust(String crust) {
        this.crust = crust;
    }

    // for now this is how we set price
    private void setPrice() {
        if (size.equalsIgnoreCase("small")) {
            price = 8.00;
        } else if (size.equalsIgnoreCase("medium")) {
            price = 10.00;
        } else if (size.equalsIgnoreCase("large")) {
            price = 12.00;
        } else {
            // extra-large
            price = 14.00;
        }
    }

    public Pizza(String pizzaName, String pizzaToppings, String pizzaSize, String pizzaCrust) {
        name = pizzaName;
        toppings = pizzaToppings;
        size = pizzaSize;
        crust = pizzaCrust;
        setPrice();
    }

    // Parcelling part
    public Pizza(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.toppings = in.readString();
        this.size = in.readString();
        this.crust = in.readString();
        this.price = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.toppings);
        dest.writeString(this.size);
        dest.writeString(this.crust);
        dest.writeDouble(this.price);
    }

    @NonNull
    @NotNull
    @Override
    public String toString() {
        return name + " - " + size + " - " + crust + " crust";
    }

    public String toStringWithPrice() {
        return name + " " + size + " " + crust + " crust: ($" + Double.toString(price) + ")";
    }
}
