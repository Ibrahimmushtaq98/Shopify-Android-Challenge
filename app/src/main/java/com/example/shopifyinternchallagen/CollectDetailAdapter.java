package com.example.shopifyinternchallagen;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class CollectDetailAdapter extends ArrayAdapter<CollectionDetail> {
    private Context context;
    private ArrayList<CollectionDetail> data;

    //Constructor
    public CollectDetailAdapter( Context context, ArrayList<CollectionDetail> data) {
        super(context, R.layout.show_collection_list);
        this.context = context;
        this.data = data;
    }

    /*
    function getCount returns the size of the collection
     */
    public int getCount(){
        return data.size();
    }


    /*
    function getItemId returns positions of the collections
    */
    public long getItemId(int position){
        return position;
    }

    /*
    function getView will return a custom view
     */
    public View getView(int position, View convertView, ViewGroup parent){
        CollectionDetail detailToDisplay = data.get(position);

        Log.d("CollectionDetailAdapter", "Title: " + detailToDisplay.getName());
        Log.d("CollectionDetailAdapter", "Collection Name: " + detailToDisplay.getCollectName());
        Log.d("CollectionDetailAdapter", "Type: " + detailToDisplay.getType());
        Log.d("CollectionDetailAdapter", "Inventory: " + detailToDisplay.getInvent());
        Log.d("CollectionDetailAdapter", "img: " + detailToDisplay.getImgProduct());

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.show_collection_list, parent,false);
        }

        //Grab the controls and initializes
        TextView lblTitle = (TextView)convertView.findViewById(R.id.txtTitle);
        TextView lblBody = (TextView)convertView.findViewById(R.id.txtBody);
        ImageView imgImage = (ImageView)convertView.findViewById(R.id.imgImageCollections);

        //Sets the text and images to the custom view
        lblTitle.setText(detailToDisplay.getName());
        lblBody.setText("Collections: " + detailToDisplay.getCollectName() + "\n"
                        + "Type: " + detailToDisplay.getType() + "\n"
                        + "Inventory: " + Integer.toString(detailToDisplay.getInvent()));

        Picasso.get().load(detailToDisplay.getImgProduct()).into(imgImage);

        return convertView;
    }
}
