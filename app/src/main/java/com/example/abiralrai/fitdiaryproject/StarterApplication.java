package com.example.abiralrai.fitdiaryproject;

import com.parse.Parse;
import android.app.Application;
import android.util.Log;import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.ParseInstallation;
import com.parse.SaveCallback;
public class StarterApplication extends Application {
    @Override public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("d252a85e203dc609f2700e8a3581f5dc32175cc3")
                .clientKey("afc45909ef1062cc5b50d10141bef8a00b5a7c85")
                .server("http://13.58.4.154:80/parse/")
                .build()
        );



//        ParseUser.enableAutomaticUser();

        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);
    }}