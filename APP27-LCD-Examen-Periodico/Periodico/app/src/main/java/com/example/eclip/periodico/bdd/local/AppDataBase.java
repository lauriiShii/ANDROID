package com.example.eclip.periodico.bdd.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.eclip.periodico.bdd.model.Periodico;

/**
 * Created by eclip on 06/03/2018.
 */

@Database(entities = {Periodico.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    public abstract DaoPeriodico PeriodicoDAO();
}
