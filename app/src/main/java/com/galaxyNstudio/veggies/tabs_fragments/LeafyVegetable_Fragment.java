package com.galaxyNstudio.veggies.tabs_fragments;

import java.util.ArrayList;
import java.util.List;
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

import com.galaxyNstudio.veggies.adapters.LeafyVegetableAdapter;
import com.galaxyNstudio.veggies.model.Product;
import com.galaxyNstudio.veggies.viewModel.MainViewModel;
import com.galaxyNstudio.veggies.R;
import com.galaxyNstudio.veggies.adapters.ListView_Recycler_Adapter;
import com.galaxyNstudio.veggies.model.Data_Model;


public class LeafyVegetable_Fragment extends Fragment {
    private static View view;
    private static RecyclerView listRecyclerView;
    private static ArrayList<Data_Model> listArrayList;
    private static LeafyVegetableAdapter adapter;

    private MainViewModel mainViewModel;

    public LeafyVegetable_Fragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.leafyvegetable_fragment, container,
                false);

        init();
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getLeafyVegetables().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(@Nullable List<Product> products) {
                adapter = new LeafyVegetableAdapter(getActivity(), products);
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

        listRecyclerView = (RecyclerView) view
                .findViewById(R.id.leafyVegetable_recyclerview);
        listRecyclerView.setHasFixedSize(true);
        listRecyclerView
                .setLayoutManager(new LinearLayoutManager(getActivity()));// for

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