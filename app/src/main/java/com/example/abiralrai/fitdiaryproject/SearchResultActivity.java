package com.example.abiralrai.fitdiaryproject;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SearchResultActivity extends AppCompatActivity  implements FatSecretSearchFragment.FragmentCallbacks {
    Fragment fatSecretSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);


        fatSecretSearch = new FatSecretSearchFragment();
        if (fatSecretSearch != null)
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new FatSecretSearchFragment()).commit();


    }

    @Override
    public void fromFragment(String food_name, String calories, String carbohydrate, String protein, String fat, String serving_description) {
        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, android.R.anim.fade_out).replace(R.id.container, new FatSecretSearchFragment()).addToBackStack(null).commit();
        Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
        intent.putExtra("food_name", food_name);
        intent.putExtra("calories", calories);
        intent.putExtra("carbohydrate", carbohydrate);
        intent.putExtra("protein", protein);
        intent.putExtra("fat", fat);
        intent.putExtra("serving_description", serving_description);
        startActivity(intent);
    }


}
