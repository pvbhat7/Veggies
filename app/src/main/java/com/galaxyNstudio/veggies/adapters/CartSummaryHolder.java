package com.galaxyNstudio.veggies.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.galaxyNstudio.veggies.R;
import com.galaxyNstudio.veggies.tabs_fragments.RecyclerView_OnClickListener;
public class CartSummaryHolder extends RecyclerView.ViewHolder implements
        View.OnClickListener {
    // View holder for list recycler view as we used in listview
    TextView productName;
    TextView oldPrice;
    TextView newPrice;
    ImageView image;
    public RelativeLayout listLayout;
    public TextView counter;
    public Button addButton;
    public ImageView plus,minus;
    public LinearLayout plusMinus;
    public TextView cartTotalAmount;


    private RecyclerView_OnClickListener.OnClickListener onClickListener;

    public CartSummaryHolder(View view) {
        super(view);

        productName=(TextView)itemView.findViewById(R.id.tv_product_name);
        oldPrice=(TextView)itemView.findViewById(R.id.tv_original_price);
        newPrice=(TextView)itemView.findViewById(R.id.tv_product_price);
        image=(ImageView)itemView.findViewById(R.id.img_product);
        this.listLayout = (RelativeLayout) view.findViewById(R.id.rl_root);

        this.listLayout.setOnClickListener(this);
        this.counter=(TextView)view.findViewById(R.id.item_count);
        this.addButton=(Button)view.findViewById(R.id.btn_add);
        this.plusMinus=(LinearLayout) view.findViewById(R.id.plusMinusLayout);
        this.minus=(ImageView)view.findViewById(R.id.img_remove);
        this.plus=(ImageView)view.findViewById(R.id.img_add);
        this.cartTotalAmount=(TextView)view.findViewById(R.id.cartTotalAmount);

        this.plus.setOnClickListener(this);
        this.minus.setOnClickListener(this);
        this.addButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        // setting custom listener
        if (onClickListener != null) {
            onClickListener.OnItemClick(v, getAdapterPosition());

        }

    }

    // Setter for listener
    public void setClickListener(
            RecyclerView_OnClickListener.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

}