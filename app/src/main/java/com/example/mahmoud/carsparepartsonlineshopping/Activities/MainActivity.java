package com.example.mahmoud.carsparepartsonlineshopping.Activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.mahmoud.carsparepartsonlineshopping.Fragments.HomeFragment;
import com.example.mahmoud.carsparepartsonlineshopping.Fragments.ProfileFragment;
import com.example.mahmoud.carsparepartsonlineshopping.Fragments.SearchFragment;
import com.example.mahmoud.carsparepartsonlineshopping.R;
import com.example.mahmoud.carsparepartsonlineshopping.utils.BottomNavigationViewHelper;

public class MainActivity extends AppCompatActivity {

    HomeFragment homeFragment;
    SearchFragment searchFragment;
    ProfileFragment profileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        bottomNav.setSelectedItemId(R.id.nav_home);

        homeFragment = new HomeFragment();
        searchFragment = new SearchFragment();
        profileFragment = new ProfileFragment();
//
//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.add(R.id.fragment_container, homeFragment);
//        fragmentTransaction.commit();


    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            setFragment(homeFragment);
                            return true;

                        case R.id.nav_search:
                            setFragment(searchFragment);
                            return true;

                        case R.id.nav_profile:
                            setFragment(profileFragment);
                            return true;

                        case R.id.navCard:
                            startActivity(new Intent(MainActivity.this,CartProduct.class));

                        default:
                            return false;
                    }
                }
            };

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();

    }

}
