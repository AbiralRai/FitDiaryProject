package com.example.abiralrai.fitdiaryproject;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;

import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.parse.ParseUser;

import java.util.EmptyStackException;

import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.example.abiralrai.fitdiaryproject.SearchRowAdapter.FoodListRowAdapter;

public class DashboardActivity extends AppCompatActivity {

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
                startActivity(intent);
                return true;
            default:
                return false;

        }
    }


    private ListView mListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        if(ParseUser.getCurrentUser() != null) {
            Log.i("onCreate: ", "AWE NOT NULL");
        } else {
            Log.i("onCreate: ", "FB NOT NULL");
        }


    String food_name = getIntent().getStringExtra("food_name");
    String calories = getIntent().getStringExtra("calories");
    String carbohydrate = getIntent().getStringExtra("carbohydrate");
    String protein = getIntent().getStringExtra("protein");
    String fat = getIntent().getStringExtra("fat");
    String serving_description = getIntent().getStringExtra("serving_description");

        }

}
