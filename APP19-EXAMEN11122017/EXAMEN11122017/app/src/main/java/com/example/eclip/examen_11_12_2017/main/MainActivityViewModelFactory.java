package com.example.eclip.examen_11_12_2017.main;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.example.eclip.examen_11_12_2017.main.bdd.BDDMensajes;
import com.example.eclip.examen_11_12_2017.main.bdd.RepositoryMensajes;

/**
 * Created by eclip on 15/11/2017.
 */

@SuppressWarnings("WeakerAccess")
public class MainActivityViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final RepositoryMensajes repository;

    public MainActivityViewModelFactory(RepositoryMensajes repository) {
        this.repository = repository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new MainActivityViewModel(repository);
    }
}