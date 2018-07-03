package com.example.eclip.app24_empresas;

import android.app.Application;
import com.facebook.stetho.Stetho;

/**
 * Created by eclip on 15/02/2018.
 */

public class App extends Application {
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}