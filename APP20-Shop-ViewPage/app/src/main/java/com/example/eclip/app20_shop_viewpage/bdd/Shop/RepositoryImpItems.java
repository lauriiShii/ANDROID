package com.example.eclip.app20_shop_viewpage.bdd.Shop;

import com.example.eclip.app20_shop_viewpage.bdd.Shop.model.Item;

import java.util.List;

/**
 * Created by eclip on 14/01/2018.
 */

public class RepositoryImpItems implements RepositoryItems {

    private static RepositoryImpItems instance;
    private static DataBaseItemsShop database;

    private RepositoryImpItems(DataBaseItemsShop database) {
        this.database = database;
    }

    public static RepositoryImpItems getInstance(DataBaseItemsShop database) {
        if (instance == null) {
            instance = new RepositoryImpItems(database);
        }
        return instance;
    }

    @Override
    public List<Item> getItems() {
        return database.getItems();
    }

}
