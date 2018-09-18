package com.example.abiralrai.fitdiaryproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.parse.ParseUser;


public class AccountActivity extends AppCompatActivity {

    ImageView profilePic;
    TextView usernameTextView;
    Intent intent;

    public void onLogout(View view) {
        ParseUser.logOut();
        intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home) {
            // Ends activity
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);



        usernameTextView = findViewById(R.id.usernameTextView);
        usernameTextView.setText(ParseUser.getCurrentUser().getUsername());

        // Add back button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
