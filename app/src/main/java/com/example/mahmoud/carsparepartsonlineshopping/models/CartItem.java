package com.example.mahmoud.carsparepartsonlineshopping.models;

import java.io.Serializable;

public class CartItem implements Serializable {
    public Products product;
    public int quantity;

    public CartItem() {
    }

    public CartItem(Products product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
}
