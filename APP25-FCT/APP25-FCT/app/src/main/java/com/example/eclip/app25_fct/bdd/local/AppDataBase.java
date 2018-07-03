package com.example.eclip.app25_fct.bdd.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.eclip.app25_fct.bdd.model.Company;
import com.example.eclip.app25_fct.bdd.model.Student;
import com.example.eclip.app25_fct.bdd.model.Teacher;
import com.example.eclip.app25_fct.bdd.model.Visit;


/**
 * Created by eclip on 13/02/2018.
 */

@Database(entities = {Company.class, Visit.class, Student.class, Teacher.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    public abstract StudentsDAO StudentsDAO();
    public abstract TeachersDAO TeachesDAO();
    public abstract VisitDAO VisitDAO();
    public abstract CompanyDAO CompanyDAO();

}

