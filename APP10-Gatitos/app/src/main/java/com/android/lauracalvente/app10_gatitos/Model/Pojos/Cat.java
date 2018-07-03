package com.android.lauracalvente.app10_gatitos.Model.Pojos;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Laura on 18/10/2017.
 */

public class Cat implements Parcelable {

    int src;
    String name;
    Boolean select;

    public Cat(int src, String name, Boolean select) {
        this.src = src;
        this.name = name;
        this.select = select;
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

    public Boolean getSelect() {
        return select;
    }

    public void setSelect(Boolean select) {
        this.select = select;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.src);
        dest.writeString(this.name);
        dest.writeValue(this.select);
    }

    protected Cat(Parcel in) {
        this.src = in.readInt();
        this.name = in.readString();
        this.select = (Boolean) in.readValue(Boolean.class.getClassLoader());
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
