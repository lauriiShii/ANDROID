package com.example.eclip.app21_bdd.bdd;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.eclip.app21_bdd.bdd.models.Alumno;

/**
 * Created by eclip on 30/01/2018.
 */

@Database(entities = {Alumno.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract AlumnoDAO alumnoDAO();

}