package com.android.lauracalvente.app10_gatitos.bdd.users;

import com.android.lauracalvente.app10_gatitos.bdd.users.model.User;

import java.util.List;

/**
 * Created by eclip on 15/11/2017.
 */

@SuppressWarnings({"WeakerAccess", "unused"})
public interface RepositoryUsers {
    List<User> getUsers();
}
