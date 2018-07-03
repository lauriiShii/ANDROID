package com.android.lauracalvente.app10_gatitos.mainActivity;

import android.arch.lifecycle.ViewModel;

import com.android.lauracalvente.app10_gatitos.bdd.users.RepositoryImplUsers;
import com.android.lauracalvente.app10_gatitos.bdd.users.RepositoryUsers;
import com.android.lauracalvente.app10_gatitos.bdd.users.model.User;

import java.util.ArrayList;

/**
 * Created by eclip on 15/11/2017.
 */

@SuppressWarnings("WeakerAccess")
public class MainActivityViewModel extends ViewModel {

    static private ArrayList<User> data;
    private final RepositoryUsers repository;

    public MainActivityViewModel(RepositoryUsers repository) {this.repository = repository;}

    public ArrayList<User> getData() {
        if (data == null) {
            data = (ArrayList<User>) repository.getUsers();
        }
        return data;
    }

    static public void deleteUser(User user) {
        RepositoryImplUsers.deleteUser(user);
    }

    static public void addUser(int position, User user){
        RepositoryImplUsers.addUserPosition(position, user);
    }

    static public User userIndex(int position){
        return RepositoryImplUsers.userIndex(position);
    }

    static public int indexUser(User user){
        return RepositoryImplUsers.indexUser(user);
    }



}