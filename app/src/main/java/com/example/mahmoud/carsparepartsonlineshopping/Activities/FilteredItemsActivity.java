package com.example.mahmoud.carsparepartsonlineshopping.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.mahmoud.carsparepartsonlineshopping.Adapters.AdapterItemProduct;
import com.example.mahmoud.carsparepartsonlineshopping.Adapters.ItemAdapter;
import com.example.mahmoud.carsparepartsonlineshopping.R;
import com.example.mahmoud.carsparepartsonlineshopping.models.Products;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FilteredItemsActivity extends AppCompatActivity {
    DatabaseReference db;
    private RecyclerView recycler;
    ArrayList<Products> products;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtered_items);
        initView();

        db = FirebaseDatabase.getInstance().getReference();

        products = new ArrayList<>();
        recycler.setLayoutManager(new GridLayoutManager(this, 2));
        final AdapterItemProduct adapter = new AdapterItemProduct(products, this);
        recycler.setAdapter(adapter);

        String query = getIntent().getStringExtra("query");
        String category = getIntent().getStringExtra("category");

        System.out.println(query + " " + category);
        db.child("Category").child(query).child(category).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    db.child("product").child(child.getKey()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Products product = dataSnapshot.getValue(Products.class);
                            product.withId(dataSnapshot.getKey());
                            products.add(product);
                            adapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void initView() {
        recycler = (RecyclerView) findViewById(R.id.recycler);
    }
}
