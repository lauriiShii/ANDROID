package com.example.eclip.app21_bdd.bdd.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by eclip on 23/01/2018.
 */
@Entity(tableName = "Alumnos", indices = {@Index(value = ("nombre"), unique = true)})
public class Alumno {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "alumnoId")
    private int id;

    @NonNull
    private String nombre;

    public Alumno(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Alumno() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
