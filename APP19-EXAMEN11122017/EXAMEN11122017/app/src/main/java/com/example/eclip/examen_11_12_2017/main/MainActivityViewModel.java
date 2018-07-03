package com.example.eclip.examen_11_12_2017.main;

import android.arch.lifecycle.ViewModel;

import com.example.eclip.examen_11_12_2017.main.bdd.BDDMensajes;
import com.example.eclip.examen_11_12_2017.main.bdd.RepositoryImplMensajes;
import com.example.eclip.examen_11_12_2017.main.bdd.RepositoryMensajes;
import com.example.eclip.examen_11_12_2017.main.model.Mensaje;

import java.util.ArrayList;

/**
 * Created by eclip on 15/11/2017.
 */

@SuppressWarnings("WeakerAccess")
public class MainActivityViewModel extends ViewModel {

    static private ArrayList<Mensaje> data;
    private final RepositoryMensajes repository;

    public MainActivityViewModel(RepositoryMensajes repository) {this.repository = repository;}

    public ArrayList<Mensaje> getData() {
        if (data == null) {
            data = (ArrayList<Mensaje>) repository.getMensajes();
        }
        return data;
    }


    static public void deleteMensaje(Mensaje user) {
        RepositoryImplMensajes.deleteMensaje(user);
    }

    static public Mensaje mensajeIndex(int position){
        return RepositoryImplMensajes.mensajeIndex(position);
    }

    static public int indexMensaje(Mensaje user){
        return RepositoryImplMensajes.indexMensaje(user);
    }




}