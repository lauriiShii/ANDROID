package com.example.eclip.periodico;
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