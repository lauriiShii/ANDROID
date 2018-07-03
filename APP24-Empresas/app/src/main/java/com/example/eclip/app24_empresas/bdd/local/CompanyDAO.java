package com.example.eclip.app24_empresas.bdd.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.eclip.app24_empresas.bdd.model.Company;

import java.util.List;

/**
 * Created by eclip on 13/02/2018.
 */

@Dao
public interface CompanyDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Company company);

    @Update
    public int update(Company company);

    @Delete
    public int delete(Company company);

    //@Query("SELECT * FROM business")
    //public List<Company> loadAllBusiness();

    @Query("SELECT name FROM business")
    public LiveData<List<String>> loadAllBusiness();
}
