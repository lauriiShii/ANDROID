package com.example.eclip.app13_pruebaactionmode;

import java.util.ArrayList;

/**
 * Created by eclip on 20/11/2017.
 */

public class bdd {

    public static ArrayList<User> bdd = new ArrayList<>(); //Cats

    static {
        bdd.add(new User("Procubito"));
        bdd.add(new User("Gatete"));
        bdd.add(new User("FrigoPolo"));
        bdd.add(new User("FrigoPie"));
        bdd.add(new User("Don Fresquito"));
        bdd.add(new User("Miwe"));
    }

    public static ArrayList<User> getData() {
        return  bdd;
    }
}
