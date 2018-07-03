package com.example.eclip.app21_bdd.bdd;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.eclip.app21_bdd.bdd.models.Alumno;

import java.util.List;

/**
 * Created by eclip on 30/01/2018.
 */

@Dao
public interface AlumnoDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAlumno(Alumno alumno);

    @Update
    public int updateAlumno(Alumno alumno);

    @Delete
    public int deleteAlumno(Alumno alumno);

    @Query("SELECT * FROM alumnos")
    public List<Alumno> loadAllALumnos();

    @Query("SELECT nombre FROM alumnos")
    public LiveData<List<String>> loadAllNombres();
}
