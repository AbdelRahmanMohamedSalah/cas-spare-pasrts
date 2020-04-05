package com.example.mahmoud.carsparepartsonlineshopping.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mahmoud.carsparepartsonlineshopping.R;
import com.example.mahmoud.carsparepartsonlineshopping.models.Order;

import java.util.ArrayList;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.VH> {

    public ArrayList<Order> orders;
    Context context;

    public MyOrderAdapter(ArrayList<Order> orders, Context context) {
        this.orders = orders;
        this.context = context;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.order, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Order order = orders.get(position);
        holder.orderId.setText(order.getId().substring(1, 12));
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    class VH extends RecyclerView.ViewHolder {

        TextView orderId;

        public VH(View itemView) {
            super(itemView);
            orderId = itemView.findViewById(R.id.order_id);
        }
    }
}
