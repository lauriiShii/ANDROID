package com.example.eclip.app21_bdd.bdd;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by eclip on 23/01/2018.
 */

public class BdHelper extends SQLiteOpenHelper {

    public static BdHelper instance;

    public BdHelper(Context context) {
        super(context, DbContrat.BD_NOMBRE, null, DbContrat.BD_VERSION);
    }

    public static synchronized BdHelper getInstance(Context context) {
        if (instance == null) {
            instance = new BdHelper(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE alumnos (\n" +
                " contact_id integer PRIMARY KEY,\n" +
                " nombre text NOT NULL\n" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE alumnos");
        db.execSQL("CREATE TABLE alumnos (\n" +
                " contact_id integer PRIMARY KEY,\n" +
                " nombre text NOT NULL\n" +
                ");");
    }
}
