package com.example.aleksei.myapplication.screens;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aleksei.myapplication.MainActivity;
import com.example.aleksei.myapplication.R;

/**
 * Created by aleksei on 29/10/17.
 */

public class UberScreen extends Screen {
    public static final String TAG = "uber_screen";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.uber, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        final String addressString = MainActivity.address.replace(" ", "%20") + "%2C" + MainActivity.postcode;

        final PackageManager pm = context.getPackageManager();

        Runnable r = new Runnable() {
            public void run() {
                try {
                    pm.getPackageInfo("com.ubercab", PackageManager.GET_ACTIVITIES);
                    String uri = "uber://?action=setPickup&pickup=my_location&dropoff[formatted_address]="+addressString;
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
        };
        Handler h = new Handler();
        h.postDelayed(r, 2000);
    }
}
