package com.example.eclip.app20_shop_viewpage.bdd.Shop;

import com.example.eclip.app20_shop_viewpage.bdd.Shop.model.Item;

import java.util.ArrayList;

/**
 * Created by eclip on 14/01/2018.
 */

public class DataBaseItemsShop {
    private static DataBaseItemsShop instance; // items collection
    private static ArrayList<Item> items; // Instance of the item collection

    /***
     * Builder
     */
    public DataBaseItemsShop(){
        items = new ArrayList<>();
        items.add(new Item ("Camiseta DC Comics Original Super Heroes", "Geek Clothing", "https://s3.thcdn.com/productimg/600/600/11468059-1094483556564421.jpg", 13.23f,4));
        items.add(new Item ("Camiseta Marvel Spider-Man - Niño - Negro", "Geek Clothing", "https://s2.thcdn.com/productimg/600/600/11350777-1774429088339253.JPG", 14.85f,7));
        items.add(new Item ("Gorra Marvel Spider-Man Ojos 3D - Rojo", "Geek Clothing", "https://s4.thcdn.com/productimg/600/600/11414108-1974461549663816.jpg", 20.55f,2));
        items.add(new Item ("Deadpool Men's Character Camiseta - Blanco", "Geek Clothing", "https://s2.thcdn.com/productimg/600/600/11243861-7214372894272990.jpg", 17.09f,5));
    }

    /***
     * create the instance of the "table" items
     * @return instance
     */
    public static DataBaseItemsShop getInstance() {
        if (instance == null) {
            instance = new DataBaseItemsShop();
        }
        return instance;
    }

    /***
     * returns the items collection
     * @return items
     */
    public static ArrayList<Item> getItems() {return items;}
}
