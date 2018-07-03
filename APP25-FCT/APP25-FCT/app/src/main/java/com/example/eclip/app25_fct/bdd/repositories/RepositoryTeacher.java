package com.example.eclip.app25_fct.bdd.repositories;

import android.arch.lifecycle.LiveData;

import com.example.eclip.app25_fct.bdd.model.Teacher;

import java.util.List;

/**
 * Created by eclip on 27/02/2018.
 */

@SuppressWarnings("WeakerAccess")
public interface RepositoryTeacher {

    LiveData<List<Teacher>> getAllTeachers();
    void insert(Teacher teacher);
    List<Teacher> loadAllTeachersNotLiveDeata();
    int loadIdByName(String name);
}