package com.galaxyNstudio.veggies.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.galaxyNstudio.veggies.Entities.Cart;
import com.galaxyNstudio.veggies.model.Product;
import com.galaxyNstudio.veggies.repository.Repository;
import com.galaxyNstudio.veggies.model.Data_Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on : Feb 26, 2019
 * Author     : AndroidWave
 */
public class MainViewModel extends AndroidViewModel {
    private Repository repository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public LiveData<ArrayList<Data_Model>> getAllBlog() {
        return repository.getcityLiveData();
    }

    public LiveData<List<Product>> getLeafyVegetables(String category) {
        return repository.getLeafyVegetableLiveData(category);
    }

    public MutableLiveData<Boolean> getIsLoading(){
        MutableLiveData<Boolean> isLoading=repository.getIsLoading();
        return isLoading;
    }

    public LiveData<List<Cart>> getCartItems() {
        return repository.getCartItems();
    }



}
