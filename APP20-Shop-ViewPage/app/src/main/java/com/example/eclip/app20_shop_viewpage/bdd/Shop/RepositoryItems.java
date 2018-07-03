package com.example.eclip.app20_shop_viewpage.bdd.Shop;

import com.example.eclip.app20_shop_viewpage.bdd.Shop.model.Item;

import java.util.List;

/**
 * Created by eclip on 14/01/2018.
 */

@SuppressWarnings({"WeakerAccess", "unused"})
public interface RepositoryItems {
    List<Item> getItems();
}
