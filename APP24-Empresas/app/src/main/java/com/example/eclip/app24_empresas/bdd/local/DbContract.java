package com.example.eclip.app24_empresas.bdd.local;

import android.provider.BaseColumns;

/**
 * Created by eclip on 13/02/2018.
 */

public class DbContract {

    // Constantes generales de la BD.
    public static final String BD_NOMBRE = "crud";
    public static final int BD_VERSION = 1;

    // Tabla Alumno.
    public abstract static class Company implements BaseColumns {
        public static final String TABLE = "business";
        public static final String NAME = "name";
        public static final String CIF = "cif";
        public static final String ADRESS = "adress";
        public static final String PHONE = "phone";
        public static final String EMAIL = "email";
        public static final String PICTURE = "picture";
        public static final String CONTACT = "contact";
    }

    private DbContract() {
    }
}
