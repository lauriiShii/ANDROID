package com.example.eclip.periodico.bdd.local;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.ContentValues;
import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created by eclip on 06/03/2018.
 */

public class BD {

    private static BD ourInstance;
    private AppDataBase db;

    public static synchronized BD getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new BD(context.getApplicationContext());
        }
        return ourInstance;
    }

    private BD(Context context) {
        db = Room.databaseBuilder(context.getApplicationContext(),
                AppDataBase.class,
                "appPeriodico3.db")
                .allowMainThreadQueries()
                //.fallbackToDestructiveMigration()
                .addCallback(new RoomDatabase.Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {

                        ContentValues periodico = new ContentValues();
                        periodico.put("name", "La linea digital");
                        periodico.put("visitas", 0);
                        periodico.put("url", "https://lalinea.digital/");
                        db.insert("periodicos", 0, periodico);
                        periodico.put("name", "ABC");
                        periodico.put("url", "http://www.abc.es/");
                        periodico.put("visitas", 0);
                        db.insert("periodicos", 0, periodico);
                        periodico.put("name", "El Periodico");
                        periodico.put("visitas", 0);
                        periodico.put("url", "https://www.elperiodico.com/es/global/?gclid=CjwKCAiAlfnUBRBQEiwAWpPA6R0YTAOSCs4aQLxpgk69sL2ibRCSaAJweO46gPsEy2IVG3nxpHusoBoC1_YQAvD_BwE");
                        db.insert("periodicos", 0, periodico);

                    }
                }).build();
    }

    public AppDataBase getDb() {
        return db;
    }
}
