package com.example.eclip.examen_11_12_2017.main.bdd;

import com.example.eclip.examen_11_12_2017.main.model.Mensaje;

import java.util.ArrayList;

public class BDDMensajes {

    private static BDDMensajes instance;
    private static ArrayList<Mensaje> mensajes;

    private BDDMensajes(){
        mensajes = new ArrayList<>();
        mensajes.add(new Mensaje("Tamzin Otero example@servidor.com", 'M', false));
        mensajes.add(new Mensaje("Carlos Ordoñez example@servidor.com" , 'B', false));
        mensajes.add(new Mensaje("Laura Calvente example@servidor.com", 'M', true));
    }

    public static BDDMensajes getInstance() {
        if (instance == null) {
            instance = new BDDMensajes();
        }
        return instance;
    }

    public static ArrayList<Mensaje> getMensajes() {return mensajes;}

    public static void deleteMenaje(Mensaje mensaje){
        mensajes.remove(mensaje);}

    public static void addMensajePosition(int position, Mensaje mensaje){
        mensajes.add(position, mensaje);}

    public static int indexMensaje (Mensaje mensaje){return mensajes.indexOf(mensaje);}

    public static Mensaje mensajeIndex (int position){return mensajes.get(position);}
}
