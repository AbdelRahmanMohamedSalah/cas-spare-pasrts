package com.example.mahmoud.carsparepartsonlineshopping.models;

public class Address {
    public String state, city, street, block, department, phoneCheckout, comment;

    public Address(String state, String city, String street, String block, String department, String phoneCheckout, String comment) {
        this.state = state;
        this.city = city;
        this.street = street;
        this.block = block;
        this.department = department;
        this.phoneCheckout = phoneCheckout;
        this.comment = comment;
    }

    public Address() {
    }
}
