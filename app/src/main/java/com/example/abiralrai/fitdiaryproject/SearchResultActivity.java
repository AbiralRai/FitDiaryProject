package com.example.abiralrai.fitdiaryproject;
import android.content.Intent;
import android.os.Bundle;
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

public class SearchResultActivity extends AppCompatActivity {
//    private static final String TAG = "SearchResultActivity";
    private ExampleAdapter adapter;
    private List<ExampleItem> exampleList;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        fillExampleList();
        setUpRecyclerView();

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
    public void getFoodItem (int position) {
        Intent intent = new Intent(SearchResultActivity.this, FoodItem.class);
        String foodLabel = exampleList.get(position).getFood_label();
        String foodWeight = exampleList.get(position).getFood_weight();
        String calories = exampleList.get(position).getCalories();
        intent.putExtra("foodLabel", foodLabel);
        intent.putExtra("foodWeight", foodWeight);
        intent.putExtra("calories", calories);
        startActivity(intent);
        adapter.notifyItemChanged(position);
    }

    private void fillExampleList() {
        exampleList = new ArrayList<>();
        exampleList.add(new ExampleItem("Food1", "100g", "123"));
        exampleList.add(new ExampleItem("Food2", "50g", "222"));
        exampleList.add(new ExampleItem("Food3", "100g", "90"));
        exampleList.add(new ExampleItem("Food4", "200g", "320"));
        exampleList.add(new ExampleItem("Food5", "100g", "441"));
        exampleList.add(new ExampleItem( "Food6", "50g", "123"));
        exampleList.add(new ExampleItem( "Food7", "100g", "301"));
        exampleList.add(new ExampleItem("Food8", "50g", "112"));
        exampleList.add(new ExampleItem("Food9", "200g", "554"));
    }

    private void setUpRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new ExampleAdapter(exampleList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickedListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                getFoodItem(position);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
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
