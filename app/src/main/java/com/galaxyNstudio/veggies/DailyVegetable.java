package com.galaxyNstudio.veggies;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DailyVegetable extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Product> productList;
    private static final String URL_DATA="https://progym007.000webhostapp.com/Api.php?apicall=getvegetables";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_vegetable);

        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        productList=new ArrayList<>();

        loadProductOnline();
    }

    public void loadProductOnline(){
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading data...");
        progressDialog.show();

        StringRequest stringRequest=new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try{
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("vegetables");
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject o=jsonArray.getJSONObject(i);
                        Product product=new Product(
                                o.getString("vegetable_name"),
                                o.getDouble("old_price"),
                                o.getDouble("new_price"),
                                o.getString("category"),
                                o.getString("availability"),
                                o.getString("image"));

                        productList.add(product);
                    }

                    adapter=new MyAdapter(productList,getApplicationContext());
                    recyclerView.setAdapter(adapter);
                }
                catch (Exception e ){}

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
