package com.example.aleksei.myapplication.screens;

/**
 * Created by aleksei on 28/10/17.
 */

public enum ScreenTypes {
    GET_WASTED, MAP, ORDER, TEST, RESULT, UBER, LOADING_SCREEN, FINAL;

    public String getTag() {
        switch (this)
        {
            case MAP:
                return MapScreen.TAG;
            case ORDER:
                return OrderScreen.TAG;
            case GET_WASTED:
                return GetWastedScreen.TAG;
            case TEST:
                return TestScreen.TAG;
            case RESULT:
                return ResultScreen.TAG;
            case UBER:
                return UberScreen.TAG;
            case FINAL:
                return FinalScreen.TAG;
        }
        return null;
    }
}
