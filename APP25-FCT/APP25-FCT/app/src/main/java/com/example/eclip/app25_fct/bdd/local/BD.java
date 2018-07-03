package com.example.eclip.app25_fct.bdd.local;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.ContentValues;
import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created by eclip on 13/02/2018.
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
                "app25fct3.db")
                .allowMainThreadQueries()
                //.fallbackToDestructiveMigration()
                .addCallback(new RoomDatabase.Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {

                        ContentValues teacher = new ContentValues();
                        teacher.put("name", "David Quero");
                        db.insert("teachers", 0, teacher);
                        teacher.put("name", "Pedro Joya");
                        db.insert("teachers", 0, teacher);
                        teacher.put("name", "Eva Peralta");
                        db.insert("teachers", 0, teacher);

                        /*ContentValues company = new ContentValues();
                        company.put("name", "Accenture");
                        db.insert("business", 0, company);
                        company.put("name", "OP+");
                        db.insert("business", 0, company);
                        company.put("name", "Oracle");
                        db.insert("business", 0, company);
                        company.put("name", "Avanade");
                        db.insert("business", 0, company);*/

                        /*ContentValues estudiante = new ContentValues();
                        estudiante.put("name", "Karlishus");
                        estudiante.put("company", 1);
                        estudiante.put("teacher", 1);
                        db.insert("students", 0, estudiante);*/

                    }
                }).build();
    }

    public AppDataBase getDb() {
        return db;
    }
}
