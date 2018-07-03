package com.example.eclip.app20_shop_viewpage.shoppingCarActivity;

import android.arch.lifecycle.ViewModel;

import com.example.eclip.app20_shop_viewpage.bdd.Cest.RepositoryItemsCart;
import com.example.eclip.app20_shop_viewpage.bdd.Shop.model.Item;

import java.util.ArrayList;

/**
 * Created by eclip on 19/01/2018.
 */

@SuppressWarnings("WeakerAccess")
public class ShoppingCarActivityViewModel extends ViewModel {

    static private ArrayList<Item> data;
    private final RepositoryItemsCart repository;

    public ShoppingCarActivityViewModel(RepositoryItemsCart repository) {this.repository = repository;}

    public ArrayList<Item> getData() {
        if (data == null) {
            data = (ArrayList<Item>) repository.getItemsCest();
        }
        return data;
    }
}