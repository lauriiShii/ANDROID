package com.example.eclip.examen_11_12_2017.main.bdd;

import com.example.eclip.examen_11_12_2017.main.model.Mensaje;

import java.util.List;

/**
 * Created by eclip on 15/11/2017.
 */

@SuppressWarnings({"WeakerAccess", "unused"})
public interface RepositoryMensajes {
    List<Mensaje> getMensajes();
}
