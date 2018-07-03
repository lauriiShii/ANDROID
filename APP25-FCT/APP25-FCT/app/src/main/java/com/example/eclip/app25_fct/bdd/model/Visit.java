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

import java.util.Random;


/**
 * Created by eclip on 22/02/2018.
 */

@Entity(tableName = "visits",
        indices = {@Index(value = {"day"})}
)
public class Visit extends BaseObservable implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "visitId")
    private int id;
    private int  teacher;
    private int student;
    private String day;
    private String startHour;
    private String endHour;
    private String observe;

    @Ignore
    public Visit(int id, int teacher, int student, String day, String startHour, String endHour) {
        this.id = id;
        this.teacher = teacher;
        this.student = student;
        this.day = day;
        this.startHour = startHour;
        this.endHour = endHour;
    }

    @Ignore
    public Visit(@NonNull int teacher, @NonNull int student, @NonNull String day) {
        this.teacher = teacher;
        this.student = student;
        this.day = day;
    }

    public Visit() {
        id = 0;
        teacher = 0;
        student = 0;
        day = "";
        startHour = "";
        endHour = "";
        observe = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Bindable
    public int getTeacher() {
        return teacher;
    }

    public void setTeacher(@NonNull int teacher) {
        this.teacher = teacher;
        notifyPropertyChanged(BR.teacher);
    }

    @Bindable
    public int getStudent() {
        return student;
    }

    public void setStudent(@NonNull int student) {
        this.student = student;
        notifyPropertyChanged(BR.student);
    }

    @Bindable
    public String getDay() {
        return day;
    }

    public void setDay(@NonNull String day) {
        this.day = day;
        notifyPropertyChanged(BR.day);
    }

    @Bindable
    public String getStartHour() {
        return startHour;
    }

    public void setStartHour(String startHour) {
        this.startHour = startHour;
        notifyPropertyChanged(BR.startHour);
    }

    @Bindable
    public String getEndHour() {
        return endHour;
    }

    public void setEndHour(String endHour) {
        this.endHour = endHour;
        notifyPropertyChanged(BR.endHour);
    }
    @Bindable
    public String getObserve() {
        return observe;
    }

    public void setObserve(String observe) {
        this.observe = observe;
        notifyPropertyChanged(BR.observe);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.teacher);
        dest.writeInt(this.student);
        dest.writeString(this.day);
        dest.writeString(this.startHour);
        dest.writeString(this.endHour);
        dest.writeString(this.observe);
    }

    protected Visit(Parcel in) {
        this.id = in.readInt();
        this.teacher = in.readInt();
        this.student = in.readInt();
        this.day = in.readString();
        this.startHour = in.readString();
        this.endHour = in.readString();
        this.observe = in.readString();
    }

    public static final Creator<Visit> CREATOR = new Creator<Visit>() {
        @Override
        public Visit createFromParcel(Parcel source) {
            return new Visit(source);
        }

        @Override
        public Visit[] newArray(int size) {
            return new Visit[size];
        }
    };
}
