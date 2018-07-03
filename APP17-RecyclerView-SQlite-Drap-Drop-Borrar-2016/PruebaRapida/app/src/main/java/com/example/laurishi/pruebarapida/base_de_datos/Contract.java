package com.example.laurishi.pruebarapida.base_de_datos;

import android.provider.BaseColumns;

/**
 * Creado por Laura Calvente Dominguez, el 20/05/2017.
 */

public class Contract {
    // Nombre de la base de datos
    public static final String BD_NOMBRE = "base_de_datos";
    // Version de la base de datos
    public static final int BD_VERSION = 1;

    // Por cada tabla hacemos una clase estatica como esta:
    // Tabla Pelicula.
    public abstract static class Coche implements BaseColumns {
        // Nombre de la tabla
        public static final String TABLA = "coche";
        // Una constante por cada atributo del objeto
        public static final String MARCA = "marca";
        public static final String MODELO = "modelo";
        public static final String FECHA_MATRICULACION = "fecha_matriculacion";
        public static final String[] TODOS = new String[]{_ID, MODELO, MARCA, FECHA_MATRICULACION};
        // Debe ser siempre asi: "content://(PROYECTO)/(BD_NOMBRE)/(TABLA)"
        public static final String URI = "content://prueba_rapida_antes_del_examen/base_de_datos/coche";
    }

    // Constructor privado para que NO pueda instanciarse.
    private Contract() {
    }

}
