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

public class CollectionListAdapter extends ArrayAdapter<CollectionList> {
    private Context context;
    private ArrayList<CollectionList> data;

    //Sets the List Adapters
    public CollectionListAdapter(Context context, ArrayList<CollectionList> data){
        super(context,R.layout.show_collection_list);
        this.data = data;
        this.context = context;

    }

    @Override
    public CollectionList getItem(int position){
        return data.get(position);
    }

    public int getCount(){
        return data.size();
    }

    public long getItemId(int positions){
        return positions;
    }

    //Inflate the view with info
    public View getView(int position, View convertView, ViewGroup parent){
        CollectionList listToDisplay = data.get(position);

        Log.d("CollectionListAdapter", "Title: " + listToDisplay.getTitle());
        Log.d("CollectionListAdapter", "Body: " + listToDisplay.getBody());
        Log.d("CollectionListAdapter", "Image: " + listToDisplay.getImage());

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.show_collection_list, parent, false);
        }

        //Grabs the control and initializes the variable
        TextView lblTitle = (TextView)convertView.findViewById(R.id.txtTitle);
        TextView lblBody = (TextView)convertView.findViewById(R.id.txtBody);
        ImageView imgImage = (ImageView)convertView.findViewById(R.id.imgImageCollections);

        //Sets the text and images
        lblTitle.setText(listToDisplay.getTitle());
        lblBody.setText(listToDisplay.getBody());
        Picasso.get().load(listToDisplay.getImage()).into(imgImage);

        return convertView;


    }
}
