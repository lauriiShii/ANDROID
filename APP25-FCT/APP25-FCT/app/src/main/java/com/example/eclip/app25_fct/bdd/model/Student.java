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

@Entity(tableName = "students",
        indices = {@Index(value = {"name"}, unique = true)}
)
public class Student extends BaseObservable implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "studentId")
    private int id;
    @NonNull
    private String name;
    private String phone;
    private String email;
    private int company;
    private int teacher;
    private String teacherPhone;
    private String hour;

    public Student() {
        name = "";
        phone = "";
        email = "";
        company = 1;
        teacher = 1;
        teacherPhone = "";
        hour = "";
    }

    @Ignore
    public Student(int id, String name) {
        this.id = id;
        this.name = name;
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
    public int getCompany() {
        return company;
    }

    public void setCompany(@NonNull int company) {
        this.company = company;
        notifyPropertyChanged(BR.company);
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
    public String getHour() {
        return hour;
    }

    public void setHour(@NonNull String hour) {
        this.hour = hour;
        notifyPropertyChanged(BR.hour);
    }

    @Bindable
    public String getTeacherPhone() {
        return teacherPhone;
    }

    public void setTeacherPhone(@NonNull String teacherPhone) {
        this.teacherPhone = teacherPhone;
        notifyPropertyChanged(BR.teacherPhone);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.phone);
        dest.writeString(this.email);
        dest.writeInt(this.company);
        dest.writeInt(this.teacher);
        dest.writeString(this.teacherPhone);
        dest.writeString(this.hour);
    }

    protected Student(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.phone = in.readString();
        this.email = in.readString();
        this.company = in.readInt();
        this.teacher = in.readInt();
        this.teacherPhone = in.readString();
        this.hour = in.readString();
    }

    public static final Parcelable.Creator<Student> CREATOR = new Parcelable.Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel source) {
            return new Student(source);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };
}
