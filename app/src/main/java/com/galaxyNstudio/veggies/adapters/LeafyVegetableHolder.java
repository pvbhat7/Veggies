
package com.galaxyNstudio.veggies.adapters;

        import android.support.v7.widget.RecyclerView;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.widget.ImageView;
        import android.widget.RelativeLayout;
        import android.widget.TextView;

        import com.galaxyNstudio.veggies.R;
        import com.galaxyNstudio.veggies.tabs_fragments.RecyclerView_OnClickListener;


public class LeafyVegetableHolder extends RecyclerView.ViewHolder implements
        View.OnClickListener {
    // View holder for list recycler view as we used in listview
    TextView productName;
    TextView oldPrice;
    TextView newPrice;
    ImageView image;
    public RelativeLayout listLayout;

    private RecyclerView_OnClickListener.OnClickListener onClickListener;

    public LeafyVegetableHolder(View view) {
        super(view);

        productName=(TextView)itemView.findViewById(R.id.tv_product_name);
        oldPrice=(TextView)itemView.findViewById(R.id.tv_original_price);
        newPrice=(TextView)itemView.findViewById(R.id.tv_product_price);
        image=(ImageView)itemView.findViewById(R.id.img_product);
        this.listLayout = (RelativeLayout) view.findViewById(R.id.rl_root);

        this.listLayout.setOnClickListener(this);

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