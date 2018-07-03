package com.example.eclip.periodico.bdd.local;

import android.provider.BaseColumns;

/**
 * Created by eclip on 06/03/2018.
 */

public class DbContract {

    public static final String BD_NOMBRE = "appPeriodico";
    public static final int BD_VERSION = 1;

    public abstract static class Company implements BaseColumns {
        public static final String TABLE = "periodicos";
        public static final String NAME = "name";
        public static final String CIF = "visitas";
    }

    private DbContract() {
    }

}
