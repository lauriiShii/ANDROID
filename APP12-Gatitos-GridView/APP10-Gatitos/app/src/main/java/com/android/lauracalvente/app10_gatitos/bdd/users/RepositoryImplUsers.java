package com.android.lauracalvente.app10_gatitos.bdd.users;

import com.android.lauracalvente.app10_gatitos.bdd.users.model.User;

import java.util.List;

/**
 * Created by eclip on 15/11/2017.
 */

public class RepositoryImplUsers implements RepositoryUsers {

    private static RepositoryImplUsers instance;
    private static DataBaseUsers database;

    private RepositoryImplUsers(DataBaseUsers database) {
        this.database = database;
    }

    public static RepositoryImplUsers getInstance(DataBaseUsers database) {
        if (instance == null) {
            instance = new RepositoryImplUsers(database);
        }
        return instance;
    }

    @Override
    public List<User> getUsers() {
        return database.getUsers();
    }


    static public void deleteUser(User user) {
        database.deleteUser(user);
    }

    public static void addUserPosition(int position, User user){
        database.addUserPosition(position, user);
    }

    static public int indexUser(User user) {
        return database.indexUser(user);
    }

    static public User userIndex (int position){return database.userIndex(position);}

}
