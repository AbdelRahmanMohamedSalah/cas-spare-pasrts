package com.example.mahmoud.carsparepartsonlineshopping.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.mahmoud.carsparepartsonlineshopping.R;
import com.example.mahmoud.carsparepartsonlineshopping.models.CartItem;
import com.example.mahmoud.carsparepartsonlineshopping.utils.StaticConfig;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    ArrayList<CartItem> cartArrayList;
    Context context;
    TextView countProduct;

    public CartAdapter(ArrayList<CartItem> cartArrayList, Context context, TextView product) {
        this.cartArrayList = cartArrayList;
        this.context = context;
        this.countProduct = product;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, price, quantity;
        ElegantNumberButton elegantNumberButton;

        public ViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.nameCart);
            price = (TextView) itemView.findViewById(R.id.priceCart);
            quantity = (TextView) itemView.findViewById(R.id.quantityCart);
            elegantNumberButton = itemView.findViewById(R.id.cartElegantButton);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        final CartItem cartItem = cartArrayList.get(position);
        System.out.println(cartItem.product.getName());
        holder.name.setText(cartItem.product.getName());
        holder.price.setText("Price: " + cartItem.product.getPrice());
        holder.elegantNumberButton.setNumber(String.valueOf(cartItem.quantity));
        final Integer total = cartItem.quantity * Integer.parseInt(cartItem.product.getPrice());
        holder.quantity.setText(String.valueOf(total));
        coutnTotalPrice();
        holder.elegantNumberButton.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
            @Override
            public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
                holder.quantity.setText(String.valueOf(Integer.parseInt(cartItem.product.getPrice()) * newValue));
                StaticConfig.items.get(cartItem.product.id).quantity = newValue;
                coutnTotalPrice();
            }
        });

    }

    private void coutnTotalPrice() {
        int myCounter = 100;
        for (int count = 0; count < cartArrayList.size(); count++) {
            int supTotal = cartArrayList.get(count).quantity * Integer.parseInt(cartArrayList.get(count).product.getPrice());
            myCounter += supTotal;

        }
        countProduct.setText(String.valueOf(myCounter) + " LE");

    }

    @Override
    public int getItemCount() {
        return cartArrayList.size();
    }
}
