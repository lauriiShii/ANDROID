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


/**
 * Created by eclip on 22/02/2018.
 */

@Entity(tableName = "teachers",
        indices = {@Index(value = {"name"}, unique = true)}
)
public class Teacher extends BaseObservable implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "teacherId")
    private int id;
    @NonNull
    private String name;

    @Ignore
    public Teacher(int id, String nombre) {
        this.id = id;
        this.name = nombre;
    }

    public Teacher() {
        id = 0;
        name = "";
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
    }

    protected Teacher(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
    }

    public static final Creator<Teacher> CREATOR = new Creator<Teacher>() {
        @Override
        public Teacher createFromParcel(Parcel source) {
            return new Teacher(source);
        }

        @Override
        public Teacher[] newArray(int size) {
            return new Teacher[size];
        }
    };
}
