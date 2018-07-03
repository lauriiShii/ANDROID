package com.example.eclip.periodico.mainActivity;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.eclip.periodico.bdd.model.Periodico;
import com.example.eclip.periodico.bdd.repository.RepositoryPeriodico;

import java.util.List;

/**
 * Created by eclip on 13/02/2018.
 */

@SuppressWarnings("WeakerAccess")
public class MainActivityViewModel extends ViewModel {

    private RepositoryPeriodico mRepository;

    public MainActivityViewModel(RepositoryPeriodico repository ) {
        mRepository = repository;
    }

    public LiveData<List<Periodico>> getPeriodicos() {
        return mRepository.loadAllPeriodicos();
    }

}
