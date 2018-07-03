package com.example.eclip.periodico.bdd.repository;

import android.arch.lifecycle.LiveData;

import com.example.eclip.periodico.bdd.local.AppDataBase;
import com.example.eclip.periodico.bdd.model.Periodico;

import java.util.List;

/**
 * Created by eclip on 06/03/2018.
 */

public class RepositoryPeriodicoImpl implements RepositoryPeriodico {

    private static RepositoryPeriodicoImpl instance;
    private static AppDataBase database;

    private RepositoryPeriodicoImpl(AppDataBase database) {
        this.database = database;
    }

    public static RepositoryPeriodicoImpl getInstance(AppDataBase database) {
        if (instance == null) {
            instance = new RepositoryPeriodicoImpl(database);
        }
        return instance;
    }

    @Override
    public void insert(Periodico periodico) {
        database.PeriodicoDAO().insert(periodico);
    }

    @Override
    public int delete(Periodico periodico) {
        return database.PeriodicoDAO().delete(periodico);
    }

    @Override
    public LiveData<List<Periodico>> loadAllPeriodicos() {
        return database.PeriodicoDAO().loadAllPeriodicos();
    }

    @Override
    public List<Periodico> loadAllPeriodicosNotLiveData() {
        return database.PeriodicoDAO().loadAllPeriodicosNotLiveData();
    }
}
