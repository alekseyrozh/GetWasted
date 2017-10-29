package com.example.aleksei.myapplication.screens;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.aleksei.myapplication.Bar;
import com.example.aleksei.myapplication.Drink;
import com.example.aleksei.myapplication.DrinkArrayAdapter;
import com.example.aleksei.myapplication.MainActivity;
import com.example.aleksei.myapplication.R;

import org.joda.time.Minutes;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by aleksei on 28/10/17.
 */

public class FinalScreen extends Screen {
    public final static String TAG = "final_screen";


    private ListView listViewAllDrinks;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.final_screen, container, false);


        listViewAllDrinks = view.findViewById(R.id.listViewAllDrinks);

        showDrinks();

        return view;
    }

    private void showDrinks() {
        Map<Drink, Integer> allDrinks = MainActivity.getAllDrinks();

        List<Pair<Drink, Integer>> drinkList = new ArrayList<>();
        for (Map.Entry<Drink, Integer> entry : allDrinks.entrySet()) {
            drinkList.add(new Pair<>(entry.getKey(), entry.getValue()));
        }

//        ArrayAdapter<Pair<Drink, Integer>> adapter = new ArrayAdapter<Pair<Drink, Integer>>(getContext(), android.R.layout.simple_list_item_1,
//                drinkList.toArray(Array.newInstance(Pair<Drink, Integer>,drinkList.size())));

       // ArrayAdapter<Pair<Drink, Integer>> adapter = new ArrayAdapter<Pair<Drink, Integer>>(getContext(), android.R.layout.simple_list_item_1, drinkList);
        drinkList.add(new Pair<Drink, Integer>(Drink.BEER, 3));
        listViewAllDrinks.setAdapter(new DrinkArrayAdapter(getContext(), R.layout.drink_list_item, drinkList));
    }
}