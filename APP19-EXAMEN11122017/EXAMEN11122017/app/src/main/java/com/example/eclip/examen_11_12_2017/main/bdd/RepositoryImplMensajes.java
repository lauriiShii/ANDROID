package com.example.eclip.examen_11_12_2017.main.bdd;

import com.example.eclip.examen_11_12_2017.main.model.Mensaje;

import java.util.List;

/**
 * Created by eclip on 15/11/2017.
 */

public class RepositoryImplMensajes implements RepositoryMensajes {

    private static RepositoryImplMensajes instance;
    private static BDDMensajes database;

    private RepositoryImplMensajes(BDDMensajes database) {
        this.database = database;
    }

    public static RepositoryImplMensajes getInstance(BDDMensajes database) {
        if (instance == null) {
            instance = new RepositoryImplMensajes(database);
        }
        return instance;
    }

    @Override
    public List<Mensaje> getMensajes() {
        return database.getMensajes();
    }

    static public void deleteMensaje(Mensaje mensaje) {
        database.deleteMenaje(mensaje);
    }
    static public int indexMensaje(Mensaje mensaje) {return database.indexMensaje(mensaje);}
    static public Mensaje mensajeIndex (int position){return database.mensajeIndex(position);}




}
