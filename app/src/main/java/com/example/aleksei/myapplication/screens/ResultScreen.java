package com.example.aleksei.myapplication.screens;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aleksei.myapplication.Bar;
import com.example.aleksei.myapplication.MainActivity;
import com.example.aleksei.myapplication.R;

/**
 * Created by aleksei on 29/10/17.
 */

public class ResultScreen extends Screen {
    public static final String TAG = "result_screen";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.result, container, false);

        Button button = view.findViewById(R.id.next);
        TextView textView = view.findViewById(R.id.total);
        textView.setText(String.format("%.2f/%.2f units of happiness", MainActivity.getTotalUnits(), MainActivity.capacity));


        ImageView imageView = view.findViewById(R.id.imageView3);
        imageView.setImageDrawable(new MyDrawable());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.screenManager.setScreen((AppCompatActivity) getActivity(), ScreenTypes.MAP);
            }
        });

        return view;
    }

    private class MyDrawable extends Drawable {
        @Override
        public void draw(Canvas canvas) {
            Paint paint = new Paint();
            paint.setColor(Color.RED);

            Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.beer);
            Bitmap beer = Bitmap.createScaledBitmap(bm, canvas.getWidth(), canvas.getHeight(), false);

            if (MainActivity.isMale)
                bm = BitmapFactory.decodeResource(getResources(), R.drawable.man1);
            else
                bm = BitmapFactory.decodeResource(getResources(), R.drawable.woman1);

            Bitmap man = Bitmap.createScaledBitmap(bm, canvas.getWidth(),
                    canvas.getHeight(), false);

            double total = MainActivity.getTotalUnits();

            if (total <= MainActivity.capacity) {

                int w = canvas.getWidth(), h = canvas.getHeight();
                for (int i = 0; i < w; i++)
                    for (int j = h - 1; (h - (float) j) / h < total / MainActivity.capacity; j--) {
                        if (man.getPixel(i, j) == Color.BLACK) {
                            paint.setColor(beer.getPixel(i, j));
                            canvas.drawPoint(i, j, paint);
                        }
                    }

                paint.setColor(Color.BLACK);
                for (int i = 0; i < w; i++) {
                    for (int j = 0; (h - (float) j) / h >= total / MainActivity.capacity; j++) {
                        if (man.getPixel(i, j) == Color.BLACK) {
                            canvas.drawPoint(i, j, paint);
                        }
                    }

                }
            }
            else
            {
                //go home
                MainActivity.screenManager.setScreen((AppCompatActivity)getActivity(), ScreenTypes.FINAL);
            }
        }

        @Override
        public int getOpacity() {
            return PixelFormat.OPAQUE;
        }

        @Override
        public void setAlpha(int alpha) {
        }

        @Override
        public void setColorFilter(ColorFilter cf) {
        }

    }


}
