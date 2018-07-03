package com.example.eclip.periodico.bdd.repository;

import android.arch.lifecycle.LiveData;

import com.example.eclip.periodico.bdd.model.Periodico;

import java.util.List;

/**
 * Created by eclip on 06/03/2018.
 */

@SuppressWarnings("WeakerAccess")
public interface RepositoryPeriodico {

    void insert(Periodico periodico);
    int delete(Periodico periodico);
    LiveData<List<Periodico>> loadAllPeriodicos();
    List<Periodico> loadAllPeriodicosNotLiveData();
}
