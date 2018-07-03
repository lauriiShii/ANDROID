package com.example.eclip.app22_bottomsheet.bdd.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by eclip on 30/01/2018.
 */

public class User implements Parcelable {
    private String name;
    private String phone;
    private String imagen;
    private static User instance;

    private User() {
        this.name = "Laura Calvente";
        this.phone = "663420564";
        this.imagen = "https://www.popsci.com/sites/popsci.com/files/styles/1000_1x_/public/images/2017/08/cat_in_bowl.jpeg?itok=p8hGzKH4&fc=50,50";
    }

    public static synchronized User getInstance(){
        if(instance == null){
            instance = new User();
        }
        return instance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.phone);
        dest.writeString(this.imagen);
    }

    protected User(Parcel in) {
        this.name = in.readString();
        this.phone = in.readString();
        this.imagen = in.readString();
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
