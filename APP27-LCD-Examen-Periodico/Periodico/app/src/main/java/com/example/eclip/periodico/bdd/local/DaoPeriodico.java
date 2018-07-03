package com.example.eclip.periodico.bdd.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.eclip.periodico.bdd.model.Periodico;

import java.util.List;

/**
 * Created by eclip on 06/03/2018.
 */

@Dao
public interface DaoPeriodico {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Periodico periodico);

    @Update
    public int update(Periodico periodico);

    @Delete
    public int delete(Periodico periodico);

    @Query("SELECT * FROM periodicos")
    public LiveData<List<Periodico>> loadAllPeriodicos();

    @Query("SELECT * FROM periodicos")
    public List<Periodico> loadAllPeriodicosNotLiveData();
}
