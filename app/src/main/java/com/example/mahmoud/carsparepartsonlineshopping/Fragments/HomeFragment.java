package com.example.mahmoud.carsparepartsonlineshopping.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.mahmoud.carsparepartsonlineshopping.Activities.ActivityProduct;
import com.example.mahmoud.carsparepartsonlineshopping.R;


public class HomeFragment extends Fragment {
    FrameLayout interiors, Exterior, engine;
    ProductsFragment ProductsFragment;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        interiors = (FrameLayout) v.findViewById(R.id.interiors);
        Exterior = (FrameLayout) v.findViewById(R.id.frameExterior);
        engine = (FrameLayout) v.findViewById(R.id.frameEngine);

        ProductsFragment = new ProductsFragment();
        engine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                setFragment(ProductsFragment);

                Intent intent = new Intent(getContext(), ActivityProduct.class);
                intent.putExtra("category", "engine");
                startActivity(intent);


            }
        });

        Exterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        //        setFragment(ProductsFragment);

                Intent intent = new Intent(getContext(), ActivityProduct.class);
                intent.putExtra("category", "exterior");
                startActivity(intent);
            }
        });


        interiors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //           setFragment(ProductsFragment);

                Intent intent = new Intent(getContext(), ActivityProduct.class);
                intent.putExtra("category", "enterior");
                startActivity(intent);
            }
        });


        return v;


    }

//    private void setFragment(Fragment fragment) {
//        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//        fragmentTransaction.replace(R.id.fragment_container, fragment);
//        fragmentTransaction.commit();
//
//
//    }
}
