package com.example.laurishi.pruebarapida.modelos;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.laurishi.pruebarapida.base_de_datos.Contract;

/**
 * Created by Laura Calvente Dominguez on 08/06/2017.
 */

public class Coche implements Parcelable {
    public static long NUM_ID = 0;
    public long id = 0;
    public String modelo;
    public String marca;
    public String fecha_matriculacion;


    public Coche(String modelo, String marca, String fecha_matriculacion) {
        this.id = NUM_ID;
        NUM_ID++;
        this.modelo = modelo;
        this.marca = marca;
        this.fecha_matriculacion = fecha_matriculacion;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getFecha_matriculacion() {
        return fecha_matriculacion;
    }

    public void setFecha_matriculacion(String fecha_matriculacion) {
        this.fecha_matriculacion = fecha_matriculacion;
    }

    public long getId() {
        return id;
    }

    // SQLite. Si vas a usar SQLite, necesitaras este metodo:
    public ContentValues toContentValues() {
        ContentValues valores = new ContentValues();
        valores.put(Contract.Coche.MARCA, marca);
        valores.put(Contract.Coche.MODELO, modelo);
        valores.put(Contract.Coche.FECHA_MATRICULACION, fecha_matriculacion);
        return valores;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.modelo);
        dest.writeString(this.marca);
        dest.writeString(this.fecha_matriculacion);
    }

    protected Coche(Parcel in) {
        this.id = in.readLong();
        this.modelo = in.readString();
        this.marca = in.readString();
        this.fecha_matriculacion = in.readString();
    }

    public static final Parcelable.Creator<Coche> CREATOR = new Parcelable.Creator<Coche>() {
        @Override
        public Coche createFromParcel(Parcel source) {
            return new Coche(source);
        }

        @Override
        public Coche[] newArray(int size) {
            return new Coche[size];
        }
    };
}
