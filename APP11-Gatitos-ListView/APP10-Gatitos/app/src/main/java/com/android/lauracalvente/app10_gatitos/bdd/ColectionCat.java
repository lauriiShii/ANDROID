package com.android.lauracalvente.app10_gatitos.bdd;

import com.android.lauracalvente.app10_gatitos.R;
import com.android.lauracalvente.app10_gatitos.model.pojos.Cat;

import java.util.ArrayList;

/**
 *
 * Class that simulates the storage of a table of cats
 *
 * This class consists of a static array that contains all the cats that are pre-established,
 * this arry will never be increased or decreased for this particular application and is necessary
 * to be able to represent the possible avatars
 *
 * @author Laura Calvente
 * @version 19/10/2017
 * @since 1.0
 */

public class ColectionCat {

    public static ArrayList<Cat> bdd = new ArrayList<>(); //Cats

    static {
        bdd.add(new Cat(R.drawable.cat1, "Procubito"));
        bdd.add(new Cat(R.drawable.cat2, "Gatete"));
        bdd.add(new Cat(R.drawable.cat3, "FrigoPolo"));
        bdd.add(new Cat(R.drawable.cat4, "FrigoPie"));
        bdd.add(new Cat(R.drawable.cat5, "Don Fresquito"));
        bdd.add(new Cat(R.drawable.cat6, "Miwe"));
    }

    /**
     * GetCats: returns all cats
     * @return bdd cats
     */
    public static ArrayList<Cat> getCats() {
        return bdd;
    }
}