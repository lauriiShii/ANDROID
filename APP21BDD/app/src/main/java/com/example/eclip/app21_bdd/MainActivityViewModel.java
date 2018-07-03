package com.example.eclip.app21_bdd;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.example.eclip.app21_bdd.bdd.AlumnoDAO;
import com.example.eclip.app21_bdd.bdd.BD;
import com.example.eclip.app21_bdd.bdd.models.Alumno;

import java.util.List;

/**
 * Created by eclip on 30/01/2018.
 */

public class MainActivityViewModel extends AndroidViewModel {

    LiveData<List<String>> alumnos;
    AlumnoDAO alumnoDAO;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        alumnoDAO = BD.getInstance(application.getApplicationContext()).getDb().alumnoDAO();

    }

    private LiveData<List<String>> getAlumnos(){
        if(alumnos == null)
        {
            alumnoDAO.loadAllNombres();
        }
        return alumnos;
    }
}
