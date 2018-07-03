package com.example.eclip.app14_galeria.model.artWorks.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import static com.example.eclip.app14_galeria.utils.Constantes.HTTP_NO_VALID;
import static com.example.eclip.app14_galeria.utils.Constantes.NO_ITEM_SELECTED;
import static com.example.eclip.app14_galeria.utils.Constantes.SELECT_ART_WORK;
import static com.example.eclip.app14_galeria.utils.Constantes.SELECT_ART_WORK_AUTHOR;

/**
 * Created by eclip on 24/11/2017.
 */

public class ArtWork implements Parcelable {

    private int id;
    private String image;
    private String name;
    private String author;
    private int year;

    public ArtWork(int id, String image, String name, String author, int year) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.author = author;
        this.year = year;
    }
    public ArtWork() {
        this.id = NO_ITEM_SELECTED;
        this.image = HTTP_NO_VALID;
        this.name = SELECT_ART_WORK;
        this.author = SELECT_ART_WORK_AUTHOR;
        this.year = NO_ITEM_SELECTED;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.image);
        dest.writeString(this.name);
        dest.writeString(this.author);
        dest.writeInt(this.year);
    }

    protected ArtWork(Parcel in) {
        this.id = in.readInt();
        this.image = in.readString();
        this.name = in.readString();
        this.author = in.readString();
        this.year = in.readInt();
    }

    public static final Parcelable.Creator<ArtWork> CREATOR = new Parcelable.Creator<ArtWork>() {
        @Override
        public ArtWork createFromParcel(Parcel source) {
            return new ArtWork(source);
        }

        @Override
        public ArtWork[] newArray(int size) {
            return new ArtWork[size];
        }
    };
}
