package com.example.laurishi.pruebarapida.base_de_datos;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import com.example.laurishi.pruebarapida.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Creado por Laura Calvente Dominguez, el 20/05/2017.
 */

public class Helper extends SQLiteOpenHelper {

    private static final String TBL_ITEM_DROP = "drop table if exists " + Contract.Coche.TABLA;

    private static Helper sInstance;
    private final AssetManager mAssets;
    private final String mTag;

    // Constructor. Recibe el contexto.
    private Helper(Context context) {
        // Se llama al constructor del padre, que es quien realmente crea o
        // actualiza la versión de BD si es necesario.
        super(context, Contract.BD_NOMBRE, null, Contract.BD_VERSION);
        mAssets = context.getAssets();
        mTag = context.getString(R.string.app_name);
    }

    // Retorna la instancia (única) del helper.
    public static synchronized Helper getInstance(Context context) {
        // Al usar el contexto de la aplicación nos aseguramos de que no haya
        // memory leaks si el recibido es el contexto de una actividad.
        if (sInstance == null) {
            sInstance = new Helper(context.getApplicationContext());
        }
        return sInstance;
    }

    // Se llama automáticamente en API >= 16 para configurar la base de datos, una vez
    // creado el archivo de base de la base de datos y haberla abierto, y antes de
    // llamar al onCreate.
    @Override
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        // Se activa el log de la base de datos.
        setWriteAheadLoggingEnabled(true);
        // Se activa el uso de foreign keys en SQLite.
        db.setForeignKeyConstraintsEnabled(true);
    }

    // Se llama una vez creado el archivo de la base de datos y haberla abierto,
    // después del onConfigure, para que ejecutemos las sentencias SQL de creación.
    @Override
    public void onCreate(SQLiteDatabase db) {
        // A partir de la API 16 se llama al método onConfigure().
        // Debe hacerse antes de crear las tablas.
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            // Se activa el log de la base de datos
            db.enableWriteAheadLogging();
            if (!db.isReadOnly()) {
                // Se activa el uso de foreign keys en SQLite.
                db.execSQL("PRAGMA foreign_keys = ON;");
            }
        }
        // Se ejecutan las sentencias SQL de creación de las tablas de la BD.
        ejecutarFicheroSQLAssets(db, Contract.BD_VERSION);
    }

    // Método de callback para cuando la BD debe ser actualizada de versión.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Se eliminan las tablas y se recrean de nuevo (solo hacer en versión de desarrollo).
        db.execSQL(TBL_ITEM_DROP);
        ejecutarFicheroSQLAssets(db, Contract.BD_VERSION);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Se eliminan las tablas y se recrean de nuevo (solo hacer en versión de desarrollo).
        db.execSQL(TBL_ITEM_DROP);
        ejecutarFicheroSQLAssets(db, Contract.BD_VERSION);
    }

    // Ejecuta contra la base de datos reciibida las sentencias SQL contenidas
    // en un archivo de Assets.
    private void ejecutarFicheroSQLAssets(SQLiteDatabase db, int version) {
        BufferedReader reader = null;
        try {
            // Se abre el archivo de Assets (p.e.
            String filename = String.format("%d.sql", version);
            final InputStream inputStream =
                    mAssets.open(filename);
            reader =
                    new BufferedReader(new InputStreamReader(inputStream));
            // Sentencia SQL.
            final StringBuilder statement = new StringBuilder();

            for (String line; (line = reader.readLine()) != null; ) {
                // Si es una línea válida (se ignoran las líneas en blanco y
                // las de comentario SQL).
                if (!TextUtils.isEmpty(line) && !line.startsWith("--")) {
                    // Se añade a la sentencia (limpiando los espacios en blanco
                    // sobrantes.
                    statement.append(line.trim());
                }
                // Si es una sentencia válida.
                if (line.endsWith(";")) {
                    // Se ejecuta.
                    db.execSQL(statement.toString());
                    // Se reinicializa.
                    statement.setLength(0);
                }
            }
        } catch (IOException e) {
            Log.e(mTag, "Se ha producido un error al cargar el fichero SQL", e);
        } finally {
            // Si el lector estaba abierto, se cierra.
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.w(mTag, "No se ha podido cerrar el lector del fichero SQL", e);
                }
            }
        }
    }

}
