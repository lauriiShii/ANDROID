package com.example.eclip.app25_fct.bdd.repositories;


import android.arch.lifecycle.LiveData;


import com.example.eclip.app25_fct.bdd.local.AppDataBase;
import com.example.eclip.app25_fct.bdd.model.Student;

import java.util.List;

/**
 * Created by eclip on 17/02/2018.
 */

public class RepositoryStudentImpl implements RepositoryStudent {

    private static RepositoryStudentImpl instance;
    private static AppDataBase database;

    private RepositoryStudentImpl(AppDataBase database) {
        this.database = database;
    }

    public static RepositoryStudentImpl getInstance(AppDataBase database) {
        if (instance == null) {
            instance = new RepositoryStudentImpl(database);
        }
        return instance;
    }

    @Override
    public LiveData<List<Student>> getAllStudents() {
        return database.StudentsDAO().loadAllStudents();
    }

    @Override
    public void insert(Student student) {
        database.StudentsDAO().insert(student);
    }

    @Override
    public int delete(Student student) {
        return database.StudentsDAO().delete(student);
    }

    @Override
    public String getNameById(int id) {
        return database.StudentsDAO().getNameById(id);
    }

    @Override
    public Student getStudentById(String name) {
        return database.StudentsDAO().getStudentById(name);
    }

    @Override
    public int getIdByName(String name) {
        return database.StudentsDAO().getIdByName(name);
    }

    @Override
    public Student getNameUpdate(String newname, String oldname) {
        return database.StudentsDAO().getNameUpdate(newname, oldname);
    }
}