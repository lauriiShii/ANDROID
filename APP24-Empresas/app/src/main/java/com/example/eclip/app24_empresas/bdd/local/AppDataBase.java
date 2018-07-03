package com.example.eclip.app24_empresas.bdd.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.eclip.app24_empresas.bdd.model.Company;

/**
 * Created by eclip on 13/02/2018.
 */

@Database(entities = {Company.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    public abstract CompanyDAO companyDAO();

}

