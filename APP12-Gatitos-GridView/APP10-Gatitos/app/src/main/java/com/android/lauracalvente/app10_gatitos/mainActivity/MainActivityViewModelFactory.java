package com.android.lauracalvente.app10_gatitos.mainActivity;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.android.lauracalvente.app10_gatitos.bdd.users.RepositoryUsers;

/**
 * Created by eclip on 15/11/2017.
 */

@SuppressWarnings("WeakerAccess")
public class MainActivityViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final RepositoryUsers repository;

    public MainActivityViewModelFactory(RepositoryUsers repository) {
        this.repository = repository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new MainActivityViewModel(repository);
    }
}