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
import com.example.mahmoud.carsparepartsonlineshopping.models.Products;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    ArrayList<Products> arrayProducts;
    Context context;

    public ItemAdapter(ArrayList<Products> arrayProducts, Context context) {
        this.arrayProducts = arrayProducts;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.items_shape, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder holder, int position) {
        Products products = arrayProducts.get(position);
        holder.nameId.setText(products.getName());
        holder.descriptionId.setText(products.getModel());
        holder.yearId.setText(products.getYear());
        holder.manuId.setText(products.getManu());
        holder.priceId.setText(products.getPrice());
        Picasso.with(context).load(products.getImage()).into(holder.imgId);

    }

    @Override
    public int getItemCount() {
        return arrayProducts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgId;
        TextView nameId, manuId, descriptionId, yearId, priceId;

        public ViewHolder(View itemView) {
            super(itemView);
            imgId = (ImageView) itemView.findViewById(R.id.imgId);
            nameId = (TextView) itemView.findViewById(R.id.nameId);
            manuId = (TextView) itemView.findViewById(R.id.manuId);
            descriptionId = (TextView) itemView.findViewById(R.id.modelId);
            yearId = (TextView) itemView.findViewById(R.id.yearId);
            priceId = (TextView) itemView.findViewById(R.id.priceId);

        }
    }
}
