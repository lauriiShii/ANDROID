package com.example.eclip.app25_fct.bdd.repositories;

import android.arch.lifecycle.LiveData;

import com.example.eclip.app25_fct.bdd.model.Company;
import com.example.eclip.app25_fct.bdd.model.Visit;

import java.util.List;

/**
 * Created by eclip on 27/02/2018.
 */

@SuppressWarnings("WeakerAccess")
public interface RepositoryVisit {

    LiveData<List<Visit>> getAllVisit();
    void insert(Visit visit);
    List<Visit> loadAllVisitById(int id);
    List<Visit> loadAllVisitNotOK();
    int delete(Visit visit);
    List<Visit> loadAllVisitNotLiveData(int id);
}