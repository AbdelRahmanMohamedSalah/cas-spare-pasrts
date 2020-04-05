package com.example.mahmoud.carsparepartsonlineshopping.models;

import java.util.ArrayList;

public class Order {
    String id;
    ArrayList<Products> order;
    Address address;
    String userId;

    public Order() {

    }

    public Order(String id, ArrayList<Products> order, Address address, String userId) {
        this.id = id;
        this.order = order;
        this.address = address;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Products> getOrder() {
        return order;
    }

    public void setOrder(ArrayList<Products> order) {
        this.order = order;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
