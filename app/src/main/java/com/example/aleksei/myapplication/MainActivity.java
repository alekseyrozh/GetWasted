package com.example.aleksei.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import com.example.aleksei.myapplication.screens.GetWastedScreen;
import com.example.aleksei.myapplication.screens.OrderScreen;
import com.example.aleksei.myapplication.screens.Screen;
import com.example.aleksei.myapplication.screens.ScreenManager;
import com.example.aleksei.myapplication.screens.ScreenTypes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static ScreenManager screenManager = new ScreenManager();

    public static List<Bar> barsBeenTo = new ArrayList<>();

    public static String postcode;
    public static String address;
    public static int weight;
    public static int experience;
    public static boolean isMale;

    public static double capacity = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        screenManager.setScreen(this, ScreenTypes.LOADING_SCREEN, true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static double getTotalUnits() {
        double sum = 0;
        for (Bar bar : barsBeenTo)
            sum += bar.totalUnits();
        return sum;
    }

    public static Map<Drink, Integer> getAllDrinks() {
        Map<Drink, Integer> allDrinks = new HashMap<>();

        for (Bar bar : barsBeenTo) {
            for (Map.Entry<Drink, Integer> entry : bar.getDrinks().entrySet()) {
                Drink drink = entry.getKey();
                int numberOfDrinks = entry.getValue();
                int oldCount = allDrinks.getOrDefault(drink, 0);
                allDrinks.put(drink, oldCount + numberOfDrinks);
            }
        }
        return allDrinks;
    }
}
