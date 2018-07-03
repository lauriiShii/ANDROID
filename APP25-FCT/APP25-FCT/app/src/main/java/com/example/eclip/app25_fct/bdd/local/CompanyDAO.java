package com.example.eclip.app25_fct.bdd.local;

/**
 * Created by eclip on 27/02/2018.
 */

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.eclip.app25_fct.bdd.model.Company;

import java.util.List;

@Dao
public interface CompanyDAO {

    //** BUSINESS **//

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Company company);

    @Update
    public int update(Company company);

    @Delete
    public int delete(Company company);

    @Query("SELECT * FROM business")
    public LiveData<List<Company>> loadAllBusiness();

    @Query("SELECT * FROM business")
    public List<Company> loadAllBusinessNotLiveData();

    @Query("SELECT name FROM business")
    public LiveData<List<String>> loadAllNamesBusiness();

    @Query("SELECT companyoId FROM business WHERE name = :name")
    public int loadIdByName(String name);

    @Query("SELECT * FROM business WHERE name = :name")
    public Company loadCompanyByName(String name);

    @Query("SELECT * FROM business WHERE name = :name")
    public Company getCompanyById(String  name);

    @Query("SELECT * FROM business WHERE name = :newname AND name != :oldname")
    public Company getCompanyUpdate(String  newname, String oldname);
}
