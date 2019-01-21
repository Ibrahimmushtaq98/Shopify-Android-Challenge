package com.example.shopifyinternchallagen;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class CollectionDetailDownload extends AsyncTask<String, Void, ArrayList<CollectionDetail>> {

    private CollectionDetailListener collectionDetailListener;

    /*
    function setCollectionDetailListener() will set a listener
    @Param collectionDetailListener : CollectionDetailListener contains a custom listener
     */
    public void setCollectionDetailListener(CollectionDetailListener collectionDetailListener){
        this.collectionDetailListener = collectionDetailListener;
    }

    @Override
    protected ArrayList<CollectionDetail> doInBackground(String... strings) {

        ArrayList<CollectionDetail> data = new ArrayList<>();
        String resultJsonOne;

        try {
            //Connect to the API
            URL url = new URL(strings[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            InputStream inputStream = conn.getInputStream();
            BufferedReader input = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();

            //reads the json line by line then appends it
            String line;
            while ((line = input.readLine()) != null){
                sb.append(line + "\n");
            }

            resultJsonOne = sb.toString();

            //This will grab the multiple products ID and will append it with commas
            JSONObject jsonObject = new JSONObject(resultJsonOne);
            JSONArray productsList = jsonObject.getJSONArray("collects");
            sb = new StringBuilder();
            for(int i = 0; i < productsList.length();i++){
                JSONObject c = productsList.getJSONObject(i);
                if(i != (productsList.length() - 1)){
                    sb.append(c.getString("product_id") + ",");
                }else{
                    sb.append(c.getString("product_id"));
                }
            }

            inputStream.close();
            conn.disconnect();

            Log.d("CollectionDetailDownload","ids: " +sb.toString());

            String urlTmp = "https://shopicruit.myshopify.com/admin/products.json?ids=" + sb.toString()
                    + "&page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6";


            //Connect with the now appended url with multiple ID
            url = new URL(urlTmp);
            conn = (HttpURLConnection) url.openConnection();
            inputStream = conn.getInputStream();
            input = new BufferedReader(new InputStreamReader(inputStream));
            sb = new StringBuilder();

            //Reads the json line by line, then appends it
            line = "";
            while ((line = input.readLine()) != null){
                sb.append(line + "\n");
            }

            resultJsonOne = sb.toString();

            jsonObject = new JSONObject(resultJsonOne);
            JSONArray products = jsonObject.getJSONArray("products");

            //This loop will go through the json array and take out useful info like
            //the name of the product, the image, the type of the products, and the inventory quantity
            for(int i = 0; i < products.length(); i++){
                JSONObject c = products.getJSONObject(i);
                JSONObject img = c.getJSONObject("image");

                String name = c.getString("title");
                String collection = strings[1];
                String imgO = img.getString("src");
                String type = c.getString("product_type");
                int invent = 0;

                JSONArray variants = c.getJSONArray("variants");
                for(int k = 0; k < variants.length(); k++){
                    JSONObject d = variants.getJSONObject(k);
                    invent+=d.getInt("inventory_quantity");
                }

                //With the useful info, it can now be added to the array list
                CollectionDetail collectionDetail = new CollectionDetail(name,invent,collection, imgO, type);
                data.add(collectionDetail);
            }

            inputStream.close();
            conn.disconnect();


        }catch (Exception e){
            Log.d("ERROR", e.toString());
            e.printStackTrace();
        }



        return data;
    }

    @Override
    protected void onPostExecute(ArrayList<CollectionDetail> collectionDetails) {
        collectionDetailListener.taskUpdater(collectionDetails);
    }
}
