package com.example.aleksei.myapplication;

import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by aleksei on 28/10/17.
 */

public class Bar {
    public final String id;
    public final String name;

    public LatLng location;

    private Map<Drink, Integer> drinks = new HashMap<>();

    private Drink drink;


    public Bar(String id, String name, double lat, double lng) {
        this.id = id;
        this.name = name;
        this.location = new LatLng(lat, lng);
    }

    public double totalKcal() {
        double kcal = 0;
        for (Map.Entry<Drink, Integer> entry : drinks.entrySet()) {
            Drink drink = entry.getKey();
            int numberOfDrinks = entry.getValue();
            kcal += drink.getKcal() * numberOfDrinks;
        }
        return kcal;
    }

    public double totalUnits() {
        double unit = 0;
        for (Map.Entry<Drink, Integer> entry : drinks.entrySet()) {
            Drink drink = entry.getKey();
            int numberOfDrinks = entry.getValue();
            unit += drink.getUnits() * numberOfDrinks;
        }
        return unit;
    }

    public void drink() {
        drink = Drink.ALL_DRINKS[new Random().nextInt(Drink.ALL_DRINKS.length)];
        drinks.put(drink, new Random().nextInt(3) + 1);
    }

    public Map<Drink, Integer> getDrinks() {
        return drinks;
    }

    public Drink getDrink() {
        return drink;
    }

    @Override
    public String toString() {
        return "Bar{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", location=" + location +
                '}' + drinks;
    }
}
