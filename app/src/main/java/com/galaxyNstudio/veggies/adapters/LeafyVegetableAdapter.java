package com.galaxyNstudio.veggies.adapters;


        import java.util.List;

        import android.arch.lifecycle.ViewModelProviders;
        import android.content.Context;
        import android.os.AsyncTask;
        import android.support.v7.widget.RecyclerView;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.bumptech.glide.Glide;
        import com.galaxyNstudio.veggies.Dao.DatabaseClient;
        import com.galaxyNstudio.veggies.Entities.Cart;
        import com.galaxyNstudio.veggies.R;
        import com.galaxyNstudio.veggies.model.Product;
        import com.galaxyNstudio.veggies.storage.SharedPrefManager;
        import com.galaxyNstudio.veggies.tabs_fragments.LeafyVegetable_Fragment;
        import com.galaxyNstudio.veggies.tabs_fragments.RecyclerView_OnClickListener;
        import com.galaxyNstudio.veggies.viewModel.MainViewModel;


public class LeafyVegetableAdapter extends
        RecyclerView.Adapter<LeafyVegetableHolder> {// Recyclerview will extend to
    // recyclerview adapter
    private List<Product> leafyVegetableList;
    private Context context;
    private Button btn_add, img_add, img_remove;
    private TextView item_count;
    private Boolean isProductExist;
    private Integer productQty;
    private Double cartTotal=0.0;


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
                        int counter = SharedPrefManager.getInstance(context).getCounter(model.getId()) + 1;
                        SharedPrefManager.getInstance(context).setCounter(model.getId(), counter);
                        holder.plusMinus.setVisibility(View.VISIBLE);
                        holder.addButton.setVisibility(View.GONE);
                        holder.counter.setText(String.valueOf(SharedPrefManager.getInstance(context).getCounter(model.getId())));
                        isProductExists(model.getId(),model,"add");
                        Log.i("Cart total :",String.valueOf(cartTotal));
                        //holder.cartTotalAmount.setText(String.valueOf(cartTotal));
                        cartTotal=0.0;
                        //getCartItemCount();
                        //cartTotal=cartTotal+50;

                        break;

                    case R.id.img_add:
                        int counter2 = SharedPrefManager.getInstance(context).getCounter(model.getId()) + 1;
                        SharedPrefManager.getInstance(context).setCounter(model.getId(), counter2);
                        holder.counter.setText(String.valueOf(SharedPrefManager.getInstance(context).getCounter(model.getId())));
                        isProductExists(model.getId(),model,"add");
                        Log.i("Cart total :",String.valueOf(cartTotal));
                        cartTotal=0.0;
                        //holder.cartTotalAmount.setText(String.valueOf(cartTotal));
                        //getCartItemCount();
                        //holder.cartTotalAmount.setText(String.valueOf(cartTotal));
                        break;

                    case R.id.img_remove:
                        int counter1 = SharedPrefManager.getInstance(context).getCounter(model.getId());
                        isProductExists(model.getId(),model,"remove");
                        Log.i("Cart total :",String.valueOf(cartTotal));
                        //holder.cartTotalAmount.setText(String.valueOf(cartTotal));
                        cartTotal=0.0;
                        //getCartItemCount();
                        //holder.cartTotalAmount.setText(String.valueOf(cartTotal));
                        if (counter1 == 1) {
                            SharedPrefManager.getInstance(context).setCounter(model.getId(), counter1 - 1);
                            holder.plusMinus.setVisibility(View.GONE);
                            holder.addButton.setVisibility(View.VISIBLE);
                        } else {
                            SharedPrefManager.getInstance(context).setCounter(model.getId(), counter1 - 1);
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

    private void addRemoveproductInFromcart(final Product model, final String addOrRemove) {
        class addToCart extends AsyncTask<Void, Void, Void>{
            @Override
            protected Void doInBackground(Void... voids) {

                if(addOrRemove.equals("add")){
                    if(!isProductExist) {
                        // add fresh product with qty=1
                        productQty=productQty+1;
                        final Cart cart = new Cart(model.getId(), model.getProductName(), model.getOldPrice(), model.getNewPrice(), model.getAvailability(), model.getCategory(), model.getImage(), productQty);
                        DatabaseClient.getInstance(context).getAppDatabase()
                                .cartDao()
                                .insert(cart);
                        Log.i("Updatd Cart Prod  id :",model.getId()+"  Qty :"+productQty);

                        productQty=null;
                        isProductExist=null;

                    }
                    else{
                        // update qty of existing product
                        productQty=productQty+1;
                        DatabaseClient.getInstance(context).getAppDatabase()
                                .cartDao()
                                .updateProductQty(productQty,model.getId());
                        productQty=null;
                        isProductExist=null;

                    }
                }
                else if(addOrRemove.equals("remove")){
                    if(productQty == 1){
                        //remove product from offline cart
                        productQty=productQty-1;
                        DatabaseClient.getInstance(context).getAppDatabase()
                                .cartDao()
                                .removeProductFromCart(model.getId());
                        productQty=null;
                        isProductExist=null;
                    }
                    else{
                        //decrement product qty
                        productQty=productQty-1;
                        DatabaseClient.getInstance(context).getAppDatabase()
                                .cartDao()
                                .updateProductQty(productQty,model.getId());
                        productQty=null;
                        isProductExist=null;
                    }
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void Void) {
                super.onPostExecute(Void);
                getCartItemCount();
            }

        }
        addToCart obj = new addToCart();
        obj.execute();
    }

    private void getProductQty(final String productId,final Product model,final String addOrRemove) {
        class GetProdQty extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... voids) {

                // check if product already exists ?


                //get product qty from sqlite
                productQty = DatabaseClient.getInstance(context).getAppDatabase()
                        .cartDao()
                        .getproductQty(productId);

                return null;
            }


            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                addRemoveproductInFromcart(model,addOrRemove);
            }
        }
        GetProdQty obj = new GetProdQty();
        obj.execute();



    }

    private Boolean isProductExists(final String productId, final Product model, final String addOrRemove) {
        class CheckProductExist extends AsyncTask<Void, Void, Integer> {
            @Override
            protected Integer doInBackground(Void... voids) {

                Integer result=DatabaseClient.getInstance(context).getAppDatabase()
                        .cartDao()
                        .checkProductExists(productId);
                return result;
            }

            @Override
            protected void onPostExecute(Integer result) {
                super.onPostExecute(result);
                if(result == null)
                    isProductExist=Boolean.FALSE;
                else
                    isProductExist=Boolean.TRUE;

                // add cart item to squlite database

                getProductQty(model.getId(),model,addOrRemove);
            }

        }
        CheckProductExist obj = new CheckProductExist();
        obj.execute();

        return isProductExist;
    }

    @Override
    public LeafyVegetableHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem, parent, false);
        return new LeafyVegetableHolder(view);
    }

    public void getCartItemCount() {


        class GetTasks extends AsyncTask<Void, Void, List<Cart>> {

            @Override
            protected List<Cart> doInBackground(Void... voids) {
                List<Cart> taskList = DatabaseClient
                        .getInstance(context)
                        .getAppDatabase()
                        .cartDao()
                        .getAll();
                return taskList;
            }

            @Override
            protected void onPostExecute(List<Cart> tasks) {
                super.onPostExecute(tasks);
                Log.i("Total products in cart",String.valueOf(tasks.size()));
                for(Cart c: tasks){
                    for(int i=0;i<c.getQty();i++){
                        cartTotal=cartTotal+c.getNewPrice();
                    }
                }
/*
                Toast.makeText(context, "Cart Total :"+cartTotal, Toast.LENGTH_SHORT).show();
*/
                LeafyVegetable_Fragment.calculateTotal(cartTotal);




            }
        }

        GetTasks gt = new GetTasks();
        gt.execute();

    }


}


