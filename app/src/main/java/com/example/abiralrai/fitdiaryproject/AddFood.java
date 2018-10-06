package com.example.abiralrai.fitdiaryproject;

public class AddFood {

    private String food_name;
    private String calories;
    private String fat;
    private String carbs;
    private String protein;

    //Constructor

    public AddFood(String food_name, String calories, String fat, String carbs, String protein) {
        this.food_name = food_name;
        this.calories = calories;
        this.fat = fat;
        this.carbs = carbs;
        this.protein = protein;
    }

    //Setter, Getter


    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public String getCarbs() {
        return carbs;
    }

    public void setCarbs(String carbs) {
        this.carbs = carbs;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }
}
