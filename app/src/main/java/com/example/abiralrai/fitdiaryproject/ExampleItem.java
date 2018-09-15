package com.example.abiralrai.fitdiaryproject;


public class ExampleItem {
    private String food_label;
    private String food_weight;
    private String calories;

    public ExampleItem(String food_label, String food_weight, String calories) {
        this.food_label = food_label;
        this.food_weight = food_weight;
        this.calories = calories;
    }


    public String getFood_label() {
        return food_label;
    }

    public String getFood_weight() {
        return food_weight;
    }


    public String getCalories() {
        return calories;
    }
}
