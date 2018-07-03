package com.example.eclip.app25_fct.bdd.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.example.eclip.app25_fct.BR;

@Entity(tableName = "business",
        indices = {@Index(value = {"name"}, unique = true)}
)
public class Company extends BaseObservable implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "companyoId")
    private int id;
    @NonNull
    private String name;
    private String cif;
    private String adress;
    private String phone;
    private String email;
    private String picture;
    private String contact;

    @Ignore
    public Company(int id, String nombre) {
        this.id = id;
        this.name = nombre;
    }

    public Company() {
        id = 0;
        name = "";
        cif  = "";
        adress = "";
        phone = "";
        email = "";
        picture = "URL EMPTY";
        contact = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getCif() {
        return cif;
    }

    public void setCif(@NonNull String cif) {
        this.cif = cif;
        notifyPropertyChanged(BR.cif);
    }

    @Bindable
    public String getAdress() {
        return adress;
    }

    public void setAdress(@NonNull String adress) {
        this.adress = adress;
        notifyPropertyChanged(BR.adress);
    }

    @Bindable
    public String getPhone() {
        return phone;
    }

    public void setPhone(@NonNull String phone) {
        this.phone = phone;
        notifyPropertyChanged(BR.phone);
    }

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    @Bindable
    public String getPicture() {
        return picture;
    }

    public void setPicture(@NonNull String picture) {
        this.picture = picture;
        notifyPropertyChanged(BR.picture);
    }

    @Bindable
    public String getContact() {
        return contact;
    }

    public void setContact(@NonNull String contact) {
        this.contact = contact;
        notifyPropertyChanged(BR.contact);
    }

    public String getEmpty (){
        return " ";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.cif);
        dest.writeString(this.adress);
        dest.writeString(this.phone);
        dest.writeString(this.email);
        dest.writeString(this.picture);
        dest.writeString(this.contact);
    }

    protected Company(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.cif = in.readString();
        this.adress = in.readString();
        this.phone = in.readString();
        this.email = in.readString();
        this.picture = in.readString();
        this.contact = in.readString();
    }

    public static final Creator<Company> CREATOR = new Creator<Company>() {
        @Override
        public Company createFromParcel(Parcel source) {
            return new Company(source);
        }

        @Override
        public Company[] newArray(int size) {
            return new Company[size];
        }
    };
}