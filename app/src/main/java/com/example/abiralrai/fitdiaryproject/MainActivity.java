package com.example.abiralrai.fitdiaryproject;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.Parse;import com.parse.ParseAnalytics;import com.parse.ParseException;import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;import com.parse.ParseInstallation;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import java.util.Arrays;

import static com.facebook.FacebookSdk.*;
import static com.facebook.appevents.AppEventsLogger.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener {

    Boolean signUpModeActive = true;
    TextView loginTextView;
    EditText usernameEditText;
    EditText passwordEditText;

    LoginButton loginButton;
    CallbackManager callbackManager;
    private static final String EMAIL = "email";

    public void showDashboard() {
        Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {

        if(i == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
            signUpClicked(view);
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.loginTextView) {

            Button signUpButton = findViewById(R.id.signUpBtn);

            if(signUpModeActive) {
                signUpModeActive = false;
                signUpButton.setText("Login");
                loginTextView.setText("or, Sign Up");
            } else {
                signUpModeActive = true;
                signUpButton.setText("Sign Up");
                loginTextView.setText("or, Login");
            }
        } else if (view.getId() == R.id.logoImageView || view.getId() == R.id.backgroundLayout) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    public void signUpClicked(final View view) {

        if(usernameEditText.getText().toString().matches("") || passwordEditText.getText().toString().matches("")) {
            Toast.makeText(this, "Username and Password are required.", Toast.LENGTH_SHORT).show();

        } else {
            if(signUpModeActive) {
                ParseUser user = new ParseUser();
                user.setUsername(usernameEditText.getText().toString());
                user.setPassword(passwordEditText.getText().toString());

                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Toast.makeText(MainActivity.this, "Sign Up Successful", Toast.LENGTH_SHORT).show();
                            showDashboard();
                        } else {
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } else {
                //Login
                ParseUser.logInInBackground(usernameEditText.getText().toString(), passwordEditText.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if(user != null) {
                            Toast.makeText(MainActivity.this,"Login Successful", Toast.LENGTH_SHORT).show();
                            showDashboard();
                        } else {
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
    }


    @Override protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        loginButton = findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList(EMAIL));

        // Login Button callback registration
        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        showDashboard();
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                // Error msg display
                String toastMessage = exception.getMessage();
                Toast.makeText(MainActivity.this, toastMessage, Toast.LENGTH_LONG).show();
                    }
                });

        loginTextView = findViewById(R.id.loginTextView);
        loginTextView.setOnClickListener(this);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        ImageView logoImageView = findViewById(R.id.logoImageView);
        ConstraintLayout backgroundLayout = findViewById(R.id.backgroundLayout);
        logoImageView.setOnClickListener(this);
        backgroundLayout.setOnClickListener(this);

        passwordEditText.setOnKeyListener(this);

        if(ParseUser.getCurrentUser() != null) {
            showDashboard();
        }


        ParseAnalytics.trackAppOpenedInBackground(getIntent());

        // check for an existing access token
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        com.facebook.AccessToken loginToken = com.facebook.AccessToken.getCurrentAccessToken();
        if (accessToken != null || loginToken != null) {
            // if previously logged in, proceed to the account activity
            showDashboard();
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }





}



//        ParseObject tweet = new ParseObject("Tweet");
//        tweet.put("username", "Abiral");
//        tweet.put("tweet", "I like this tweet");
//        tweet.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//                if(e == null) {
//                    // OK
//                    Log.i("Success", "We save the tweet");
//                } else {
//                    e.printStackTrace();
//                }
//            }
//        });

//        ParseQuery<ParseObject> query = ParseQuery.getQuery("Tweet");
//
//        query.getInBackground("xfo6GS7Scr", new GetCallback<ParseObject>() {
//            @Override
//            public void done(ParseObject object, ParseException e) {
//                if(e == null && object != null) {
//
//                    object.put("tweet", "This is a modified tweet");
//                    object.saveInBackground();
//
//                    Log.i("username", object.getString("username"));
//                    Log.i("tweet", object.getString("tweet"));
//                }
//            }
//        });
        /* // Create new user
        ParseUser user = new ParseUser();

        user.setUsername("Abiral");
        user.setPassword("123");

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null) {
                    //ok
                    Log.i("Sign up ook", "We did it");
                } else {
                    e.printStackTrace();
                }
            }
        });
*/
        /* //Login user
        ParseUser.logInInBackground("Abiral", "123", new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(user != null) {
                    Log.i("Success", "We logging in");
                } else {
                    e.printStackTrace();
                }
            }
        });
        */

       /* // Log out user
        ParseUser.logOut();

        if(ParseUser.getCurrentUser() != null) {
            Log.i("Signed In", ParseUser.getCurrentUser().getUsername());
        } else {
            Log.i("Status", "Not Signed IN");
        }
        */
