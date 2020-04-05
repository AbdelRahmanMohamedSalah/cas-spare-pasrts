package com.example.mahmoud.carsparepartsonlineshopping.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.andremion.counterfab.CounterFab;
import com.example.mahmoud.carsparepartsonlineshopping.Adapters.AdapterItemProduct;
import com.example.mahmoud.carsparepartsonlineshopping.R;
import com.example.mahmoud.carsparepartsonlineshopping.models.Products;
import com.example.mahmoud.carsparepartsonlineshopping.utils.StaticConfig;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ActivityProduct extends AppCompatActivity {

    DatabaseReference databaseReference;
    ArrayList<Products> arrayProduct;
    AdapterItemProduct adapter;
    RecyclerView recyclerView;
    CounterFab fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);


        arrayProduct = new ArrayList<>();
        adapter = new AdapterItemProduct(arrayProduct, this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerProduct);
        fab = (CounterFab) findViewById(R.id.fab);

        Intent intent = getIntent();
        final String category = intent.getStringExtra("category");

        databaseReference = FirebaseDatabase.getInstance().getReference("product");


        databaseReference.orderByChild("category").equalTo(category).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot data : dataSnapshot.getChildren()) {

                    Products products = data.getValue(Products.class).withId(data.getKey());
                    arrayProduct.add(products);
                    //   String id = data.getKey();
//                    String name = data.child("name").getValue(String.class);
//                    String manu = data.child("manu").getValue(String.class);
//                    String model = data.child("model").getValue(String.class);
//                    String price = data.child("price").getValue(String.class);
//                    String subModel = data.child("subModel").getValue(String.class);
//                    String subCategory = data.child("subCategory").getValue(String.class);
//                    String image = data.child("image").getValue(String.class);
//                    String year = data.child("year").getValue(String.class);
//                    String categoryy = data.child("category").getValue(String.class);
//                    arrayProduct.add(new Products(categoryy, image, manu, model, name, price, subCategory, subModel, year));


                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityProduct.this, CartProduct.class));
            }
        });
        fab.setCount(StaticConfig.items.size());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == 0) {
                fab.setCount(StaticConfig.items.size());
            }
        }
    }
}
