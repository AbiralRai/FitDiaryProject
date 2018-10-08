package com.example.abiralrai.fitdiaryproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SearchView;

import com.example.abiralrai.fitdiaryproject.FatSecretImplementation.FatSecretGet;
import com.example.abiralrai.fitdiaryproject.FatSecretImplementation.FatSecretSearch;
import com.example.abiralrai.fitdiaryproject.SearchRowAdapter.FoodListRowAdapter;
import com.example.abiralrai.fitdiaryproject.SearchRowAdapter.Item;
import com.example.abiralrai.fitdiaryproject.SearchRowAdapter.SearchAdapter;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.felipecsl.quickreturn.library.QuickReturnAttacher;
import com.felipecsl.quickreturn.library.widget.QuickReturnAdapter;
import com.felipecsl.quickreturn.library.widget.QuickReturnTargetView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.parse.ParseUser;

import java.io.DataOutput;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.support.v4.app.Fragment;
import android.widget.TextView;

import bolts.Task;

public class DashboardActivity extends AppCompatActivity {


    private ListView mListView;
    static FoodListRowAdapter mFoodListAdapter;
    static  ArrayList<Item> mItem = new ArrayList<>();

    String name="";
    String food_name = "";
    String food_description = "";
    String[] row;
    String brand;
    String id = "";
    String food_id = "";
    String calories="";
    int total = 0;
    int net = 0;
    int goal = 2000;
    String EditGoal;

    TextView goalTextView;
    TextView consumeTextView;
    TextView netTextView;

    public static final String MY_PREFS_NAME = "MyPrefsFile";
    SharedPreferences prefs;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        Intent intent;
        switch (item.getItemId()) {
            case R.id.profileInfo:
                if(ParseUser.getCurrentUser() != null) {
                    intent = new Intent(getApplicationContext(), AccountActivity.class);
                    startActivity(intent);
                    return true;
                } else {
                    intent = new Intent(getApplicationContext(), FBAccountActivity.class);
                    startActivity(intent);
                    return true;
                }
            case R.id.logout:
                ParseUser.logOut();
                LoginManager.getInstance().logOut();
                 intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_search:
                intent = new Intent(getApplicationContext(), SearchResultActivity.class);
//                startActivity(intent);
                startActivityForResult(intent, 1);
                return true;
            default:
                return false;

        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        if(ParseUser.getCurrentUser() != null) {
            Log.i("onCreate: ", "AWE NOT NULL");
        } else {
            Log.i("onCreate: ", "FB NOT NULL");
        }


        goalTextView = (TextView) findViewById(R.id.calorie_goal_top);
        consumeTextView = (TextView) findViewById(R.id.calories_consumed_top);
        netTextView = (TextView) findViewById(R.id.net_calories_top);

        mListView = (ListView) findViewById(R.id.food_item_list);
//        mItem = new ArrayList<>();

        mFoodListAdapter = new FoodListRowAdapter(getApplicationContext(), mItem);
        mListView.setAdapter(mFoodListAdapter);
        registerForContextMenu(mListView);

        prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String restoredText = prefs.getString("item", null);

//        prefs.edit().clear().commit();

        if(restoredText != null) {
            name = prefs.getString("item", "No item defined");

            int storedNet = prefs.getInt("net", 2000);
            int storedTotal = prefs.getInt("total", 0);
            int storedGoal = prefs.getInt("goal", 2000);

            net = storedNet;
            total = storedTotal;
            goal = storedGoal;


            consumeTextView.setText(""+total);
            netTextView.setText(""+net);
            goalTextView.setText(""+goal);

            Gson gson = new Gson();
            TypeToken<ArrayList<Item>> token = new TypeToken<ArrayList<Item>>() {};

             mItem = gson.fromJson(name, token.getType());
            mFoodListAdapter = new FoodListRowAdapter(getApplicationContext(), mItem);
            mListView.setAdapter(mFoodListAdapter);

        }



        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int itemToDelete = position;


                Gson gson = new Gson();
                food_description = gson.toJson(mItem.get(itemToDelete));

                new AlertDialog.Builder(DashboardActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Are you sure?")
                        .setMessage("Do you want to delete this meal")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {


                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                mItem.remove(itemToDelete);
                                mFoodListAdapter.notifyDataSetChanged();


//                                 Regex - Splitting Calories to just number
                                Pattern p = Pattern.compile("Calories: (.*?)kcal");
                                Matcher m = p.matcher(food_description);

                                while (m.find()) {
                                    calories = m.group(1);
                                }

                                    int intCalories = Integer.parseInt(calories);

                                    Log.e("intCalories", String.valueOf(intCalories));
                                    total -= intCalories;
                                    net += intCalories;

                                    consumeTextView.setText(""+total);
                                    netTextView.setText(""+net);
//                                }

//                                Log.e("calories", calories);
//                                Log.e("Total", String.valueOf(total));
//                                Log.e("net", String.valueOf(net));
//                                Log.e("food", food_description);
//                                Log.e("description", food_description);


                                Gson gson2 = new Gson();
                                String json = gson2.toJson(mItem);

                                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                                editor.putString("item", json);
                                editor.putInt("net",net);
                                editor.putInt("total", total);
                                editor.putInt("goal", goal);
                                editor.apply();



                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                return true;
            }
        });

        goalTextView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                AlertDialog.Builder alert = new AlertDialog.Builder(DashboardActivity.this);

                final EditText edittext = new EditText(DashboardActivity.this);
                alert.setTitle("Enter Your Calories Goals");
                alert.setView(edittext);

                alert.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        EditGoal = edittext.getText().toString();
                        goalTextView.setText(EditGoal);

                        goal = Integer.parseInt(EditGoal);
                        net = Integer.parseInt(EditGoal) - total;

                        netTextView.setText(""+net);

                    }
                });

                alert.setNegativeButton("Cancel", null);

                alert.show();


                return true;
            }
        });

        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK && requestCode == 1) {

            if(data != null) {

                food_name = data.getStringExtra("food_name");
                food_description= data.getStringExtra("food_description");
                String[] row = food_description.split("-");
                brand = data.getStringExtra("brand");
                food_id = data.getStringExtra("food_id");
                Item a = new Item(food_name, row[1].substring(1), brand, food_id);

                // Regex - Splitting Calories to just number
                Pattern p = Pattern.compile("Calories: (.*?)kcal");
                Matcher m = p.matcher(food_description);

                while (m.find()) {
                    calories = m.group(1);
                }

                int intCalories = Integer.parseInt(calories);

                total += intCalories;
                net = goal - total;

                Log.e("NET", String.valueOf(net));
                Log.e("TOTAL", String.valueOf(total));
                Log.e("GOAL", String.valueOf(goal));


                consumeTextView.setText(""+total);
                netTextView.setText(""+net);

                mItem.add(a);
                mFoodListAdapter.notifyDataSetChanged();

                Gson gson = new Gson();
                String json = gson.toJson(mItem);


                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putString("item", json);
                editor.putInt("net",net);
                editor.putInt("total", total);
                editor.putInt("goal", goal);
                editor.apply();


            }
        }
    }
}
