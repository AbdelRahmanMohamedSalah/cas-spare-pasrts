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
import com.example.mahmoud.carsparepartsonlineshopping.R;
import com.example.mahmoud.carsparepartsonlineshopping.models.Model;
import com.example.mahmoud.carsparepartsonlineshopping.utils.FontUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SpinnersFragment extends Fragment {

    private CardView filterContainer;
    private Spinner manu;
    private Spinner model;
    private Spinner submodel;
    private Spinner year;
    private Spinner category;
    private Button searchBtn;

    ArrayAdapter<String> adapterManu, subModelAdapter, yearAdapter, categoryAdapter;
    ArrayAdapter<Model> adapterModel;
    DatabaseReference mReference = FirebaseDatabase.getInstance().getReference();

    public SpinnersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_spinners, container, false);
        initView(v);
        connectToFirebaseAndInitSpinners();

        return v;
    }

    private void connectToFirebaseAndInitSpinners() {


        manu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final AdapterView<?> parent, View view, final int position, long id) {
                if (!parent.getSelectedItem().toString().equals("Manufacture"))
                    getModel(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        model.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Model m = (Model) parent.getItemAtPosition(position);
                if (!m.getModel_Name().equals("Model")) {
                    getSubModel(m.getModel_id());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        submodel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!parent.getSelectedItem().toString().equals("SubModel")) {
                    get_year(parent.getSelectedItem().toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!parent.getSelectedItem().toString().equals("Year")) {
                    getCategory(parent.getItemAtPosition(position).toString());
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = manu.getSelectedItem().toString()
                        + model.getSelectedItem().toString()
                        + submodel.getSelectedItem().toString()
                        + year.getSelectedItem().toString();

                startActivity(new Intent(getActivity(), FilteredItemsActivity.class).putExtra("query", query).putExtra("category", category.getSelectedItem().toString()));
            }
        });

        getManu();

    }

    private void getManu() {

        final ArrayList<String> m = new ArrayList<>();

        adapterManu = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, m) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                TextView t = (TextView) v.findViewById(android.R.id.text1);
                FontUtils.setPoppinsFont(t);

                return v;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View v = super.getDropDownView(position, convertView, parent);
                TextView t = (TextView) v.findViewById(android.R.id.text1);
                FontUtils.setPoppinsFont(t);
                return v;
            }

        };
        adapterManu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mReference.child("Manufacture").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                m.clear();

                for (DataSnapshot ss : dataSnapshot.getChildren()) {
                    m.add(ss.getKey());
                }
                manu.setAdapter(adapterManu);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void getCategory(String s) {
        s = manu.getSelectedItem().toString() + model.getSelectedItem().toString() + submodel.getSelectedItem().toString() + s;
        final ArrayList<String> m = new ArrayList<>();

        categoryAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, m) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View v = super.getView(position, convertView, parent);

                TextView t = (TextView) v.findViewById(android.R.id.text1);
                FontUtils.setPoppinsFont(t);

                return v;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View v = super.getDropDownView(position, convertView, parent);
                TextView t = (TextView) v.findViewById(android.R.id.text1);
                FontUtils.setPoppinsFont(t);
                return v;
            }

        };
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mReference.child("Category").child(s).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                m.clear();

                for (DataSnapshot ss : dataSnapshot.getChildren()) {
                    m.add(ss.getKey());
                }
                category.setAdapter(categoryAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    private void getSubModel(String s) {
        final ArrayList<String> m = new ArrayList<>();
        subModelAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, m) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View v = super.getView(position, convertView, parent);

                TextView t = (TextView) v.findViewById(android.R.id.text1);
                FontUtils.setPoppinsFont(t);

                return v;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View v = super.getDropDownView(position, convertView, parent);
                TextView t = (TextView) v.findViewById(android.R.id.text1);
                FontUtils.setPoppinsFont(t);
                return v;
            }

        };

        subModelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mReference.child("Model").child(s).child("sub-model").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                m.clear();
                for (final DataSnapshot ss : dataSnapshot.getChildren()) {
                    m.add(ss.getKey());
                }
                submodel.setAdapter(subModelAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    private void getModel(String s) {
        final ArrayList<Model> m = new ArrayList<>();
        adapterModel = new ArrayAdapter<Model>(getActivity(), android.R.layout.simple_spinner_item, m) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View v = super.getView(position, convertView, parent);

                TextView t = (TextView) v.findViewById(android.R.id.text1);
                FontUtils.setPoppinsFont(t);

                return v;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View v = super.getDropDownView(position, convertView, parent);
                TextView t = (TextView) v.findViewById(android.R.id.text1);
                FontUtils.setPoppinsFont(t);
                return v;
            }

        };

        adapterModel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        model.setAdapter(adapterModel);


        mReference.child("Manufacture").child(s).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                m.clear();
                for (final DataSnapshot ss : dataSnapshot.getChildren()) {

                    mReference.child("Model").child(ss.getKey()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Model model = new Model(dataSnapshot.getKey(), dataSnapshot.child("name").getValue(String.class));
                            m.add(model);
                            adapterModel.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    private void get_year(String s) {
        String yearString = manu.getSelectedItem().toString() + model.getSelectedItem().toString() + s;
        System.out.println(yearString);
        final ArrayList<String> y = new ArrayList<>();
        yearAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, y) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View v = super.getView(position, convertView, parent);

                TextView t = (TextView) v.findViewById(android.R.id.text1);
                FontUtils.setPoppinsFont(t);

                return v;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View v = super.getDropDownView(position, convertView, parent);
                TextView t = (TextView) v.findViewById(android.R.id.text1);
                FontUtils.setPoppinsFont(t);
                return v;
            }

        };
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mReference.child("Years").child(yearString).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                y.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    y.add(snapshot.getKey());
                }
                year.setAdapter(yearAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    private void initView(View v) {
        filterContainer = (CardView) v.findViewById(R.id.filter_container);
        manu = (Spinner) v.findViewById(R.id.manuid);
        model = (Spinner) v.findViewById(R.id.modelid);
        submodel = (Spinner) v.findViewById(R.id.submodelid);
        year = (Spinner) v.findViewById(R.id.yearid);
        category = (Spinner) v.findViewById(R.id.categoryid);
        searchBtn = (Button) v.findViewById(R.id.searchBtn);
    }

}
