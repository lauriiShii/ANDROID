package com.example.eclip.app14_galeria.mainActivity;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.example.eclip.app14_galeria.model.artWorks.bdd.RepositoryArtWork;

/**
 * Created by eclip on 25/11/2017.
 */

@SuppressWarnings("WeakerAccess")
public class MainActivityViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final RepositoryArtWork repository;

    public MainActivityViewModelFactory(RepositoryArtWork repository) {
        this.repository = repository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new MainActivityViewModel(repository);
    }
}