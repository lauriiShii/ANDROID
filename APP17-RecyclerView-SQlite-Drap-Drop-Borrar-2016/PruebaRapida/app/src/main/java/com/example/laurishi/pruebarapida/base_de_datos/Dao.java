package com.example.laurishi.pruebarapida.base_de_datos;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.example.laurishi.pruebarapida.modelos.Coche;

import java.util.ArrayList;

/**
 * Creado por Laura Calvente Dominguez, el 20/05/2017.
 */

public class Dao {
    private static Dao sInstance;

    private final Helper mHelper; // Ayudante para la creación y gestión de la BD.
    private final ContentResolver mContentResolver;

    // Constructor. Recibe el contexto.
    private Dao(Context contexto) {
        // Se obtiene el mHelper.
        mHelper = Helper.getInstance(contexto);
        mContentResolver = contexto.getContentResolver();
    }

    // Retorna la instancia (única) del helper.
    public static synchronized Dao getInstance(Context context) {
        // Al usar el contexto de la aplicación nos aseguramos de que no haya
        // memory leaks si el recibido es el contexto de una actividad.
        if (sInstance == null) {
            sInstance = new Dao(context);
        }
        return sInstance;
    }

    // Abre la base de datos para escritura.
    public SQLiteDatabase openWritableDatabase() {
        return mHelper.getWritableDatabase();
    }

    // Cierra la base de datos.
    public void closeDatabase() {
        mHelper.close();
    }

    // CRUD (Create-Read-Update-Delete) de la tabla item

    // Inserta un item en la tabla item.
    // Recibe el objeto Item a insertar.
    // Retorna el _id del item una vez insertado o -1 si se ha producido un
    // error.
    public long createItem(Coche coche) {
        // Se abre la base de datos.
        SQLiteDatabase bd = mHelper.getWritableDatabase();
        // Se crea la lista de pares campo-valor para realizar la inserción.
        ContentValues valores = coche.toContentValues();
        // Se realiza el insert
        long resultado = bd.insert(Contract.Coche.TABLA, null, valores);
        // Se cierra la base de datos.
        mHelper.close();
        // Se notifica.
        mContentResolver.notifyChange(
                Uri.parse(Contract.Coche.URI), null);
        // Se retorna el _id del item insertado o -1 si error.
        return resultado;
    }

    // Borra de la BD un Item. Recibe el _id del Item a borrar. Retorna true
    // si se ha realizado la eliminación con éxito.
    public boolean deleteItem(long id) {
        // Se abre la base de datos.
        SQLiteDatabase bd = mHelper.getWritableDatabase();
        // Se realiza el delete.
        long resultado = bd.delete(Contract.Coche.TABLA, Contract.Coche._ID + " = "
                + id, null);
        // Se cierra la base de datos.
        mHelper.close();
        // Se notifica.
        mContentResolver.notifyChange(
                Uri.parse(Contract.Coche.URI), null);
        // Se retorna si se ha eliminado algún Item.
        return resultado > 0;

    }

    // Actualiza en la BD los datos de un Item. Recibe el Item. Retorna true
    // si la actualización se ha realizado con éxito.
    public boolean updateItem(Coche coche) {
        // Se abre la base de datos.
        SQLiteDatabase bd = mHelper.getWritableDatabase();
        // Se crea la lista de pares clave-valor con cada campo-valor.
        ContentValues valores = coche.toContentValues();
        // Se realiza el update.
        long resultado = bd.update(Contract.Coche.TABLA, valores, Contract.Coche._ID
                + " = " + coche.id, null);
        // Se cierra la base de datos.
        mHelper.close();
        // Se notifica.
        mContentResolver.notifyChange(
                Uri.parse(Contract.Coche.URI), null);
        // Se retorna si ha actualizado algún Item.
        return resultado > 0;

    }

    // Consulta en la BD los datos de un Item. Recibe el _id del Item a
    // consultar. Retorna el objeto Item o null si no existe.
    public Coche queryItem(long id) {
        // Se abre la base de datos.
        SQLiteDatabase bd = mHelper.getWritableDatabase();
        // Se realiza la query SQL sobre la BD.
        Cursor cursor = bd.query(true, Contract.Coche.TABLA,
                Contract.Coche.TODOS, Contract.Coche._ID + " = " + id,
                null, null, null, null, null);
        // Se mueve al primer registro del cursor.
        Coche coche = null;
        if (cursor != null) {
            cursor.moveToFirst();
            // Retorno el objeto Item correspondiente.
            coche = cursorToItem(cursor);
        }
        // Se cierra la base de datos.
        mHelper.close();
        // Se retorna el Item o null.
        return coche;
    }

    // Consulta en la BD todos los Items. Retorna el cursor resultado de la
    // consulta (puede ser null si no hay Items), ordenados alfabéticamente
    // por nombre.
    public Cursor queryAllItems() {
        // Se abre la base de datos.
        SQLiteDatabase bd = mHelper.getWritableDatabase();
        // Se realiza la consulta y se retorna el cursor.
        return bd.query(Contract.Coche.TABLA, Contract.Coche.TODOS, null,
                null, null, null, Contract.Coche.MODELO);
    }

    // Consulta en la BD todos los Items. Retorna una lista de objeto Item,
    // ordenados alfabéticamente por nombre.
    public ArrayList<Coche> getAllItems() {
        ArrayList<Coche> lista = new ArrayList<>();
        // Se consultan todos los Items en la BD y obtiene un cursor.
        Cursor cursor = this.queryAllItems();
        if (cursor != null) {
            lista = cursorToItems(cursor);
        }
        // Se cierra el cursor (IMPORTANTE).
        cursor.close();
        // Se cierra la base de datos.
        mHelper.close();
        // Se retorna la lista.
        return lista;
    }

    public static ArrayList<Coche> cursorToItems(Cursor cursor) {
        ArrayList<Coche> lista = new ArrayList<>();
        // Se convierte cada registro del cursor en un elemento de la lista.
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Coche coche = cursorToItem(cursor);
            lista.add(coche);
            cursor.moveToNext();
        }
        // Se retorna la lista.
        return lista;
    }

    // Crea un objeto Item a partir del registro actual de un cursor. Recibe
    // el cursor y retorna un nuevo objeto Item cargado con los datos del
    // registro actual del cursor.
    public static Coche cursorToItem(Cursor cursorItem) {
        // Crea un objeto Item y guarda los valores provenientes
        // del registro actual del cursor.
        Coche coche = new Coche("-", "-", "-");
        coche.id = cursorItem.getLong(
                cursorItem.getColumnIndexOrThrow(Contract.Coche._ID));
        coche.marca = cursorItem.getString(
                cursorItem.getColumnIndexOrThrow(Contract.Coche.MARCA));
        coche.modelo = cursorItem.getString(
                cursorItem.getColumnIndexOrThrow(Contract.Coche.MODELO));
        coche.fecha_matriculacion = cursorItem.getString(
                cursorItem.getColumnIndexOrThrow(Contract.Coche.FECHA_MATRICULACION));
        ;

        // Se retorna el objeto Item.
        return coche;
    }

}

