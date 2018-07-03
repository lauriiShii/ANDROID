package com.android.lauracalvente.app10_gatitos.bdd.users;

import com.android.lauracalvente.app10_gatitos.bdd.cats.ColectionCat;
import com.android.lauracalvente.app10_gatitos.bdd.users.model.User;

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

public class DataBaseUsers {

    private static DataBaseUsers instance;
    private static ArrayList<User> users;

    /***
     * DataBaseUsers: Constructor de la base de datos que muestra datos de prueba
     */
    private DataBaseUsers(){
        users = new ArrayList<>();
        users.add(new User (ColectionCat.bdd.get(0), "Tamzin Otero", "example@servidor.com", "663420564", "C estefania n16", "as.com"));
        users.add(new User (ColectionCat.bdd.get(1), "Carlos Ordoñez", "example@servidor.com", "111111111", "", ""));
        users.add(new User (ColectionCat.bdd.get(2), "Laura Calvente", "example@servidor.com", "663345678", "C Dolores", "as.com"));
        users.add(new User (ColectionCat.bdd.get(0), "Cristina Sola", "example@servidor.com", "643546465", "", ""));
        users.add(new User (ColectionCat.bdd.get(3), "Carmen Dominguez", "example@servidor.com", "635435564", "C Dolores", ""));
        users.add(new User (ColectionCat.bdd.get(4), "Julio Marquez", "example@servidor.com", "753453535", "", "as.com"));
        users.add(new User (ColectionCat.bdd.get(5), "Patricia Ruz", "example@servidor.com", "623242445", "", "as.com"));
        users.add(new User (ColectionCat.bdd.get(1), "Isaac Gomez", "example@servidor.com", "956174545", "", ""));
        users.add(new User (ColectionCat.bdd.get(1), "Patricia Campos", "example@servidor.com", "652434225", "", ""));
        users.add(new User (ColectionCat.bdd.get(0), "Oscar Merrikin", "example@servidor.com", "856784533", "C Dolores", "as.com"));
        users.add(new User (ColectionCat.bdd.get(0), "Salvador Sams", "example@servidor.com", "635435564", "C Dolores", ""));
        users.add(new User (ColectionCat.bdd.get(2), "Eva Rosa Prast", "example@servidor.com", "643546465", "", "as.com"));
        users.add(new User (ColectionCat.bdd.get(3), "Bartolo Gomez", "example@servidor.com", "635435564", "", ""));
        users.add(new User (ColectionCat.bdd.get(5), "Juanito Peludo", "example@servidor.com", "643546465", "", ""));
    }

    /***
     * DataBaseUsers: Inicializa una instancia de la bdd de usuarios.
     * @return instancia de la bdd
     */
    public static DataBaseUsers getInstance() {
        if (instance == null) {
            instance = new DataBaseUsers();
        }
        return instance;
    }

    /**
     * GetUsers: returns all users
     * @return bdd cats
     */
    public static ArrayList<User> getUsers() {return users;}

    /**
     * DeleteUser: Remove an user in array
     * @param user of user that we removed
     */
    public static void deleteUser(User user){users.remove(user);}

    /**
     * AddUserPosition: we add a user in a indicated position
     * @param position where the new user is added
     * @param user that we are going to add
     */
    public static void addUserPosition(int position, User user){users.add(position, user);}

    /***
     * IndexUser: receives a user and returns the index in the bdd
     * @param user
     * @return index in the bdd
     */
    public static int indexUser (User user){return users.indexOf(user);}

    /***
     * IndexUser: receives a position and returns the index in the bdd
     * @param position
     * @return user in the bdd
     */
    public static User userIndex (int position){return users.get(position);}

}
