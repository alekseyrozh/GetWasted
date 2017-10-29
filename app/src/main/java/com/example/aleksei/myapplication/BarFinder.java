package com.example.aleksei.myapplication;

import android.location.Location;

import com.example.aleksei.myapplication.googleMapsApi.PlacesApiFetcher;
import com.example.aleksei.myapplication.googleMapsApi.RouteFinder;
import com.example.aleksei.myapplication.screens.MapScreen;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.model.DirectionsResult;

/**
 * Created by aleksei on 28/10/17.
 */

public class BarFinder {
    private PlacesApiFetcher placesApiFetcher = new PlacesApiFetcher(this);
    private RouteFinder routeFinder = new RouteFinder(this);

    private MapScreen mapScreen;

    private LatLng lastLocation;

    private final static String MODE = "walking";

    public BarFinder(MapScreen mapScreen) {
        this.mapScreen = mapScreen;
    }


    public void findNearestBar(Location location) {
        findNearestBar(new LatLng(location.getLatitude(), location.getLongitude()));
    }

    public void findNearestBar(LatLng location) {
        placesApiFetcher.initiateBarRetrieval(location);
        lastLocation = location;
    }

    public void nextBarFound(Bar bar) {
        mapScreen.barHeadingTo = bar;
        mapScreen.goToBar(bar);
        routeFinder.findRoute(new com.google.maps.model.LatLng(lastLocation.latitude, lastLocation.longitude),
                new com.google.maps.model.LatLng(bar.location.latitude, bar.location.longitude));
    }

    public void nextBarPathFound(DirectionsResult result) {
        mapScreen.drawPathTobar(result);
    }

}
