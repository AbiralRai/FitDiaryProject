package com.example.abiralrai.fitdiaryproject;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

public class FBAccountActivity extends AppCompatActivity {

    ImageView profilePic;
    TextView usernameTextView;
    Intent intent;
    Button btnUploadPic;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        usernameTextView = findViewById(R.id.usernameTextView);
        profilePic = findViewById(R.id.profilePic);
        btnUploadPic = findViewById(R.id.btnUploadPic);
        getProfileInfo();
        btnUploadPic.setText("");
        btnUploadPic.setOnClickListener(null);

        // Add back button
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
    private  void getProfileInfo() {

        usernameTextView.setText(Profile.getCurrentProfile().getName());
        Uri profilePicUri = Profile.getCurrentProfile().getProfilePictureUri(100, 100);
        getProfilePic(profilePicUri);

    }

    private void getProfilePic(Uri uri) {
        // helper method to load the profile pic in a circular imageview
        Transformation transformation = new RoundedTransformationBuilder()
                .cornerRadiusDp(30)
                .oval(false)
                .build();
        Picasso.with(FBAccountActivity.this)
                .load(uri)
                .transform(transformation)
                .into(profilePic);
    }

    public void onLogout(View view) {
        LoginManager.getInstance().logOut();
        intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}
