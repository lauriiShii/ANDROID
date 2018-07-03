package com.example.eclip.app20_shop_viewpage.bdd.Cest;

import com.example.eclip.app20_shop_viewpage.bdd.Shop.model.Item;

import java.util.List;

/**
 * Created by eclip on 19/01/2018.
 */

public class RepositoryImpItemsCart implements RepositoryItemsCart {
    private static RepositoryImpItemsCart instance;
    private static DataBaseItemsCest database;

    private RepositoryImpItemsCart(DataBaseItemsCest database) {
        this.database = database;
    }

    public static RepositoryImpItemsCart getInstance(DataBaseItemsCest database) {
        if (instance == null) {
            instance = new RepositoryImpItemsCart(database);
        }
        return instance;
    }

    @Override
    public List<Item> getItemsCest() {
        return database.getShoppingCar();
    }

    @Override
    public void addItem(Item item) {
        database.addItem(item);
    }

    @Override
    public void deleteItem(Item item) {
        database.deleteItem(item);
    }

}
