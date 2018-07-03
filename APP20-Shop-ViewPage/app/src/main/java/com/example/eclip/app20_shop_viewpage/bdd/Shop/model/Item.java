package com.example.eclip.app20_shop_viewpage.bdd.Shop.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by eclip on 14/01/2018.
 */

public class Item implements Parcelable {

    private String name;
    private String factory;
    private String src;
    private float price;
    private int valueFans;

    public Item(String name, String factory, String src, float price, int valueFans) {
        this.name = name;
        this.factory = factory;
        this.src = src;
        this.price = price;
        this.valueFans = valueFans;
    }

    public Item(){
        name = "";
        factory =  "";
        src = "";
        price = 0.0f;
        valueFans = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getValueFans() {
        return valueFans;
    }

    public void setValueFans(int valueFans) {
        this.valueFans = valueFans;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.factory);
        dest.writeString(this.src);
        dest.writeFloat(this.price);
        dest.writeInt(this.valueFans);
    }

    protected Item(Parcel in) {
        this.name = in.readString();
        this.factory = in.readString();
        this.src = in.readString();
        this.price = in.readFloat();
        this.valueFans = in.readInt();
    }

    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel source) {
            return new Item(source);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}
