package com.example.eclip.app25_fct.mainActivity;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;


import com.example.eclip.app25_fct.bdd.repositories.RepositoryCompany;
import com.example.eclip.app25_fct.bdd.repositories.RepositoryStudent;
import com.example.eclip.app25_fct.bdd.repositories.RepositoryTeacher;
import com.example.eclip.app25_fct.bdd.repositories.RepositoryVisit;
import com.example.eclip.app25_fct.bdd.model.Company;
import com.example.eclip.app25_fct.bdd.model.Student;
import com.example.eclip.app25_fct.bdd.model.Teacher;
import com.example.eclip.app25_fct.bdd.model.Visit;

import java.util.List;

/**
 * Created by eclip on 13/02/2018.
 */

@SuppressWarnings("WeakerAccess")
public class MainActivityViewModel extends ViewModel {

    private RepositoryCompany mRepositoryBusiness;
    private RepositoryStudent mRepositoryStudent;
    private RepositoryTeacher mRepositoryTeacher;
    private RepositoryVisit mRepositoryVisit;

    public MainActivityViewModel(RepositoryCompany repositoryCompany, RepositoryStudent repositoryStudent, RepositoryTeacher repositoryTeacher, RepositoryVisit repositoryVisit ) {
        mRepositoryBusiness = repositoryCompany;
        mRepositoryStudent = repositoryStudent;
        mRepositoryTeacher = repositoryTeacher;
        mRepositoryVisit = repositoryVisit;
    }

    public LiveData<List<Company>> getBusiness() {
        return mRepositoryBusiness.getAllBusiness();
    }

    public LiveData<List<String>> getNamesBusiness() {
        return mRepositoryBusiness.loadAllNamesBusiness();
    }

    public LiveData<List<Student>> getStudents() {
        return mRepositoryStudent.getAllStudents();
    }

    public LiveData<List<Teacher>> getTeachers(){
        return mRepositoryTeacher.getAllTeachers();
    }

    public LiveData<List<Visit>> getVisit(){
        return mRepositoryVisit.getAllVisit();
    }
}
