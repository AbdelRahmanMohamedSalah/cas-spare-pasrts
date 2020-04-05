package com.example.mahmoud.carsparepartsonlineshopping.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mahmoud.carsparepartsonlineshopping.Adapters.AdapterItemProduct;
import com.example.mahmoud.carsparepartsonlineshopping.R;
import com.example.mahmoud.carsparepartsonlineshopping.models.Products;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductsFragment extends Fragment {

    DatabaseReference databaseReference;
    ArrayList<Products> arrayProduct;
    AdapterItemProduct adapter;
    RecyclerView recyclerView;


    public ProductsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_products_ftagment, container, false);

        arrayProduct = new ArrayList<>();
        adapter = new AdapterItemProduct(arrayProduct, getContext());
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerProduct);

        Intent intent = getActivity().getIntent();
        final String category = intent.getStringExtra("category");

        databaseReference = FirebaseDatabase.getInstance().getReference("product");


        databaseReference.orderByChild("category").equalTo(category).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot data : dataSnapshot.getChildren()) {


                    String id = data.getKey();
                    String name = data.child("name").getValue(String.class);
                    String manu = data.child("manu").getValue(String.class);
                    String model = data.child("model").getValue(String.class);
                    String price = data.child("price").getValue(String.class);
                    String subModel = data.child("subModel").getValue(String.class);
                    String subCategory = data.child("subCategory").getValue(String.class);
                    String image = data.child("image").getValue(String.class);
                    String year = data.child("year").getValue(String.class);
                    String categoryy = data.child("category").getValue(String.class);
                    arrayProduct.add(new Products(categoryy, image, manu, model, name, price, subCategory, subModel, year));
                    adapter.notifyDataSetChanged();


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(adapter);
        return v;
    }
}
