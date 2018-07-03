package com.example.eclip.app25_fct.bdd.local;

import android.provider.BaseColumns;

/**
 * Created by eclip on 13/02/2018.
 */

public class DbContract {

    public static final String BD_NOMBRE = "app25fct";
    public static final int BD_VERSION = 1;

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

    public abstract static class Student implements BaseColumns {
        public static final String TABLE = "students";
        public static final String NAME = "name";
        public static final String PHONE = "phone";
        public static final String EMAIL = "email";
        public static final String ID_EMPRESA = "id_empresa";
        public static final String ID_TUTOR = "id_tutor";
    }

    public abstract static class Visit implements BaseColumns {
        public static final String TABLE = "visits";
        public static final String ID_TUTOR = "id_tutor";
        public static final String ID_STUTENT = "id_student";
        public static final String DAY = "day";
        public static final String START_TIME = "start_time";
        public static final String END_TIME = "end_time";
    }

    public abstract static class Teacher implements BaseColumns {
        public static final String TABLE = "teachers";
        public static final String NAME = "name";
    }

    private DbContract() {
    }
}
