package com.example.eclip.app25_fct.bdd.repositories;

import android.arch.lifecycle.LiveData;

import com.example.eclip.app25_fct.bdd.model.Company;
import com.example.eclip.app25_fct.bdd.model.Student;

import java.util.List;

/**
 * Created by eclip on 27/02/2018.
 */

@SuppressWarnings("WeakerAccess")
public interface RepositoryStudent {

    LiveData<List<Student>> getAllStudents();
    void insert(Student student);
    int delete(Student student);
    String getNameById(int id);
    Student getStudentById(String name);
    int getIdByName(String  name);
    Student getNameUpdate(String  newname, String oldname);
}

