package com.example.abiralrai.fitdiaryproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.support.v4.app.Fragment;
public class DashboardActivity extends AppCompatActivity {


    private ListView mListView;
    static FoodListRowAdapter mFoodListAdapter;
    static  ArrayList<Item> mItem;


    String food_name = "";
    String food_description = "";
    String[] row;
    String brand;
    String id = "";
    String food_id = "";



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

        mListView = (ListView) findViewById(R.id.food_item_list);
            mItem = new ArrayList<>();
            mFoodListAdapter = new FoodListRowAdapter(getApplicationContext(), mItem);
            mListView.setAdapter(mFoodListAdapter);
            registerForContextMenu(mListView);


        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int itemToDelete = position;

                new AlertDialog.Builder(DashboardActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Are you sure?")
                        .setMessage("Do you want to delete this meal")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mItem.remove(itemToDelete);
                                mFoodListAdapter.notifyDataSetChanged();

                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
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
                mFoodListAdapter.add(a);
                mFoodListAdapter.notifyDataSetChanged();
            }
        }
    }

//    private void updateList() {
//        if (mFoodListAdapter.getCount() == 0) {
//            mListView.setVisibility(View.GONE);
//        } else {
//            mListView.setVisibility(View.VISIBLE);
//        }
//    }


//    private void findViewsById() {
//        mListView = (ListView) findViewById(R.id.food_item_list);
//        listViewConfigurations();
//
//        updateList();
//    }


//
//    private void listViewConfigurations() {
//        mItem = new ArrayList<>();
//        mFoodListAdapter = new FoodListRowAdapter(getApplicationContext(), mItem);
//        mListView.setAdapter(mFoodListAdapter);
//        mListView.setAdapter(new QuickReturnAdapter(mFoodListAdapter));
//        mQuickReturnAttacher = QuickReturnAttacher.forView(mListView);
//    }





}
