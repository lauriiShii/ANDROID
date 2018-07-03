package com.example.eclip.app25_fct;

import android.app.Application;

import com.facebook.stetho.Stetho;

/***
 * Starts the Stetho service
 */
public class App extends Application {
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}