package com.example.eclip.app21_bdd.bdd;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.ContentValues;
import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created by eclip on 30/01/2018.
 */

public class BD {
    private static BD ourInstance;
    private AppDatabase db;

    public static synchronized BD getInstance(Context context) {

        if (ourInstance == null) {
            ourInstance = new BD(context.getApplicationContext());
        }
        return ourInstance;
    }

    private BD(Context context) {
        db = Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class,
                "instituto.db")
                //.fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .addCallback(new RoomDatabase.Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        ContentValues cv = new ContentValues();
                        cv.put("alumnoId", "Baldomero");
                        db.insert("alumnos", 0, cv);
                    }
                }).build();
    }

    public AppDatabase getDb(){return db;}
}
