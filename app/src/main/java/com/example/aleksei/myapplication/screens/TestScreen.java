package com.example.aleksei.myapplication.screens;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aleksei.myapplication.Bar;
import com.example.aleksei.myapplication.Drink;
import com.example.aleksei.myapplication.MainActivity;
import com.example.aleksei.myapplication.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by aleksei on 28/10/17.
 */

public class TestScreen extends Screen {
    public final static String TAG = "test_screen";

    TextView textViewTryNumber, textViewTextToType;
    Button buttonSubmit;
    EditText editTextSumitted;
    int numberOfTries = 1;
    int maxNumberOfTries = 3;

    int chosenIndex = -1;

    String[] texts = new String[]{"a", "b", "c", "d", "e"};

    List<String> testTexts = new ArrayList(Arrays.asList(texts));

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.test, container, false);

        textViewTryNumber = view.findViewById(R.id.textViewTryNumber);
        buttonSubmit = view.findViewById(R.id.buttonSubmitTest);
        textViewTextToType = view.findViewById(R.id.textViewTextToType);

        editTextSumitted = view.findViewById(R.id.editTextInput);


        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = editTextSumitted.getText().toString();
                String actualText = textViewTextToType.getText().toString();
                if (input.trim().equals(actualText)) {
                    Toast.makeText(getActivity(), "Correct!", Toast.LENGTH_SHORT).show();
                    MainActivity.screenManager.setScreen((AppCompatActivity)getActivity(), ScreenTypes.RESULT);
                } else {
                    lost();
                }
            }
        });

        setRandomTest();

        return view;
    }

    private void lost() {
        if (numberOfTries >= maxNumberOfTries) {
            reallyLost();
        } else {
            Toast.makeText(getActivity(), "Focus. Try harder.", Toast.LENGTH_SHORT).show();
            testTexts.remove(chosenIndex);
            numberOfTries++;
            setRandomTest();
        }

    }

    private void reallyLost() {
        //go to uber screen

        //"Don't worry, Uber is gonna come shortly";

        MainActivity.screenManager.setScreen((AppCompatActivity)getActivity(), ScreenTypes.UBER);
    }

    private void setRandomTest() {
        chosenIndex = new Random().nextInt(testTexts.size());
        textViewTextToType.setText(testTexts.get(chosenIndex));
        textViewTryNumber.setText(String.format("%d / %d", numberOfTries, maxNumberOfTries));
    }
}