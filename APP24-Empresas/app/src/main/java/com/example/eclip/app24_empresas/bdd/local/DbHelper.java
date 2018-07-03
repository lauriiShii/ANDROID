package com.example.eclip.app24_empresas.bdd.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by eclip on 13/02/2018.
 */

public class DbHelper extends SQLiteOpenHelper {

    public static DbHelper instance;

    private DbHelper(Context context) {
        super(context, DbContract.BD_NOMBRE, null, DbContract.BD_VERSION);
    }

    public static synchronized DbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DbHelper(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE business (\n" +
                " _id integer PRIMARY KEY,\n" +
                " name text NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE business;");
        db.execSQL("CREATE TABLE business (\n" +
                " _id integer PRIMARY KEY,\n" +
                " name text NOT NULL);");
    }
}
