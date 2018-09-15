package com.example.abiralrai.fitdiaryproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

public class FoodItem extends AppCompatActivity {

    private static final String TAG = "FoodItem";
    TextView food_item_title;
    TextView number_of_servings;
    TextView food_item_calories;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_item);
        Log.d(TAG, "onCreate: started.");

        food_item_title = findViewById(R.id.food_item_title);
        number_of_servings = findViewById(R.id.item_serving_size);
        food_item_calories = findViewById(R.id.total_calories);

        String foodLabel = getIntent().getExtras().getString("foodLabel");
        String foodWeight = getIntent().getExtras().getString("foodWeight");
        String calories = getIntent().getExtras().getString("calories");
        food_item_title.setText(foodLabel);
        number_of_servings.setText(foodWeight);
        food_item_calories.setText(calories);


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    // Returns back to previous intent
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            // Ends activity
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
