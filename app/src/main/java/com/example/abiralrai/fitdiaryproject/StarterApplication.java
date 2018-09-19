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


        //kgple6LzZRoI (password for bitnami)

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("a0fc2feea24a292479e83d0901196a5edd3dd61e")
                .clientKey("2e7f13c097f7d34828bf3db66b6022048ad69130")
                .server("http://18.222.144.81:80/parse/")
                .build()
        );



//        ParseUser.enableAutomaticUser();

        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);
    }}