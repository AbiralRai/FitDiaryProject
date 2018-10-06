package com.example.abiralrai.fitdiaryproject;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.example.abiralrai.fitdiaryproject.SearchRowAdapter.Item;

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
    public void fromFragment(String food_name, String food_description, String brand, String food_id) {
        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, android.R.anim.fade_out).replace(R.id.container, new FatSecretSearchFragment()).addToBackStack(null).commit();
//        Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
        Intent intent = new Intent();
        intent.putExtra("food_name", food_name);
        intent.putExtra("food_description", food_description);
        intent.putExtra("brand", brand);
        intent.putExtra("food_id", food_id);

        setResult(RESULT_OK, intent);
        finish();
    }


}
