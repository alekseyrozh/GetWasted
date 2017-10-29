package com.example.aleksei.myapplication.googleMapsApi;

import android.location.Location;

import com.example.aleksei.myapplication.BarFinder;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.LatLng;

/**
 * Created by aleksei on 28/10/17.
 */

public class RouteFinder {
    private final BarFinder barFinder;
    private GetPathToBarTask getPathToBarTask;

    public RouteFinder(BarFinder barFinder) {
        this.barFinder = barFinder;
    }

    public void findRoute(LatLng origin, LatLng destination) {
        getPathToBarTask = new GetPathToBarTask(this);
        getPathToBarTask.execute(origin, destination);
    }

    public void barPathRetrieved(DirectionsResult result) {
        barFinder.nextBarPathFound(result);
    }
}
