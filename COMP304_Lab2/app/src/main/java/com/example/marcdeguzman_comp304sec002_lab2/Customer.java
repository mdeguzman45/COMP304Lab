package com.example.marcdeguzman_comp304sec002_lab2;

import android.os.Parcel;
import android.os.Parcelable;

// implementing this as parcelable to be able to pass it between activities
public class Customer implements Parcelable  {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Customer createFromParcel(Parcel in) {
            return new Customer(in);
        }

        public Customer[] newArray(int size) {
            return new Customer[size];
        }
    };

    private String name;
    private String streetAddress;
    private String city;
    private String state;
    private String postalCode;
    private String phoneNumber;
    private String emailAddress;
    private String otherInfo1;
    private String otherInfo2;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getOtherInfo1() {
        return otherInfo1;
    }

    public void setOtherInfo1(String otherInfo1) {
        this.otherInfo1 = otherInfo1;
    }

    public String getOtherInfo2() {
        return otherInfo2;
    }

    public void setOtherInfo2(String otherInfo2) {
        this.otherInfo2 = otherInfo2;
    }

    public Customer(String customerName, String customerAddress, String customerCity, String customerState,
                    String customerPostal, String customerPhone, String customerEmail) {
        name = customerName;
        streetAddress = customerAddress;
        city = customerCity;
        state = customerState;
        postalCode = customerPostal;
        phoneNumber = customerPhone;
        emailAddress = customerEmail;
    }
    // Parcelling part
    public Customer(Parcel in) {
        this.name = in.readString();
        this.streetAddress = in.readString();
        this.city = in.readString();
        this.state = in.readString();
        this.postalCode = in.readString();
        this.phoneNumber = in.readString();
        this.emailAddress = in.readString();
        this.otherInfo1 = in.readString();
        this.otherInfo2 = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.streetAddress);
        dest.writeString(this.city);
        dest.writeString(this.state);
        dest.writeString(this.postalCode);
        dest.writeString(this.phoneNumber);
        dest.writeString(this.emailAddress);
        dest.writeString(this.otherInfo1);
        dest.writeString(this.otherInfo2);

    }
}
