package com.example.eclip.app25_fct.bdd.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.eclip.app25_fct.bdd.model.Visit;

import java.util.List;

/**
 * Created by eclip on 27/02/2018.
 */

@Dao
public interface VisitDAO {

    //** VISIT **/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Visit visit);

    @Update
    public int update(Visit visit);

    @Delete
    public int delete(Visit visit);

    @Query("SELECT * FROM visits")
    public LiveData<List<Visit>> loadAllVisit();

    @Query("SELECT * FROM visits WHERE student = :id")
    public List<Visit> loadAllVisitNotLiveData(int id);

    @Query("SELECT * FROM visits WHERE student = :id")
    public List<Visit> loadAllVisitById(int id);


    @Query("SELECT * FROM visits WHERE observe IS '' OR observe IS NULL ")
    public List<Visit> loadAllVisitNotOK();

}
