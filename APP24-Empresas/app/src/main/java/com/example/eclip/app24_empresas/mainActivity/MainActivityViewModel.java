package com.example.eclip.app24_empresas.mainActivity;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.eclip.app24_empresas.bdd.local.BD;

import java.util.List;

/**
 * Created by eclip on 13/02/2018.
 */

public class MainActivityViewModel extends AndroidViewModel {

    private LiveData<List<String>> names;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        names = BD.getInstance(application.getApplicationContext()).getDb().companyDAO().loadAllBusiness();
    }

    public LiveData<List<String>> getBusiness() {
        return names;
    }
}
