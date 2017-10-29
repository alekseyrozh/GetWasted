package com.example.aleksei.myapplication.screens;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.example.aleksei.myapplication.MainActivity;
import com.example.aleksei.myapplication.R;

/**
 * Created by aleksei on 29/10/17.
 */

public class LoadingScreen extends Screen {
    public final static String TAG = "map_screen";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Animation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(4000);

        View view = inflater.inflate(R.layout.loading_screen, container, false);
        ImageView imageView = view.findViewById(R.id.imageView);

        imageView.setAnimation(animation);
        imageView.animate();

        Runnable r = new Runnable() {
            public void run() {
                MainActivity.screenManager.setScreen((AppCompatActivity) getActivity(),ScreenTypes.GET_WASTED);
            }
        };

        Handler h = new Handler();
        h.postDelayed(r, 4000);

        return view;
    }
}
