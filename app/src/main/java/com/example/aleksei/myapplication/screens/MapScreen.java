package com.example.aleksei.myapplication.screens;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aleksei.myapplication.Bar;
import com.example.aleksei.myapplication.BarFinder;
import com.example.aleksei.myapplication.MainActivity;
import com.example.aleksei.myapplication.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;
import com.google.maps.model.DirectionsResult;

import java.util.List;

/**
 * Created by aleksei on 28/10/17.
 */

public class MapScreen extends Screen implements OnMapReadyCallback {
    public final static String TAG = "map_screen";

    private GoogleMap mMap;
    private Location myLocation;
    private TextView timeTextView;

    boolean locationDetected = false;

    public Bar barHeadingTo;

    private BarFinder barFinder = new BarFinder(this);
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 0;

    int[] pics;

    Button uberBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (MainActivity.isMale) {
            pics = new int[]{R.drawable.man1, R.drawable.man2, R.drawable.man3, R.drawable.man4, R.drawable.man5};
        } else {
            pics = new int[]{R.drawable.woman1, R.drawable.woman2, R.drawable.woman3, R.drawable.woman4, R.drawable.woman5};
        }

        View view = inflater.inflate(R.layout.map_screen, container, false);
        timeTextView = view.findViewById(R.id.time_text_view);
        Button hereBtn = view.findViewById(R.id.here_btn);
        uberBtn = view.findViewById(R.id.uber_btn);

        ImageView imageView = view.findViewById(R.id.imageView2);


        int index = (int) (MainActivity.getTotalUnits() / (MainActivity.capacity / pics.length));
        int clampedValue = Math.max(0, Math.min(pics.length - 1, index));
        imageView.setImageResource(pics[clampedValue]);
        hereBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.barsBeenTo.add(barHeadingTo);

                MainActivity.screenManager.setScreen((AppCompatActivity) getActivity(), ScreenTypes.ORDER);
            }
        });


        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //attaching the map
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setMapToolbarEnabled(false);
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "permission initially granted");
            afterPermissionGranted();
        } else {
            Log.d(TAG, "permission requested");
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);

                // MY_PERMISSIONS_REQUEST_LOCATION is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }


    public void goToBar(Bar bar) {
        mMap.addMarker(new MarkerOptions().position(bar.location).title(bar.name));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(bar.location));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

        final PackageManager pm = getContext().getPackageManager();

        uberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    pm.getPackageInfo("com.ubercab", PackageManager.GET_ACTIVITIES);
                    String uri = "uber://?action=setPickup&pickup=my_location&dropoff[latitude]=" + barHeadingTo.location.latitude +
                            "&dropoff[longitude]=" + barHeadingTo.location.longitude;
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(uri));
                    startActivity(intent);
                } catch (PackageManager.NameNotFoundException e) {
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.ubercab")));
                    } catch (android.content.ActivityNotFoundException anfe) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=com.ubercab")));
                    }
                }
            }
        });
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "OnCallback Permission granted");
                    if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        Log.e(TAG, "OnCallback Returned from permission");
                        return;
                    }
                    Log.d(TAG, "OnCallback My location enabled");

                    afterPermissionGranted();
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }

    }

    private void afterPermissionGranted() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);

        mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                myLocation = location;

                if (!locationDetected) {
                    locationDetected = true;
                    barFinder.findNearestBar(myLocation);
                }
            }
        });
    }

    public void drawPathTobar(DirectionsResult result) {
        List<LatLng> decodedPath = PolyUtil.decode(result.routes[0].overviewPolyline.getEncodedPath());
        mMap.addPolyline(new PolylineOptions().addAll(decodedPath));

        String time = "Walking time : " + result.routes[0].legs[0].duration.humanReadable;
        timeTextView.setText(time);
    }
}