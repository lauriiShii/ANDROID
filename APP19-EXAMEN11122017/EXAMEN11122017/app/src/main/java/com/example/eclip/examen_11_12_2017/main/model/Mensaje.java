package com.example.eclip.examen_11_12_2017.main.model;

import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by eclip on 11/12/2017.
 */

public class Mensaje {

    String mensaje;
    String hora;
    char propietario; // si es B es bot si es M es del usuario
    boolean favorito;

    public Mensaje(String mensaje, char propietario, boolean favorito) {
        this.mensaje = mensaje;
        this.propietario = propietario;
        this.favorito = favorito;
        this.hora = dameHora();
    }

    public Mensaje() {
        this.mensaje = "nuevo mensaje";
        this.propietario = 'B';
        this.favorito = false;
        this.hora = dameHora();
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public char getPropietario() {
        return propietario;
    }

    public void setPropietario(char propietario) {
        this.propietario = propietario;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    private String dameHora() {
        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
            String currentTimeStamp = dateFormat.format(new Date()); // Find todays date

            return currentTimeStamp;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

}
