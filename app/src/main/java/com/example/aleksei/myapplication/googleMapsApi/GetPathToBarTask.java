package com.example.aleksei.myapplication.googleMapsApi;

import android.os.AsyncTask;

import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;

import org.joda.time.DateTime;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by aleksei on 28/10/17.
 */

public class GetPathToBarTask extends AsyncTask<LatLng, Void, DirectionsResult> {

    private static final String KEY = "AIzaSyCPcVssST3ewHy0iwYjj929TQsEZ_XSdKk";

    private RouteFinder routeFinder;

    public GetPathToBarTask(RouteFinder routeFinder) {
        this.routeFinder = routeFinder;
    }

    private GeoApiContext getGeoContext() {
        GeoApiContext geoApiContext = new GeoApiContext();
        return geoApiContext.setQueryRateLimit(3)
                .setApiKey(KEY)
                .setConnectTimeout(1, TimeUnit.SECONDS)
                .setReadTimeout(1, TimeUnit.SECONDS)
                .setWriteTimeout(1, TimeUnit.SECONDS);
    }

    @Override
    protected DirectionsResult doInBackground(LatLng... origDest) {

        try {
            DirectionsResult result = DirectionsApi.newRequest(getGeoContext())
                    .mode(TravelMode.WALKING)
                    .origin(origDest[0])
                    .destination(origDest[1])
                    .departureTime(new DateTime())
                    .await();
            return result;
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }


    @Override
    protected void onPostExecute(DirectionsResult result) {
        routeFinder.barPathRetrieved(result);
    }
}
