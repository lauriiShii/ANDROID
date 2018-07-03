package com.example.eclip.app20_shop_viewpage.bdd.Cest;

import com.example.eclip.app20_shop_viewpage.bdd.Shop.model.Item;

import java.util.List;

/**
 * Created by eclip on 19/01/2018.
 */

@SuppressWarnings({"WeakerAccess", "unused"})
public interface RepositoryItemsCart {
    List<Item> getItemsCest();
    void addItem(Item item);
    void deleteItem(Item item);
}
