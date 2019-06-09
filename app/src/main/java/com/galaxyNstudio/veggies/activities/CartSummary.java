package com.galaxyNstudio.veggies.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.galaxyNstudio.veggies.Dao.DatabaseClient;
import com.galaxyNstudio.veggies.Entities.Cart;
import com.galaxyNstudio.veggies.R;
import com.galaxyNstudio.veggies.adapters.CartSummaryAdapter;
import com.galaxyNstudio.veggies.adapters.LeafyVegetableAdapter;
import com.galaxyNstudio.veggies.model.Product;
import com.galaxyNstudio.veggies.tabs_fragments.LeafyVegetable_Fragment;
import com.galaxyNstudio.veggies.viewModel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class CartSummary extends AppCompatActivity {

    TextView totalcartamount;
    private double cartTotal=0.00;
    private MainViewModel mainViewModel;
    private static RecyclerView listRecyclerView;
    private static CartSummaryAdapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_summary);
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getCartItems().observe(this, new Observer<List<Cart>>() {
            @Override
            public void onChanged(@Nullable List<Cart> cartItems) {
                listRecyclerView = (RecyclerView)findViewById(R.id.cartSummary_recyclerview);
                listRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));// for
                listRecyclerView.setHasFixedSize(true);
                adapter = new CartSummaryAdapter(getApplicationContext(), cartItems);
                listRecyclerView.setAdapter(adapter);// set adapter on recyclerview
                adapter.notifyDataSetChanged();
                mainViewModel.getIsLoading().setValue(false);
            }
        });


    }


}
