package com.example.mahmoud.carsparepartsonlineshopping.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mahmoud.carsparepartsonlineshopping.R;
import com.example.mahmoud.carsparepartsonlineshopping.models.Address;
import com.example.mahmoud.carsparepartsonlineshopping.utils.StaticConfig;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CheckoutActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    private EditText state;
    private EditText city;
    private EditText street;
    private EditText block;
    private EditText department;
    private EditText phoneCheckout;
    private EditText comment;
    private Button buyCheckout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user == null) {
            startActivity(new Intent(CheckoutActivity.this, LogInActivity.class).putExtra("from", "CHECKOUT"));
            finish();
        }
        initView();

        buyCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkAddress()) {
                    return;
                }
                Address address = new Address(
                        state.getText().toString(),
                        city.getText().toString(),
                        street.getText().toString(),
                        block.getText().toString(),
                        department.getText().toString(),
                        phoneCheckout.getText().toString(),
                        comment.getText().toString()
                );

                Map<String, Object> order = new HashMap<>();
                order.put("address", address);
                order.put("order", new ArrayList<>(StaticConfig.items.values()));
                order.put("userId", firebaseAuth.getCurrentUser().getUid());

                FirebaseDatabase.getInstance().getReference("orders").push().setValue(order);
                startActivity(new Intent(CheckoutActivity.this, OrderCompleteActivity.class));
                StaticConfig.items.clear();
                finish();
            }
        });
    }

    private boolean checkAddress() {
        if (TextUtils.isEmpty(state.getText())) {
            state.setError("Required Data");
            return false;
        }
        if (TextUtils.isEmpty(city.getText())) {
            city.setError("Required Data");
            return false;
        }
        if (TextUtils.isEmpty(street.getText())) {
            street.setError("Required Data");
            return false;
        }
        if (TextUtils.isEmpty(block.getText())) {
            block.setError("Required Data");
            return false;
        }
        if (TextUtils.isEmpty(department.getText())) {
            department.setError("Required Data");
            return false;
        }
        if (TextUtils.isEmpty(phoneCheckout.getText())) {
            phoneCheckout.setError("Required Data");
            return false;
        }
        return true;
    }

    private void initView() {
        state = (EditText) findViewById(R.id.state);
        city = (EditText) findViewById(R.id.city);
        street = (EditText) findViewById(R.id.street);
        block = (EditText) findViewById(R.id.block);
        department = (EditText) findViewById(R.id.department);
        phoneCheckout = (EditText) findViewById(R.id.phoneCheckout);
        comment = (EditText) findViewById(R.id.comment);
        buyCheckout = (Button) findViewById(R.id.buyCheckout);
    }
}
