package com.galaxyNstudio.veggies;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;

import com.galaxyNstudio.veggies.tabs.Data_Model;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created on : Feb 26, 2019
 * Author     : AndroidWave
 */
public class BlogRepository {
    private ArrayList<Data_Model> movies = new ArrayList<>();
    private static ArrayList<Data_Model> listArrayList;
    private MutableLiveData<ArrayList<Data_Model>> mutableLiveData ;
    private Application application;
    String[] getTitle, getLocation, getYear;
    private static final int[] images = { R.drawable.p1,
            R.drawable.p1, R.drawable.p1, R.drawable.p1,
            R.drawable.p1, R.drawable.p1, R.drawable.p1,
            R.drawable.p1, R.drawable.p1 };


    public BlogRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<ArrayList<Data_Model>> getMutableLiveData() {

        if(mutableLiveData == null){
            mutableLiveData = new MutableLiveData<>();
            // Getting the string array from strings.xml
            getTitle = application.getApplicationContext().getResources().getStringArray(R.array.title);
            getLocation = application.getApplicationContext().getResources().getStringArray(
                    R.array.location);
            getYear = application.getApplicationContext().getResources().getStringArray(
                    R.array.constructed_year);
            listArrayList = new ArrayList<Data_Model>();

            for (int i = 0; i < getTitle.length; i++) {
                listArrayList.add(new Data_Model(getTitle[i], getLocation[i],
                        getYear[i], images[i]));
            }
            mutableLiveData.setValue(listArrayList);
        }


        return mutableLiveData;
    }
}
