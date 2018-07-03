package com.android.lauracalvente.app10_gatitos.model.pojos;

import android.os.Parcel;
import android.os.Parcelable;

/**
 *
 * User model class
 *
 * A user has a cat as avatar, name, email, phone, address and web.
 * The object is parcelabre to be able to pass it for an extra of an intent
 *
 * @author Laura Calvente
 * @version 2017
 * @since 1.0
 */

public class User implements Parcelable {
    Cat cat;//Avatar (image and name cat)
    String name;
    String email;
    String phone;
    String direccion;
    String web;

    public User(Cat cat, String name, String email, String phone, String direccion, String web) {
        this.cat = cat;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.direccion = direccion;
        this.web = web;
    }

    public User(){}

    public Cat getCat() {return cat;}

    public void setCat(Cat cat) {this.cat = cat;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.cat, flags);
        dest.writeString(this.name);
        dest.writeString(this.email);
        dest.writeString(this.phone);
        dest.writeString(this.direccion);
        dest.writeString(this.web);
    }

    public User(Parcel in) {
        this.cat = in.readParcelable(Cat.class.getClassLoader());
        this.name = in.readString();
        this.email = in.readString();
        this.phone = in.readString();
        this.direccion = in.readString();
        this.web = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
