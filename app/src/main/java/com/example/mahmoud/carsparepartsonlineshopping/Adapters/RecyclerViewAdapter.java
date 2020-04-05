package com.example.mahmoud.carsparepartsonlineshopping.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mahmoud.carsparepartsonlineshopping.R;
import com.example.mahmoud.carsparepartsonlineshopping.models.Items;
import com.example.mahmoud.carsparepartsonlineshopping.models.Products;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Products> mData;
//    private ArrayList<Items> mData_copy;

    public RecyclerViewAdapter(Context context, ArrayList<Products> mData) {
        this.context = context;
        this.mData = mData;
//        this.mData_copy = mData;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nameView;
        TextView descriptionView;
        TextView priceView;
        ImageView imgView;


        public MyViewHolder(View itemView) {
            super(itemView);

            nameView = (TextView) itemView.findViewById(R.id.nameId);
            descriptionView = (TextView) itemView.findViewById(R.id.modelId);
            priceView = (TextView) itemView.findViewById(R.id.priceId);
            imgView = (ImageView) itemView.findViewById(R.id.imgId);
        }


    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.items_shape, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Products items = mData.get(position);


        holder.nameView.setText(items.getName());

        holder.priceView.setText(items.getPrice());
        Picasso.with(context).load(items.getImage()).into(holder.imgView);


        //tmam kda


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

}
