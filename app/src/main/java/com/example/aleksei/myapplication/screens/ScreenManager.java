package com.example.aleksei.myapplication.screens;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.aleksei.myapplication.R;

/**
 * Created by aleksei on 28/10/17.
 */

public class ScreenManager {
    private ScreenTypes currentScreen;

    public void setScreen(AppCompatActivity activity, ScreenTypes to) {
        setScreen(activity, to, false);
    }

    public void setScreen(AppCompatActivity activity, ScreenTypes to, boolean firstScreen) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if (!firstScreen)
            transaction.setCustomAnimations(R.anim.in, R.anim.out);

        Fragment nextScreen = fragmentManager.findFragmentByTag(to.getTag());
        if (nextScreen == null)
            nextScreen = Screen.createScreen(to);

        transaction.replace(R.id.frame_for_fragment, nextScreen, to.getTag());
        transaction.commit();

        currentScreen = to;
    }
}
