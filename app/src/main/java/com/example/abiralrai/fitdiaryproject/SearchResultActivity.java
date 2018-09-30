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
import android.view.inputmethod.EditorInfo;
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

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fatSecretSearch = new FatSecretSearchFragment();
        if (fatSecretSearch != null)
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new FatSecretSearchFragment()).commit();
    }

    @Override
    public void fromFragment() {

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
