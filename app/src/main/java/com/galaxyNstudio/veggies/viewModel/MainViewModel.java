package com.galaxyNstudio.veggies.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

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
        return repository.getcityLiveDataLiveData();
    }

    public LiveData<List<Product>> getLeafyVegetables() {
        return repository.getLeafyVegetableLiveData();
    }


}
