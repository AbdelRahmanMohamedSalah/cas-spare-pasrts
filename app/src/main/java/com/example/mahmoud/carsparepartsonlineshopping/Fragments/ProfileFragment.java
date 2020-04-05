package com.example.mahmoud.carsparepartsonlineshopping.Fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.mahmoud.carsparepartsonlineshopping.Activities.LogInActivity;
import com.example.mahmoud.carsparepartsonlineshopping.Activities.MainActivity;
import com.example.mahmoud.carsparepartsonlineshopping.Activities.MyOrdersActivity;
import com.example.mahmoud.carsparepartsonlineshopping.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {

    private TextView accountName;
    FirebaseAuth firebaseAuth;
    private Button logoutBtn;
    private TextView userPhoneNumber;
    DatabaseReference databaseReference;

    public ProfileFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v;
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            v = inflater.inflate(R.layout.not_login_fragment, container, false);
            Button loginBtn = v.findViewById(R.id.loginBtn);
            loginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), LogInActivity.class).putExtra("from", "LOGOUT"));
                }
            });
        } else {
            v = inflater.inflate(R.layout.fragment_profile, container, false);
            String uid = firebaseAuth.getInstance().getCurrentUser().getUid();
            databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(uid);


            accountName = (TextView) v.findViewById(R.id.account_name);
            logoutBtn = (Button) v.findViewById(R.id.logoutBtn);
            userPhoneNumber = (TextView) v.findViewById(R.id.userPhoneNumber);


            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    String name = dataSnapshot.child("name").getValue(String.class);
                    String phone = dataSnapshot.child("phone").getValue(String.class);
                    accountName.setText(name);
                    userPhoneNumber.setText(phone);


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            logoutBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                    alert.setTitle("Logout")
                            .setMessage("Are you sure to logout?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    FirebaseAuth.getInstance().signOut();
                                    startActivity(new Intent(getActivity(), MainActivity.class).putExtra("from", "LOGOUT"));
                                }
                            })
                            .setNegativeButton(android.R.string.no, null)
                            .show();
                }
            });

            Button ordersBtn = v.findViewById(R.id.order_btn);
            ordersBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), MyOrdersActivity.class));
                }
            });
        }

        return v;
    }


}
