package com.example.aleksei.myapplication.screens;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.aleksei.myapplication.Bar;
import com.example.aleksei.myapplication.Drink;
import com.example.aleksei.myapplication.MainActivity;
import com.example.aleksei.myapplication.R;

import org.w3c.dom.Text;

import java.util.Map;

/**
 * Created by aleksei on 28/10/17.
 */

public class OrderScreen extends Screen {
    public final static String TAG = "order_screen";

    TextView textView;
    TextView textViewWhatToOrder;
    ImageView imageView;
    ProgressBar progressBarThinking;
    TextView textViewUnitsKcal, textViewToOrder, textViewOrder;
    TextView textViewThinking;

    Button buttonCompleted;
    Button buttonNeedHelp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.order, container, false);


        Bar bar = MainActivity.barsBeenTo.get(MainActivity.barsBeenTo.size() - 1);
        bar.drink();

        textView = (TextView) view.findViewById(R.id.textViewUnitsKcal);
        textView.setText(String.format(" Units: %.2f", bar.totalUnits()));

        String whatToOrder = "";
        Map<Drink, Integer> drinks = bar.getDrinks();
        for (Map.Entry<Drink, Integer> entry : drinks.entrySet()) {
            Drink drink = entry.getKey();
            int numberOfDrinks = entry.getValue();
            whatToOrder += "" + numberOfDrinks + "x  " + drink.getType() + "     ";
        }

        textViewWhatToOrder = (TextView) view.findViewById(R.id.textViewToOrder);
        textViewWhatToOrder.setText(whatToOrder);

        imageView = (ImageView) view.findViewById(R.id.imageViewDrink);
        imageView.setBackgroundResource(bar.getDrink().getImage());

        textViewUnitsKcal = view.findViewById(R.id.textViewUnitsKcal);
        textViewToOrder = view.findViewById(R.id.textViewToOrder);
        textViewOrder = view.findViewById(R.id.textViewOrder);
        textViewThinking = view.findViewById(R.id.textViewThinking);

        progressBarThinking = view.findViewById(R.id.progressBarThinking);

        buttonCompleted = view.findViewById(R.id.buttonCompleted);
        buttonNeedHelp = view.findViewById(R.id.buttonHelp);

        buttonCompleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.screenManager.setScreen((AppCompatActivity) getActivity(), ScreenTypes.TEST);
            }
        });
        buttonNeedHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.screenManager.setScreen((AppCompatActivity) getActivity(), ScreenTypes.UBER);
            }
        });


        buttonCompleted.setEnabled(false);
        buttonNeedHelp.setEnabled(false);

        final String[] texts = {"", "Finding the best drink", "Thinking...", "Almost there..."};

        new CountDownTimer(4000, 1000) {
            int index = 0;

            public void onTick(long millisUntilFinished) {
                index++;
                if (index < texts.length)
                    textViewThinking.setText(texts[index]);
            }

            public void onFinish() {
                imageView.setVisibility(View.VISIBLE);
                textViewWhatToOrder.setVisibility(View.VISIBLE);
                textViewUnitsKcal.setVisibility(View.VISIBLE);
                textViewToOrder.setVisibility(View.VISIBLE);
                textViewOrder.setVisibility(View.VISIBLE);

                textViewThinking.setVisibility(View.INVISIBLE);
                progressBarThinking.setVisibility(View.INVISIBLE);

                buttonCompleted.setEnabled(true);
                buttonNeedHelp.setEnabled(true);
            }

        }.start();

        return view;
    }
}