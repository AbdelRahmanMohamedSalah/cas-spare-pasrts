package com.example.mahmoud.carsparepartsonlineshopping.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.mahmoud.carsparepartsonlineshopping.Adapters.AdapterItemProduct;
import com.example.mahmoud.carsparepartsonlineshopping.Adapters.RecyclerViewAdapter;
import com.example.mahmoud.carsparepartsonlineshopping.R;
import com.example.mahmoud.carsparepartsonlineshopping.models.Products;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchResultFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchResultFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private String mParam1;

    RecyclerView dataSearch;
    DatabaseReference mUserDatabase;
    AdapterItemProduct recyclerViewAdapter;
    FirebaseUser firebaseUser;



    ArrayList<Products> itemSearch;


    public SearchResultFragment() {
    }

    public static SearchResultFragment newInstance(String param1) {
        SearchResultFragment fragment = new SearchResultFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search_result, container, false);
        dataSearch = (RecyclerView) v.findViewById(R.id.recyclerId);


        mUserDatabase = FirebaseDatabase.getInstance().getReference();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        itemSearch = new ArrayList<Products>();
        dataSearch.setHasFixedSize(true);
        dataSearch.setLayoutManager(new LinearLayoutManager(getContext()));
        dataSearch.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));

        searchItem(mParam1);

        return v;
    }

    private void searchItem(final String searchedString) {
        mUserDatabase.child("product").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                itemSearch.clear();
                dataSearch.removeAllViews();

                //int count = 0;

                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    //String uid = data.getKey();
                    String name = data.child("name").getValue(String.class);
                    String manu = data.child("manu").getValue(String.class);
                    String model = data.child("model").getValue(String.class);
                    String price = data.child("price").getValue(String.class);
                    String subModel = data.child("subModel").getValue(String.class);
                    String subCategory = data.child("subCategory").getValue(String.class);
                    String image = data.child("image").getValue(String.class);
                    String year = data.child("year").getValue(String.class);
                    String categoryy = data.child("category").getValue(String.class);


                    if (name.toLowerCase().contains(searchedString.toLowerCase())) {
                        itemSearch.add(new Products(categoryy, image, manu, model, name, price, subCategory, subModel, year));
                        //count++;
                    }

//                    if (count == 15) {
//                        break;
//                    }

                    recyclerViewAdapter = new AdapterItemProduct(itemSearch, getContext());
                    dataSearch.setAdapter(recyclerViewAdapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
