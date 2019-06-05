package com.galaxyNstudio.veggies;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.galaxyNstudio.veggies.tabs.Data_Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on : Feb 26, 2019
 * Author     : AndroidWave
 */
public class MainViewModel extends AndroidViewModel {
    private BlogRepository movieRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        movieRepository = new BlogRepository(application);
    }

    public LiveData<ArrayList<Data_Model>> getAllBlog() {
        return movieRepository.getMutableLiveData();
    }


}
