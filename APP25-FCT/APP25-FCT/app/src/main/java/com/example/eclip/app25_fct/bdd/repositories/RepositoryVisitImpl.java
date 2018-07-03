package com.example.eclip.app25_fct.bdd.repositories;

import android.arch.lifecycle.LiveData;

import com.example.eclip.app25_fct.bdd.local.AppDataBase;
import com.example.eclip.app25_fct.bdd.model.Visit;

import java.util.List;

/**
 * Created by eclip on 17/02/2018.
 */

public class RepositoryVisitImpl implements RepositoryVisit {

    private static RepositoryVisitImpl instance;
    private static AppDataBase database;

    private RepositoryVisitImpl(AppDataBase database) {
        this.database = database;
    }

    public static RepositoryVisitImpl getInstance(AppDataBase database) {
        if (instance == null) {
            instance = new RepositoryVisitImpl(database);
        }
        return instance;
    }

    @Override
    public LiveData<List<Visit>> getAllVisit() {
        return database.VisitDAO().loadAllVisit();
    }

    @Override
    public void insert(Visit visit) {
        database.VisitDAO().insert(visit);
    }

    @Override
    public List<Visit> loadAllVisitById(int id) {
        return database.VisitDAO().loadAllVisitById(id);
    }

    @Override
    public List<Visit> loadAllVisitNotOK() {
        return database.VisitDAO().loadAllVisitNotOK();
    }

    @Override
    public int delete(Visit visit) {
        return database.VisitDAO().delete(visit);
    }

    @Override
    public List<Visit> loadAllVisitNotLiveData(int id) {
        return database.VisitDAO().loadAllVisitNotLiveData(id);
    }
}