package com.example.eclip.app25_fct.bdd.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.eclip.app25_fct.bdd.model.Company;
import com.example.eclip.app25_fct.bdd.model.Teacher;

import java.util.List;

/**
 * Created by eclip on 27/02/2018.
 */

@Dao
public interface TeachersDAO {

    //** TEACHERS **//

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Teacher teacher);

    @Query("SELECT * FROM teachers")
    public LiveData<List<Teacher>> loadAllTeachers();

    @Query("SELECT * FROM teachers")
    public List<Teacher> loadAllTeachersNotLiveDeata();

    @Query("SELECT teacherId FROM teachers WHERE name = :name")
    public int loadIdByName(String name);

}
