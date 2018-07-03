package com.example.eclip.app24_empresas.bdd.local;

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
                "crud3.db")
                .allowMainThreadQueries()
                //.fallbackToDestructiveMigration()
                .addCallback(new RoomDatabase.Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        // Ejecutar sentencias SQL sobre db.
                        ContentValues cv = new ContentValues();
                        cv.put("name", "saladillo");
                        db.insert("business", 0, cv);
                    }
                }).build();
    }

    public AppDataBase getDb() {
        return db;
    }
}
