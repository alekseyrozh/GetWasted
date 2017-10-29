package com.example.aleksei.myapplication;

public class Drink {

    public static final Drink BEER = new Drink("A pint of beer", 2.3, 182, R.drawable.pint);
    public static final Drink VODKA = new Drink("Shot of vodka", 1.0, 62, R.drawable.shot);
    public static final Drink WINE = new Drink("Glass of red wine", 2.3, 159, R.drawable.wine);
    public static final Drink MARTINI = new Drink("Martini", 0.8, 159, R.drawable.martini);

    public static final Drink[] ALL_DRINKS = {BEER, VODKA, WINE, MARTINI};

    private String type;

    private double units;
    private double kcal;

    private int image;

    @Override
    public int hashCode() {
        return type.hashCode();
    }

    public Drink(String type, double units, double kcal, int image) {
        this.type = type;
        this.units = units;
        this.kcal = kcal;
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public double getUnits() {
        return units;
    }

    public double getKcal() {
        return kcal;
    }

    public int getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "Drink{" +
                "type='" + type + '\'' +
                ", units=" + units +
                ", kcal=" + kcal +
                '}';
    }
}
