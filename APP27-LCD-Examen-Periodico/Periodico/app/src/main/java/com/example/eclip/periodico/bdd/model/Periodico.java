package com.example.eclip.periodico.bdd.model;

/**
 * Created by eclip on 06/03/2018.
 */

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

import com.example.eclip.periodico.BR;

@Entity(tableName = "periodicos",
        indices = {@Index(value = {"name"}, unique = true)}
)
public class Periodico extends BaseObservable implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "periodicoId")
    private int id;
    @NonNull
    private String name;
    private String url;
    private int visitas;

    @Ignore
    public Periodico(int id, @NonNull String name) {
        this.id = id;
        this.name = name;
    }

    public Periodico(int id, @NonNull String name, int visitas) {
        this.id = id;
        this.name = name;
        this.visitas = visitas;
    }

    public  Periodico(){
        this.name = "";
        this.visitas = 0;
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
    public int getVisitas() {
        return visitas;
    }

    public void setVisitas(int visitas) {
        this.visitas = visitas;
        notifyPropertyChanged(BR.visitas);
    }

    @Bindable
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
        notifyPropertyChanged(BR.url);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.url);
        dest.writeInt(this.visitas);
    }

    protected Periodico(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.url = in.readString();
        this.visitas = in.readInt();
    }

    public static final Creator<Periodico> CREATOR = new Creator<Periodico>() {
        @Override
        public Periodico createFromParcel(Parcel source) {
            return new Periodico(source);
        }

        @Override
        public Periodico[] newArray(int size) {
            return new Periodico[size];
        }
    };
}
