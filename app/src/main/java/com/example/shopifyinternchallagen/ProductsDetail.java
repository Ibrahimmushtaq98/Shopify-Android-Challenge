package com.example.shopifyinternchallagen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ProductsDetail extends AppCompatActivity implements CollectionDetailListener {

    private CollectDetailAdapter collectDetailAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_detail);

        //Will grab the Collection ID from the list, depending on what the user picked
        Intent prevIntent = getIntent();
        String name = prevIntent.getStringExtra("CollectName");
        String id = prevIntent.getStringExtra("id");

        String url = "https://shopicruit.myshopify.com/admin/collects.json?collection_id="+ id
                +"&page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6";

        //Download data for that part of the collections
        CollectionDetailDownload task = new CollectionDetailDownload();
        task.setCollectionDetailListener(this);
        task.execute(new String[] {url, name});

    }

    //This will allow to set custom adapters to a list view
    @Override
    public void taskUpdater(ArrayList<CollectionDetail> data){
        collectDetailAdapter = new CollectDetailAdapter(this, data);
        ListView product = (ListView)findViewById(R.id.listFeed);
        product.setAdapter(collectDetailAdapter);
    }
}
