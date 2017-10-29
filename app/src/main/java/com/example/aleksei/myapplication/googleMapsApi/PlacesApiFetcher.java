package com.example.aleksei.myapplication.googleMapsApi;

import android.os.AsyncTask;

import com.example.aleksei.myapplication.Bar;
import com.example.aleksei.myapplication.BarFinder;
import com.example.aleksei.myapplication.MainActivity;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.model.DirectionsResult;

/**
 * Created by aleksei on 28/10/17.
 */

public class PlacesApiFetcher {
    private static final String API_KEY = "AIzaSyBgbL3-Qi_fh4KnT7xdlzZKR7N3vPuN4tY";

    private static final String REQUEST = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=%s,%s&key=%s&rankby=distance&type=bar&opennow=true";
    private final BarFinder barFinder;
    private GetBarTask getBarTask = new GetBarTask(this);


    public PlacesApiFetcher(BarFinder barFinder) {
        this.barFinder = barFinder;
    }

    public void initiateBarRetrieval(LatLng location) {
        String url = constructQuery(location);
        getBarTask.execute(url);
    }

    private String constructQuery(LatLng location) {
        return String.format(REQUEST, location.latitude, location.longitude, API_KEY);
    }

    public void barRetrieved(Bar bar) {
        barFinder.nextBarFound(bar);
    }


}
