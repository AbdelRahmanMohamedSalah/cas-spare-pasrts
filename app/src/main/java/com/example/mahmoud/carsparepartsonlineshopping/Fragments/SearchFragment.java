package com.example.mahmoud.carsparepartsonlineshopping.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.mahmoud.carsparepartsonlineshopping.Activities.FilteredItemsActivity;
import com.example.mahmoud.carsparepartsonlineshopping.Activities.MainActivity;
import com.example.mahmoud.carsparepartsonlineshopping.R;
import com.example.mahmoud.carsparepartsonlineshopping.models.Model;
import com.example.mahmoud.carsparepartsonlineshopping.utils.FontUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    View v;
    SearchView searchText;


    SearchResultFragment searchResultFragment;
    SpinnersFragment spinnersFragment;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_search, container, false);
        searchText = (SearchView) v.findViewById(R.id.searchId);

        spinnersFragment = new SpinnersFragment();

        if (!spinnersFragment.isAdded()) {
            getActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment, spinnersFragment)
                    .commit();
        }

        searchText.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment, spinnersFragment)
                        .commit();
                return false;
            }
        });

        searchText.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!query.isEmpty()) {
                    searchResultFragment = SearchResultFragment.newInstance(query);
                    getActivity()
                            .getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment, searchResultFragment)
                            .commit();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                //sh3'lha
                if (newText.isEmpty()) {
                    getActivity()
                            .getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment, spinnersFragment)
                            .commit();
                }
                return false;
            }
        });

        return v;

    }

}

