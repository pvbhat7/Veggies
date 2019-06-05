package com.galaxyNstudio.veggies.repository;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;

import com.galaxyNstudio.veggies.R;
import com.galaxyNstudio.veggies.api.RetrofitClient;
import com.galaxyNstudio.veggies.model.Data_Model;
import com.galaxyNstudio.veggies.model.Product;
import com.galaxyNstudio.veggies.responseWrapper.MobileExist;
import com.galaxyNstudio.veggies.responseWrapper.VegetableWrapper;

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
    private Application application;
    String[] getTitle, getLocation, getYear;
    private static final int[] images = { R.drawable.p1,
            R.drawable.p1, R.drawable.p1, R.drawable.p1,
            R.drawable.p1, R.drawable.p1, R.drawable.p1,
            R.drawable.p1, R.drawable.p1 };

    // leafy vegetable
    private static List<Product> leafyVegetableArrayList;
    private MutableLiveData<List<Product>> leafyVegetableLivedata ;



    public Repository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Product>> getLeafyVegetableLiveData() {
        if(leafyVegetableLivedata == null){
            leafyVegetableLivedata=new MutableLiveData<>();

            // fetch leafy vegetable from web service
            Call<VegetableWrapper> call = RetrofitClient
                    .getInstance()
                    .getApi()
                    .getVVegetablesByCategory("Leafy Vegetable");
            call.enqueue(new Callback<VegetableWrapper>() {
                @Override
                public void onResponse(Call<VegetableWrapper> call, Response<VegetableWrapper> response) {
                    if (response.code() == 200 && !response.body().getError()) {
                        leafyVegetableArrayList=response.body().getVegetableList();
                        leafyVegetableLivedata.setValue(leafyVegetableArrayList);
                    }

                }

                @Override
                public void onFailure(Call<VegetableWrapper> call, Throwable t) {

                }
            });
        }
        return leafyVegetableLivedata;
    }

    public MutableLiveData<ArrayList<Data_Model>> getcityLiveDataLiveData() {

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
}
