package com.galaxyNstudio.veggies.tabs_fragments;

import java.util.ArrayList;
import java.util.Random;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.galaxyNstudio.veggies.viewModel.MainViewModel;
import com.galaxyNstudio.veggies.R;
import com.galaxyNstudio.veggies.adapters.ListView_Recycler_Adapter;
import com.galaxyNstudio.veggies.model.Data_Model;


public class LinearLayout_Fragment extends Fragment {
    private static View view;
    private static RecyclerView listRecyclerView;
    private static ArrayList<Data_Model> listArrayList;
    private static ListView_Recycler_Adapter adapter;

    private MainViewModel mainViewModel;

    // Images array for images
    private static final int[] images = { R.drawable.p1,
            R.drawable.p1, R.drawable.p1, R.drawable.p1,
            R.drawable.p1, R.drawable.p1, R.drawable.p1,
            R.drawable.p1, R.drawable.p1 };

    // String array for title, location, year
    String[] getTitle, getLocation, getYear;

    public LinearLayout_Fragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.linearlayout_fragment, container,
                false);

        init();
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getAllBlog().observe(this, new Observer<ArrayList<Data_Model>>() {
            @Override
            public void onChanged(@Nullable ArrayList<Data_Model> data_models) {
                adapter = new ListView_Recycler_Adapter(getActivity(), data_models);
                listRecyclerView.setAdapter(adapter);// set adapter on recyclerview
                adapter.notifyDataSetChanged();
            }
        });
        //populatRecyclerView();
        setHasOptionsMenu(true);// this method used to set option menu on
        // fragment
        return view;
    }

    // Initialize the view
    private void init() {

        /*// Getting the string array from strings.xml
        getTitle = getActivity().getResources().getStringArray(R.array.title);
        getLocation = getActivity().getResources().getStringArray(
                R.array.location);
        getYear = getActivity().getResources().getStringArray(
                R.array.constructed_year);
        listArrayList = new ArrayList<Data_Model>();*/

        listRecyclerView = (RecyclerView) view
                .findViewById(R.id.linear_recyclerview);
        listRecyclerView.setHasFixedSize(true);
        listRecyclerView
                .setLayoutManager(new LinearLayoutManager(getActivity()));// for
        // linear
        // data
        // display
        // we
        // use
        // linear
        // layoutmanager

    }

    // populate the list view by adding data to arraylist
    private void populatRecyclerView() {

        /*for (int i = 0; i < getTitle.length; i++) {
            listArrayList.add(new Data_Model(getTitle[i], getLocation[i],
                    getYear[i], images[i]));
        }*/
        adapter = new ListView_Recycler_Adapter(getActivity(), listArrayList);
        listRecyclerView.setAdapter(adapter);// set adapter on recyclerview
        adapter.notifyDataSetChanged();// Notify the adapter

    }

    // random generator method for generating data in int nos.
    private int RandomGenerator() {
        Random random = new Random();
        int randomNum = random.nextInt(9);

        return randomNum;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        /*getActivity().getMenuInflater().inflate(R.menu.main, menu);
        MenuItem add = menu.findItem(R.id.add);

        add.setOnMenuItemClickListener(new OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem arg0) {
                int value = RandomGenerator();// get random value from random
                // method

                // add random data to arraylist
                listArrayList.add(new Data_Model(getTitle[value],
                        getLocation[value], getYear[value], images[value]));
                adapter.notifyDataSetChanged();// finally notify adapter
                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);*/
    }
}