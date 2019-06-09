package com.galaxyNstudio.veggies.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.widget.Toast;

import com.galaxyNstudio.veggies.Dao.DatabaseClient;
import com.galaxyNstudio.veggies.Entities.Cart;
import com.galaxyNstudio.veggies.Entities.ProductEntity;
import com.galaxyNstudio.veggies.R;
import com.galaxyNstudio.veggies.api.RetrofitClient;
import com.galaxyNstudio.veggies.model.Data_Model;
import com.galaxyNstudio.veggies.model.Product;
import com.galaxyNstudio.veggies.responseWrapper.VegetableWrapper;
import com.galaxyNstudio.veggies.storage.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created on : Feb 26, 2019
 * Author     : AndroidWave
 */
public class Repository {
    private static ArrayList<Data_Model> cityList;
    private MutableLiveData<ArrayList<Data_Model>> cityLiveData ;
    private MutableLiveData<List<Cart>> cartItemsLiveData ;
    private Application application;
    String[] getTitle, getLocation, getYear;
    private static final int[] images = { R.drawable.p1,
            R.drawable.p1, R.drawable.p1, R.drawable.p1,
            R.drawable.p1, R.drawable.p1, R.drawable.p1,
            R.drawable.p1, R.drawable.p1 };

    // leafy vegetable
    private static List<Product> leafyVegetableArrayList;
    private MutableLiveData<List<Product>> leafyVegetableLivedata ;
    private final MutableLiveData<Boolean> isLoading=new MutableLiveData<>();



    public Repository(Application application) {
        this.application = application;
    }

    public MutableLiveData<Boolean> getIsLoading(){
        isLoading.setValue(true);
        return isLoading;
    }
    public MutableLiveData<List<Product>> getLeafyVegetableLiveData(String category) {
        if(leafyVegetableLivedata == null){
            leafyVegetableLivedata=new MutableLiveData<>();
            int flag=SharedPrefManager.getInstance(application).getVegetableFetchedFromAndSavedInSqlite(category);
            if(flag == 0){
                // fetch data from rest api
                fetchLeafyVegetableFromRestApiAndSaveInSqlite(category);


            }else if(flag == 1){
                fetchLeafyVegetableFromSqliteDb();
            }
        }

        return leafyVegetableLivedata;
    }

    public MutableLiveData<ArrayList<Data_Model>> getcityLiveData() {

        if(cityLiveData == null){
            cityLiveData = new MutableLiveData<>();
            // Getting the string array from strings.xml
            getTitle = application.getApplicationContext().getResources().getStringArray(R.array.title);
            getLocation = application.getApplicationContext().getResources().getStringArray(
                    R.array.location);
            getYear = application.getApplicationContext().getResources().getStringArray(
                    R.array.constructed_year);
            cityList = new ArrayList<Data_Model>();

            for (int i = 0; i < getTitle.length; i++) {
                cityList.add(new Data_Model(getTitle[i], getLocation[i],
                        getYear[i], images[i]));
            }
            cityLiveData.setValue(cityList);
        }


        return cityLiveData;
    }

    private void fetchLeafyVegetableFromRestApiAndSaveInSqlite(final String category) {
        // fetch leafy vegetable from web service
        Call<VegetableWrapper> call = RetrofitClient
                .getInstance()
                .getApi()
                .getVVegetablesByCategory(category);
        call.enqueue(new Callback<VegetableWrapper>() {
            @Override
            public void onResponse(Call<VegetableWrapper> call, Response<VegetableWrapper> response) {
                isLoading.setValue(false);
                if (response.code() == 200 && !response.body().getError()) {
                    leafyVegetableArrayList=response.body().getVegetableList();
                    leafyVegetableLivedata.setValue(leafyVegetableArrayList);

                    //save in sqlite
                    insertLeafyVegetableIntoSqliteDb(leafyVegetableArrayList);
                    // update flags
                    SharedPrefManager.getInstance(application).setVegetableFetchedFromAndSavedInSqlite(category,1);
                }
            }

            @Override
            public void onFailure(Call<VegetableWrapper> call, Throwable t) {

            }
        });
    }

    public void insertLeafyVegetableIntoSqliteDb(final List<Product> products){
        // add cart item to squlite database
        class addToCart extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                //creating a task
                List<ProductEntity> productEntities=new ArrayList<>();
                for(Product p : products){
                    ProductEntity productEntity=new ProductEntity(p.getId(),
                    p.getProductName(),
                            p.getNewPrice(),
                            p.getOldPrice(),
                            p.getCategory(),
                            p.getAvailability(),
                            p.getImage());
                    productEntities.add(productEntity);
                }


                //adding to database
                DatabaseClient.getInstance(application).getAppDatabase()
                        .vegetableDao()
                        .insertAllLeafyVegetable(productEntities);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(application, "Products added to sqlite", Toast.LENGTH_LONG).show();
            }

        }
        addToCart obj = new addToCart();
        obj.execute();
    }
    public void fetchLeafyVegetableFromSqliteDb() {


        class GetLeafyVegetables extends AsyncTask<Void, Void, List<ProductEntity>> {

            public List<Product> getProductList(){
                return productList;
            }
            List<Product> productList=new ArrayList<>();
            @Override
            protected List<ProductEntity> doInBackground(Void... voids) {
                List<ProductEntity> taskList = DatabaseClient
                        .getInstance(application)
                        .getAppDatabase()
                        .vegetableDao()
                        .getAllLeafyVegetable();
                return taskList;
            }

            @Override
            protected void onPostExecute(List<ProductEntity> productEntities) {
                super.onPostExecute(productEntities);
                for(ProductEntity p :productEntities){
                    Product product=new Product(p.getProductId(),
                            p.getVegetable_name(),
                            p.getNew_price(),
                            p.getOld_price(),
                            p.getCategory(),
                            p.getAvailability(),
                            p.getImage());
                    productList.add(product);
                }

                if(leafyVegetableLivedata.getValue() == null)
                leafyVegetableLivedata.setValue(productList);
            }
        }

        GetLeafyVegetables gt = new GetLeafyVegetables();
        gt.execute();
    }

    public LiveData<List<Cart>> getCartItems() {
        if(cartItemsLiveData == null){
            cartItemsLiveData=new MutableLiveData<>();
            fetchCartItems();
        }
        return cartItemsLiveData;
    }

    public void fetchCartItems() {


        class GetTasks extends AsyncTask<Void, Void, List<Cart>> {

            @Override
            protected List<Cart> doInBackground(Void... voids) {
                List<Cart> taskList = DatabaseClient
                        .getInstance(application)
                        .getAppDatabase()
                        .cartDao()
                        .getAll();
                return taskList;
            }

            @Override
            protected void onPostExecute(List<Cart> tasks) {
                super.onPostExecute(tasks);
                if(cartItemsLiveData.getValue() == null)
                cartItemsLiveData.setValue(tasks);
            }
        }

        GetTasks gt = new GetTasks();
        gt.execute();

    }
}
