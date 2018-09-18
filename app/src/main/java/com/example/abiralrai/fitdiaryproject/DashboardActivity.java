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
import android.widget.SearchView;

import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.parse.ParseUser;

import java.util.EmptyStackException;

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
//            case R.id.settings:
//                Log.i("Item Selected", "Set tings");
//                return true;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        if(ParseUser.getCurrentUser() != null) {
            Log.i("onCreate: ", "AWE NOT NULL");
        } else {
            Log.i("onCreate: ", "FB NOT NULL");
        }
    }

}
