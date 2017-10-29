package com.example.aleksei.myapplication.googleMapsApi;

import android.os.AsyncTask;

import com.example.aleksei.myapplication.Bar;
import com.example.aleksei.myapplication.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by aleksei on 28/10/17.
 */

public class GetBarTask extends AsyncTask<String, Void, Bar> {

    private PlacesApiFetcher placesApiFetcher;

    public GetBarTask(PlacesApiFetcher placesApiFetcher) {
        this.placesApiFetcher = placesApiFetcher;
    }

    @Override
    protected Bar doInBackground(String... strings) {

        URL url = null;
        try {
            url = new URL(strings[0]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Scanner scanner = null;
        try {
            scanner = new Scanner(url.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNextLine()) stringBuilder.append(scanner.nextLine());

        String responseStr = stringBuilder.toString();


        Bar bar = null;


        try {
            JSONObject response = new JSONObject(responseStr);

            JSONArray resultsArray = response.getJSONArray("results");

            int i = 0;
            JSONObject barJSONObject = resultsArray.getJSONObject(i);


            List<String> ids = new ArrayList<>();
            for(Bar bar1:MainActivity.barsBeenTo)
                ids.add(bar1.id);
            while (ids.contains(barJSONObject.getString("id"))) {
                i++;
                barJSONObject = resultsArray.getJSONObject(i);
            }
            String name = barJSONObject.getString("name");
            JSONObject geometry = barJSONObject.getJSONObject("geometry");
            JSONObject location = geometry.getJSONObject("location");
            double lat = location.getDouble("lat");
            double lng = location.getDouble("lng");
            String id = barJSONObject.getString("id");

            bar = new Bar(id, name, lat, lng);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return bar;
    }

    @Override
    protected void onPostExecute(Bar bar) {
        placesApiFetcher.barRetrieved(bar);
    }
}
