package com.galaxyNstudio.veggies.adapters;


        import java.util.ArrayList;
        import java.util.List;

        import android.content.Context;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.bumptech.glide.Glide;
        import com.galaxyNstudio.veggies.R;
        import com.galaxyNstudio.veggies.activities.MyAdapter;
        import com.galaxyNstudio.veggies.model.Data_Model;
        import com.galaxyNstudio.veggies.model.Product;
        import com.galaxyNstudio.veggies.storage.SharedPrefManager;
        import com.galaxyNstudio.veggies.tabs_fragments.RecyclerView_OnClickListener;


public class LeafyVegetableAdapter extends
        RecyclerView.Adapter<LeafyVegetableHolder> {// Recyclerview will extend to
    // recyclerview adapter
    private List<Product> leafyVegetableList;
    private Context context;
    private Button btn_add,img_add,img_remove;
    private TextView item_count;

    public LeafyVegetableAdapter(Context context,
                                 List<Product> arrayList) {
        this.context = context;
        this.leafyVegetableList = arrayList;

    }

    @Override
    public int getItemCount() {
        return (null != leafyVegetableList ? leafyVegetableList.size() : 0);

    }

    @Override
    public void onBindViewHolder(LeafyVegetableHolder leafyVegetableHolder, int position) {

        final Product model = leafyVegetableList.get(position);

        final LeafyVegetableHolder holder = (LeafyVegetableHolder) leafyVegetableHolder;
        holder.productName.setText(model.getProductName());
        holder.oldPrice.setText(String.valueOf(model.getOldPrice()));
        holder.newPrice.setText(String.valueOf(model.getNewPrice()));
        Glide.with(context).load(model.getImage()).into(holder.image);

        // Implement click listener over layout
        holder.setClickListener(new RecyclerView_OnClickListener.OnClickListener() {

            @Override
            public void OnItemClick(View view, int position) {
                switch (view.getId()) {
                    case R.id.rl_root:

                        // Show a toast on clicking layout
                        Toast.makeText(context,
                                "You have clicked " + model.getProductName(),
                                Toast.LENGTH_LONG).show();
                        break;

                    case R.id.btn_add:
                        int counter=SharedPrefManager.getInstance(context).getCounter(model.getId())+1;
                        SharedPrefManager.getInstance(context).setCounter(model.getId(),counter);
                        holder.plusMinus.setVisibility(View.VISIBLE);
                        holder.addButton.setVisibility(View.GONE);
                        holder.counter.setText(String.valueOf(SharedPrefManager.getInstance(context).getCounter(model.getId())));
                    break;

                    case R.id.img_add:
                        int counter2=SharedPrefManager.getInstance(context).getCounter(model.getId())+1;
                        SharedPrefManager.getInstance(context).setCounter(model.getId(),counter2);
                        holder.counter.setText(String.valueOf(SharedPrefManager.getInstance(context).getCounter(model.getId())));
                        break;

                    case R.id.img_remove:
                        int counter1=SharedPrefManager.getInstance(context).getCounter(model.getId());
                        if(counter1 == 1){
                            holder.plusMinus.setVisibility(View.GONE);
                            holder.addButton.setVisibility(View.VISIBLE);
                        }
                        else{
                            SharedPrefManager.getInstance(context).setCounter(model.getId(),counter1-1);
                            holder.counter.setText(String.valueOf(SharedPrefManager.getInstance(context).getCounter(model.getId())));
                        }

                        break;

                    /*case R.id.list_delete:
                        // remove selected item
                        leafyVegetableList.remove(position);
                        notifyItemRemoved(position);
                        break;*/

                }
            }

        });

    }

    @Override
    public LeafyVegetableHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem,parent,false);
        return new LeafyVegetableHolder(view);
    }

}