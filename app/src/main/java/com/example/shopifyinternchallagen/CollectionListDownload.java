package com.example.shopifyinternchallagen;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class CollectionListDownload extends AsyncTask<String, Void, ArrayList<CollectionList>> {

    private CollectionListListener collectionListListener;

    public void setCollectionListListener(CollectionListListener collectionListListener){
        this.collectionListListener = collectionListListener;
    }

    @Override
    protected ArrayList<CollectionList> doInBackground(String... strings) {

        ArrayList<CollectionList> data = new ArrayList<>();
        String resultJson;

        
        try{

            //Connects to the Collection List api
            URL url = new URL(strings[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            BufferedReader input = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();

            //Reads through the json line by line and then appends it
            String line;
            while((line = input.readLine()) != null){
                sb.append(line + "\n");
            }
            resultJson = sb.toString();

            JSONObject jsonObject = new JSONObject(resultJson);
            JSONArray customCollections =jsonObject.getJSONArray("custom_collections");

            //This loop will take out useful info such as Collection name, images, id, handle, and
            //body
            for(int i = 0; i < customCollections.length(); i++){
                JSONObject c = customCollections.getJSONObject(i);
                JSONObject img = c.getJSONObject("image");
                //JSONArray img = c.getJSONArray("image");

                String id = c.getString("id");
                String handle = c.getString("handle");
                String title = c.getString("title");
                String image = img.getString("src");
                String body = c.getString("body_html");
                CollectionList collectionList = new CollectionList(id,handle,title,body,image);
                data.add(collectionList);
            }

            inputStream.close();
            connection.disconnect();


        }catch (Exception e){
            Log.d("ERROR", e.toString());
            e.printStackTrace();
        }

        return data;
    }

    @Override
    protected void onPostExecute(ArrayList<CollectionList> collectionLists) {
        collectionListListener.taskUpdater(collectionLists);
    }
}
