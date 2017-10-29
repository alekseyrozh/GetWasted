package com.example.aleksei.myapplication.screens;

import android.support.v4.app.Fragment;

/**
 * Created by aleksei on 28/10/17.
 */

public abstract class Screen extends Fragment {

    public static Screen createScreen(ScreenTypes screenType) {
        switch (screenType)
        {
            case MAP:
                return new MapScreen();
            case ORDER:
                return new OrderScreen();
            case GET_WASTED:
                return new GetWastedScreen();
            case TEST:
                return new TestScreen();
            case RESULT:
                return new ResultScreen();
            case UBER:
                return new UberScreen();
            case LOADING_SCREEN:
                return new LoadingScreen();
            case FINAL:
                return new FinalScreen();
        }
        return null;
    }
}
