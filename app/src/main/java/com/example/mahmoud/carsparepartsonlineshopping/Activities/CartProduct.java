package com.example.mahmoud.carsparepartsonlineshopping.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mahmoud.carsparepartsonlineshopping.Adapters.CartAdapter;
import com.example.mahmoud.carsparepartsonlineshopping.R;
import com.example.mahmoud.carsparepartsonlineshopping.models.CartItem;
import com.example.mahmoud.carsparepartsonlineshopping.utils.StaticConfig;

import java.util.ArrayList;

public class CartProduct extends AppCompatActivity {

    RecyclerView recyclerView;
    CartAdapter adapter;
    TextView totalPrice;
    Button checkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_product);
        if (StaticConfig.items.isEmpty()) {
            ((LinearLayout) findViewById(R.id.empty_cart)).setVisibility(View.VISIBLE);
            ((LinearLayout) findViewById(R.id.full_cart)).setVisibility(View.GONE);
        } else {
            ((LinearLayout) findViewById(R.id.empty_cart)).setVisibility(View.GONE);
            ((LinearLayout) findViewById(R.id.full_cart)).setVisibility(View.VISIBLE);
        }

        totalPrice = (TextView) findViewById(R.id.totalPrice);
        ArrayList<CartItem> items = new ArrayList<>(StaticConfig.items.values());
        adapter = new CartAdapter(items, this, totalPrice);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerCart);
        checkout = findViewById(R.id.chekOutBtn);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartProduct.this, CheckoutActivity.class));
            }
        });


    }
}
