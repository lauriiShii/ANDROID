package com.example.eclip.app25_fct.bdd.repositories;


import android.arch.lifecycle.LiveData;


import com.example.eclip.app25_fct.bdd.local.AppDataBase;
import com.example.eclip.app25_fct.bdd.model.Teacher;

import java.util.List;

/**
 * Created by eclip on 17/02/2018.
 */

public class RepositoryTeacherImpl implements RepositoryTeacher {

    private static RepositoryTeacherImpl instance;
    private static AppDataBase database;

    private RepositoryTeacherImpl(AppDataBase database) {
        this.database = database;
    }

    public static RepositoryTeacherImpl getInstance(AppDataBase database) {
        if (instance == null) {
            instance = new RepositoryTeacherImpl(database);
        }
        return instance;
    }

    @Override
    public LiveData<List<Teacher>> getAllTeachers() {
        return database.TeachesDAO().loadAllTeachers();
    }

    @Override
    public List<Teacher> loadAllTeachersNotLiveDeata() {
        return database.TeachesDAO().loadAllTeachersNotLiveDeata();
    }

    @Override
    public int loadIdByName(String name) {
        return database.TeachesDAO().loadIdByName(name);
    }

    @Override
    public void insert(Teacher teacher) {
        database.TeachesDAO().insert(teacher);
    }

}