package com.example.eclip.app25_fct.bdd.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.eclip.app25_fct.bdd.model.Student;

import java.util.List;

/**
 * Created by eclip on 27/02/2018.
 */

@Dao
public interface StudentsDAO {


    //** STUDENTS **//

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Student student);

    @Update
    public int update(Student student);

    @Delete
    public int delete(Student student);

    @Query("SELECT * FROM students")
    public LiveData<List<Student>> loadAllStudents();

    @Query("SELECT name FROM students WHERE studentId = :id")
    public String getNameById(int id);

    @Query("SELECT * FROM students WHERE name = :name")
    public Student getStudentById(String  name);

    @Query("SELECT studentId FROM students WHERE name = :name")
    public int getIdByName(String  name);

    @Query("SELECT * FROM students WHERE name = :newname AND name != :oldname")
    public Student getNameUpdate(String  newname, String oldname);
}

