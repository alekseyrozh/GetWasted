package com.example.aleksei.myapplication.screens;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;

import static com.example.aleksei.myapplication.MainActivity.*;

import com.example.aleksei.myapplication.MainActivity;
import com.example.aleksei.myapplication.R;

/**
 * Created by aleksei on 28/10/17.
 */

public class GetWastedScreen extends Screen {
    public final static String TAG = "get_wasted_screen";

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.get_wasted_screen, container, false);

        // Inflate the layout for this fragment
        Button getWastedBtn = view.findViewById(R.id.get_wasted_button);
        final EditText weightText = view.findViewById(R.id.weight);
        final EditText postcodeText = view.findViewById(R.id.postcode);
        final EditText addressText = view.findViewById(R.id.address);
        final SeekBar experienceBar = view.findViewById(R.id.seekBar);
        final RadioButton male = view.findViewById(R.id.m);

        getWastedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.screenManager.setScreen((AppCompatActivity) getActivity(), ScreenTypes.MAP);
                weight = Integer.parseInt("0" + weightText.getText().toString());
                postcode = postcodeText.getText().toString();
                address = addressText.getText().toString();
                experience = experienceBar.getProgress();
                isMale = male.isChecked();
                capacity = 10 + weight/5 + experience*2;
            }
        });

        return view;
    }
}