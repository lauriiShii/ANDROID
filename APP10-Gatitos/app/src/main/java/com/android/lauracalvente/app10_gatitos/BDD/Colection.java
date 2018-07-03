package com.android.lauracalvente.app10_gatitos.BDD;

import com.android.lauracalvente.app10_gatitos.Model.Pojos.Cat;
import com.android.lauracalvente.app10_gatitos.R;

import java.util.ArrayList;

/**
 * Created by Laura on 19/10/2017.
 */

public class Colection {

    public static ArrayList<Cat> bdd = new ArrayList<>();

    static {
        bdd.add(new Cat(R.drawable.cat1, "Procubito", true));
        bdd.add(new Cat(R.drawable.cat2, "Gatete", false));
        bdd.add(new Cat(R.drawable.cat3, "FrigoPolo", false));
        bdd.add(new Cat(R.drawable.cat4, "FrigoPie", false));
        bdd.add(new Cat(R.drawable.cat5, "Don Fresquito", false));
        bdd.add(new Cat(R.drawable.cat6, "Miwe", false));
    }

    public static ArrayList<Cat> getCats() {
        return bdd;
    }
}