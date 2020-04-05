package com.example.mahmoud.carsparepartsonlineshopping.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mahmoud.carsparepartsonlineshopping.Activities.ActivityProduct;
import com.example.mahmoud.carsparepartsonlineshopping.Activities.ProductItems;
import com.example.mahmoud.carsparepartsonlineshopping.R;
import com.example.mahmoud.carsparepartsonlineshopping.models.Products;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterItemProduct extends RecyclerView.Adapter<AdapterItemProduct.ViewHolder> {

    ArrayList<Products> productsArrayList;
    Context context;

    public AdapterItemProduct(ArrayList<Products> productsArrayList, Context context) {
        this.productsArrayList = productsArrayList;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, model, manu, year, price;
        ImageView image;

        public ViewHolder(View itemView) {

            super(itemView);

            name = (TextView) itemView.findViewById(R.id.nameId);
            model = (TextView) itemView.findViewById(R.id.modelId);
            manu = (TextView) itemView.findViewById(R.id.manuId);
            year = (TextView) itemView.findViewById(R.id.yearId);
            price = (TextView) itemView.findViewById(R.id.priceId);
            image = (ImageView) itemView.findViewById(R.id.imgId);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.items_shape, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        final Products products = productsArrayList.get(position);
        holder.name.setText(products.getName());
        holder.model.setText(products.getModel());
        holder.manu.setText(products.getManu());
        holder.year.setText(products.getYear());
        holder.price.setText(products.getPrice() + " LE");
        Picasso.with(context).load(products.getImage()).into(holder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, ProductItems.class);
                intent.putExtra("item", products);


                ((ActivityProduct) context).startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productsArrayList.size();
    }


}
