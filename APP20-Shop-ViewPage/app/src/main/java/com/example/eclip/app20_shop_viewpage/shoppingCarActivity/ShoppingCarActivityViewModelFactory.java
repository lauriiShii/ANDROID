package com.example.eclip.app20_shop_viewpage.shoppingCarActivity;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.example.eclip.app20_shop_viewpage.bdd.Cest.RepositoryItemsCart;

/**
 * Created by eclip on 19/01/2018.
 */

@SuppressWarnings("WeakerAccess")
public class ShoppingCarActivityViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final RepositoryItemsCart repository;

    public ShoppingCarActivityViewModelFactory(RepositoryItemsCart repository) {
        this.repository = repository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new ShoppingCarActivityViewModel(repository);
    }
}