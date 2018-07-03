package com.example.eclip.periodico.mainActivity;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.example.eclip.periodico.bdd.repository.RepositoryPeriodico;


/**
 * Created by eclip on 17/02/2018.
 */

@SuppressWarnings("WeakerAccess")
public class MainActivityViewModelFactory extends ViewModelProvider.NewInstanceFactory{

    private final RepositoryPeriodico mRepository;

    public MainActivityViewModelFactory(RepositoryPeriodico repository) {
        this.mRepository = repository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new MainActivityViewModel(mRepository);
    }
}
