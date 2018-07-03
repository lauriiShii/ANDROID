package com.android.lauracalvente.app10_gatitos.bdd.cats;

import android.os.Parcel;
import android.os.Parcelable;

import com.android.lauracalvente.app10_gatitos.profileActivity.ProfileActivity;

/**
 *
 * Cat model class
 *
 * A cat has a src and name.
 * The object is parcelabre to be able to pass it for an extra of an intent
 *
 * @author Laura Calvente
 * @version 2017
 * @since 1.0
 */

public class Cat implements Parcelable {

    int src;// id image in drawable
    String name;

    public Cat(int src, String name) {
        this.src = src;
        this.name = name;
    }

    public int getSrc() {
        return src;
    }

    public void setSrc(int src) {
        this.src = src;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * GetSelect: necessary to know if this cat is the one that is
     * as current avatar in the main activity
     * @return if avatar
     */
    public boolean getSelect(){
        boolean select = false;
        if (ProfileActivity.cat.getSrc() == this.getSrc())
            select = true;
        return select;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.src);
        dest.writeString(this.name);
    }

    protected Cat(Parcel in) {
        this.src = in.readInt();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<Cat> CREATOR = new Parcelable.Creator<Cat>() {
        @Override
        public Cat createFromParcel(Parcel source) {
            return new Cat(source);
        }

        @Override
        public Cat[] newArray(int size) {
            return new Cat[size];
        }
    };
}
