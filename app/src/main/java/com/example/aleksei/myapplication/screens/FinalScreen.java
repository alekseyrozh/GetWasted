package com.example.aleksei.myapplication.screens;

import android.os.Bundle;
import android.os.CountDownTimer;
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
import com.example.aleksei.myapplication.MainActivity;
import com.example.aleksei.myapplication.R;

import java.util.ArrayList;
import java.util.Arrays;
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
        String[] array = new String[] {"cat", "dog", "mouse"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, array);
        listViewAllDrinks.setAdapter(adapter);
    }
}