package com.example.eclip.app21_bdd.bdd;

import android.provider.BaseColumns;

/**
 * Created by eclip on 23/01/2018.
 */

public class DbContrat {

    // Constantes generales de la BD.
    public static final String BD_NOMBRE = "instituto";
    public static final int BD_VERSION = 1;

    // Tabla Alumno.
    public abstract static class Alumno implements BaseColumns {
        public static final String TABLA = "alumnos";
        public static final String NOMBRE = "nombre";
    }

    private DbContrat(){}
}
