package com.example.mahmoud.carsparepartsonlineshopping.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.mahmoud.carsparepartsonlineshopping.R;

public class WelcomeActivity extends AppCompatActivity {

    ImageView carParts;
    private ImageView gear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        carParts = (ImageView) findViewById(R.id.carpartsId);
        initView();
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(4000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                }
            }
        };

        thread.start();

        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f,
                Animation.RELATIVE_TO_SELF, 0.517f, Animation.RELATIVE_TO_SELF,
                0.392f);
        rotateAnimation.setDuration((long) 4000);
        rotateAnimation.setRepeatCount(0);
        gear.setAnimation(rotateAnimation);

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    private void initView() {
        gear = (ImageView) findViewById(R.id.gear);
    }
}
