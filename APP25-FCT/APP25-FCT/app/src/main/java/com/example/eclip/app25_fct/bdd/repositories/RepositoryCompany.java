package com.example.eclip.app25_fct.bdd.repositories;

import android.arch.lifecycle.LiveData;
import com.example.eclip.app25_fct.bdd.model.Company;
import java.util.List;

/**
 * Created by eclip on 17/02/2018.
 */

@SuppressWarnings("WeakerAccess")
public interface RepositoryCompany {

    LiveData<List<Company>> getAllBusiness();
    void insert(Company company);
    LiveData<List<String>> loadAllNamesBusiness();
    List<Company> loadAllBusinessNotLiveData();
    int loadIdByName(String name);
    Company loadCompanyByName(String name);
    Company getCompanyUpdate(String  newname, String oldname);
    Company getCompanyById(String  name);
}
