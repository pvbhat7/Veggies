package com.galaxyNstudio.veggies.activities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import com.galaxyNstudio.veggies.R;
import com.galaxyNstudio.veggies.model.Product;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyAdapterView> {

    private List<Product> products;
    private Context context;

    public MyAdapter(List<Product> products, Context context) {
        this.products = products;
        this.context = context;
    }

    @Override
    public MyAdapterView onCreateViewHolder(@NonNull ViewGroup parent, int i) {
    View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem,parent,false);
    return new MyAdapterView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterView viewHolder, int position) {
        Product product=products.get(position);
        viewHolder.productName.setText(product.getProductName());
        viewHolder.oldPrice.setText(String.valueOf(product.getOldPrice()));
        viewHolder.newPrice.setText(String.valueOf(product.getNewPrice()));

        Glide.with(context).load(product.getImage()).into(viewHolder.image);

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class MyAdapterView extends RecyclerView.ViewHolder{

        TextView productName;
        TextView oldPrice;
        TextView newPrice;
        ImageView image;


        public MyAdapterView(@NonNull View itemView) {
            super(itemView);

            productName=(TextView)itemView.findViewById(R.id.tv_product_name);
            oldPrice=(TextView)itemView.findViewById(R.id.tv_original_price);
            newPrice=(TextView)itemView.findViewById(R.id.tv_product_price);
            image=(ImageView)itemView.findViewById(R.id.img_product);


        }
    }
}
