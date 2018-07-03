package com.example.eclip.app25_fct.mainActivity;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.example.eclip.app25_fct.bdd.repositories.RepositoryCompany;
import com.example.eclip.app25_fct.bdd.repositories.RepositoryStudent;
import com.example.eclip.app25_fct.bdd.repositories.RepositoryTeacher;
import com.example.eclip.app25_fct.bdd.repositories.RepositoryVisit;


/**
 * Created by eclip on 17/02/2018.
 */

@SuppressWarnings("WeakerAccess")
public class MainActivityViewModelFactory extends ViewModelProvider.NewInstanceFactory{

    private final RepositoryCompany repositoryComnpany;
    private final RepositoryStudent repositoryStudent;
    private final RepositoryTeacher repositoryTeacher;
    private final RepositoryVisit repositoryVisit;

    public MainActivityViewModelFactory(RepositoryCompany repositoryComnpany, RepositoryStudent repositoryStudent, RepositoryTeacher repositoryTeacher, RepositoryVisit repositoryVisit) {
        this.repositoryComnpany = repositoryComnpany;
        this.repositoryStudent = repositoryStudent;
        this.repositoryTeacher = repositoryTeacher;
        this.repositoryVisit = repositoryVisit;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new MainActivityViewModel(repositoryComnpany, repositoryStudent, repositoryTeacher, repositoryVisit);
    }
}
