package com.android.lauracalvente.app10_gatitos.bdd;

import com.android.lauracalvente.app10_gatitos.model.pojos.User;

import java.util.ArrayList;

/**
 *
 * Class that simulates the storage of a table of users
 *
 * This class contains a static array that contains all users, this array is modified,
 * since users can be eliminated or added due to the need of the application
 *
 * @author Laura Calvente
 * @version 19/10/2017
 * @since 1.0
 */

public class ColecctionUsers {
    public static ArrayList<User> bddUsers = new ArrayList<>();

    /*static{
        bddUsers.add(new User (ColectionCat.bdd.get(0), "a", "a", "a", "a", "a"));
        bddUsers.add(new User (ColectionCat.bdd.get(1), "a", "a", "a", "a", "a"));
        bddUsers.add(new User (ColectionCat.bdd.get(2), "a", "a", "a", "a", "a"));
        bddUsers.add(new User (ColectionCat.bdd.get(0), "a", "a", "a", "a", "a"));
        bddUsers.add(new User (ColectionCat.bdd.get(3), "a", "a", "a", "a", "a"));
        bddUsers.add(new User (ColectionCat.bdd.get(4), "a", "a", "a", "a", "a"));
        bddUsers.add(new User (ColectionCat.bdd.get(5), "a", "a", "a", "a", "a"));
        bddUsers.add(new User (ColectionCat.bdd.get(1), "a", "a", "a", "a", "a"));
        bddUsers.add(new User (ColectionCat.bdd.get(1), "a", "a", "a", "a", "a"));
        bddUsers.add(new User (ColectionCat.bdd.get(0), "a", "a", "a", "a", "a"));
        bddUsers.add(new User (ColectionCat.bdd.get(0), "a", "a", "a", "a", "a"));
        bddUsers.add(new User (ColectionCat.bdd.get(2), "a", "a", "a", "a", "a"));
        bddUsers.add(new User (ColectionCat.bdd.get(3), "a", "a", "a", "a", "a"));
        bddUsers.add(new User (ColectionCat.bdd.get(5), "a", "a", "a", "a", "a"));

    }

    /**
     * GetUsers: returns all users
     * @return bdd cats
     */
    public static ArrayList<User> getUsers() {return bddUsers;}

    /**
     * DeleteUser: Remove an user in array
     * @param position of user that we removed
     */
    public static void deleteUser(int position ){bddUsers.remove(position);}

    /**
     * AddUserPosition: we add a user in a indicated position
     * @param position where the new user is added
     * @param user that we are going to add
     */
    public static void addUserPosition(int position, User user){bddUsers.add(position, user);}

}
