package com.example.mahmoud.carsparepartsonlineshopping.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.mahmoud.carsparepartsonlineshopping.R;
import com.example.mahmoud.carsparepartsonlineshopping.models.CartItem;
import com.example.mahmoud.carsparepartsonlineshopping.models.Products;
import com.example.mahmoud.carsparepartsonlineshopping.utils.StaticConfig;
import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductItems extends AppCompatActivity {


    TextView name, year, model, manu, subModel, subCategory, price;
    ImageView image;
    ElegantNumberButton elegantNumberButton;

    Button addToCartBtn;
    DatabaseReference databaseReference;

    Products product;
    public int quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_items);

        elegantNumberButton = findViewById(R.id.elegantNumber);
        name = findViewById(R.id.nameItem);
        year = findViewById(R.id.yearItem);
        model = findViewById(R.id.modelItem);
        manu = findViewById(R.id.manuItem);
        subModel = findViewById(R.id.subModelItem);
        subCategory = findViewById(R.id.subCategoryItem);
        price = findViewById(R.id.priceItem);
        image = findViewById(R.id.imageItem);

        addToCartBtn = (Button) findViewById(R.id.addToCartBtn);


        Intent intent = getIntent();
        product = (Products) intent.getSerializableExtra("item");
        name.setText(product.getName());
        year.setText(product.getYear());
        model.setText(product.getModel());
        manu.setText(product.getManu());
        subModel.setText(product.getSubModel());
        subCategory.setText(product.getSubCategory());
        price.setText(product.getPrice() + " LE");
        Picasso.with(this).load(product.getImage()).into(image);


//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot data : dataSnapshot.getChildren()) {
//                    String name = data.child("name").getValue(String.class);
//                    String price = data.child("price").getValue(String.class);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//

        elegantNumberButton.setNumber("1");
        elegantNumberButton.setRange(1, Integer.MAX_VALUE);
        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity = Integer.parseInt(elegantNumberButton.getNumber());
                addToCart(new CartItem(product, quantity));
                Toast.makeText(ProductItems.this, "Item Added ", Toast.LENGTH_SHORT).show();
                setResult(0);
                finish();
            }
        });

    }


    public void addToCart(CartItem cartItem) {
        String id = cartItem.product.id;
        System.out.println(StaticConfig.items.containsKey(id));
        if (StaticConfig.items.containsKey(id)) {
            StaticConfig.items.get(id).quantity += cartItem.quantity;
        } else {
            StaticConfig.items.put(id, cartItem);
        }
    }
}
