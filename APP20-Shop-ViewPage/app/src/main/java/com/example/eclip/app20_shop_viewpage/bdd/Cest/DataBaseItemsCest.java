package com.example.eclip.app20_shop_viewpage.bdd.Cest;

import com.example.eclip.app20_shop_viewpage.bdd.Shop.model.Item;

import java.util.ArrayList;

/**
 * Created by eclip on 14/01/2018.
 */

public class DataBaseItemsCest {

    private ArrayList<Item> shoppingCar;// cart items collection
    private static DataBaseItemsCest instance; // Instance of the item collection

    /***
     * Empty constructor
     */
    public DataBaseItemsCest() {
        this.shoppingCar = new ArrayList<Item>();
    }

    /***
     * returns the cart collection
     * @return cart
     */
    public ArrayList<Item> getShoppingCar() {
        return shoppingCar;
    }

    /***
     * create the instance of the "table" cart
     * @return instance
     */
    public static DataBaseItemsCest getInstance() {
        if (instance == null) {
            instance = new DataBaseItemsCest();
        }
        return instance;
    }

    /***
     * add an item to the cart collection
     * @param item
     */
    public void addItem (Item item){
        shoppingCar.add(item);
    }

    /***
     * delete a particular item from the cart collection
     * @param item
     */
    public  void  deleteItem (Item item){
        shoppingCar.remove(item);
    }
}
