package com.example.mahmoud.carsparepartsonlineshopping.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.mahmoud.carsparepartsonlineshopping.R;

public class OrderCompleteActivity extends AppCompatActivity {

    private Button backToHomeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_complete);
        initView();


        backToHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToHomePage();
            }
        });
    }

    private void goToHomePage() {
        startActivity(new Intent(OrderCompleteActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        goToHomePage();
        super.onBackPressed();
    }

    private void initView() {
        backToHomeBtn = (Button) findViewById(R.id.back_to_home_btn);
    }
}
