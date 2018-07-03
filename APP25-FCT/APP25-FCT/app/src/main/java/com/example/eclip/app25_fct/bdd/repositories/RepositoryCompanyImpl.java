package com.example.eclip.app25_fct.bdd.repositories;

import android.arch.lifecycle.LiveData;


import com.example.eclip.app25_fct.bdd.local.AppDataBase;
import com.example.eclip.app25_fct.bdd.model.Company;

import java.util.List;

/**
 * Created by eclip on 17/02/2018.
 */

public class RepositoryCompanyImpl implements RepositoryCompany {

    private static RepositoryCompanyImpl instance;
    private static AppDataBase database;

    private RepositoryCompanyImpl(AppDataBase database) {
        this.database = database;
    }

    public static RepositoryCompanyImpl getInstance(AppDataBase database) {
        if (instance == null) {
            instance = new RepositoryCompanyImpl(database);
        }
        return instance;
    }

    @Override
    public LiveData<List<Company>> getAllBusiness() {
        return database.CompanyDAO().loadAllBusiness();
    }

    @Override
    public void insert(Company company) {
        database.CompanyDAO().insert(company);
    }

    @Override
    public LiveData<List<String>> loadAllNamesBusiness(){
        return database.CompanyDAO().loadAllNamesBusiness();
    }

    @Override
    public  List<Company> loadAllBusinessNotLiveData(){
        return database.CompanyDAO().loadAllBusinessNotLiveData();
    }

    @Override
    public int loadIdByName(String name) {
        return database.CompanyDAO().loadIdByName(name);
    }

    @Override
    public Company loadCompanyByName(String name) {
        return database.CompanyDAO().loadCompanyByName(name);
    }

    @Override
    public Company getCompanyUpdate(String newname, String oldname) {
        return database.CompanyDAO().getCompanyUpdate(newname, oldname);
    }

    @Override
    public Company getCompanyById(String name) {
        return database.CompanyDAO().getCompanyById(name);
    }


}
