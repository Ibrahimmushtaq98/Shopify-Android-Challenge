package com.example.shopifyinternchallagen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,CollectionListListener {

    public String url = "https://shopicruit.myshopify.com/admin/custom_collections.json?page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6";
    private CollectionListAdapter collectionListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Downloads the Collect List
        CollectionListDownload task = new CollectionListDownload();
        task.setCollectionListListener(this);
        task.execute(new String[] {url});
    }


    //After the collection List data has been downloaded, it will then update the listview
    //through a custom listview adapter;
    @Override
    public void taskUpdater(ArrayList<CollectionList> data) {
        collectionListAdapter = new CollectionListAdapter(this  , data);
        ListView feed = (ListView) findViewById(R.id.listView);
        feed.setAdapter(collectionListAdapter);
        feed.setOnItemClickListener(this);

    }


    //This function will allow users to click on the list, and will change to a different View
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        CollectionList collectionList = collectionListAdapter.getItem(position);

        String collectName = collectionList.getTitle();
        String collectID = collectionList.getId();

        Toast.makeText(this, "You pressed: " + collectName, Toast.LENGTH_SHORT).show();
        Log.d("MainActivity", "You pressed: " + collectName);
        Intent intent = new Intent(MainActivity.this, ProductsDetail.class);
        intent.putExtra("CollectName", collectName);
        intent.putExtra("id", collectID);
        startActivity(intent);
    }
}
