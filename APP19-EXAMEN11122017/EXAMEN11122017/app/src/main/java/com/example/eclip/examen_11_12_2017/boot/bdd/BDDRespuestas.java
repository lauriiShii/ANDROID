package com.example.eclip.examen_11_12_2017.boot.bdd;

import com.example.eclip.examen_11_12_2017.main.bdd.BDDMensajes;
import com.example.eclip.examen_11_12_2017.main.model.Mensaje;

import java.util.ArrayList;

/**
 * Created by eclip on 11/12/2017.
 */

public class BDDRespuestas {

    public static ArrayList<String> mensajes = new ArrayList<>();

    static {
        mensajes.add("Eres muy guapo");
        mensajes.add("No me caes bien");
        mensajes.add("Voy mal de tiempo");
    }


    public static ArrayList<String> getRespuestas() {return mensajes;}
}
