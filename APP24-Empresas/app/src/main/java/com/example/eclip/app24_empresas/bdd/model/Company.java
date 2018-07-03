package com.example.eclip.app24_empresas.bdd.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "business",
        indices = {@Index(value = {"name"}, unique = true)}
)
public class Company {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "companyoId")
    private int id;
    @NonNull
    private String name;
    //@NonNull
    private String cif;
    //@NonNull
    private String adress;
    //@NonNull
    private String phone;
    //@NonNull
    private String email;
    //@NonNull
    private String picture;
    //@NonNull
    private String contact;

    @Ignore
    public Company(int id, String nombre) {
        this.id = id;
        this.name = nombre;
    }

    public Company() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(@NonNull String cif) {
        this.cif = cif;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(@NonNull String adress) {
        this.adress = adress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(@NonNull String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(@NonNull String picture) {
        this.picture = picture;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(@NonNull String contact) {
        this.contact = contact;
    }
}