package com.example.eclip.app16_recicleview;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements AlumnoAdapter.OnItemClickListener {

    private static final String STATE_DATOS = "state_datos";
    private static final String STATE_LISTA = "state_lista";

    private AlumnoAdapter mAdaptador;
    private ArrayList<ListItem> mDatos;
    private LinearLayoutManager mLayoutManager;
    private Parcelable mEstadoLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            mDatos = getDatos();
        } else {
            mDatos = savedInstanceState.getParcelableArrayList(STATE_DATOS);
            mEstadoLista = savedInstanceState.getParcelable(STATE_LISTA);
        }
        setupToolbar();
        setupRecyclerView();
    }

    private void setupToolbar() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
    }

    private void setupRecyclerView() {
        RecyclerView lstAlumnos = (RecyclerView) findViewById(R.id.lstAlumnos);
        lstAlumnos.setHasFixedSize(true);
        mAdaptador = new AlumnoAdapter(mDatos);
        mAdaptador.setOnItemClickListener(this);
        lstAlumnos.setAdapter(mAdaptador);
        mLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        lstAlumnos.setLayoutManager(mLayoutManager);
        lstAlumnos.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mEstadoLista = mLayoutManager.onSaveInstanceState();
        outState.putParcelable(STATE_LISTA, mEstadoLista);
        outState.putParcelableArrayList(STATE_DATOS, mAdaptador.getData());
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Se retaura el estado de la lista.
        if (mEstadoLista != null) {
            mLayoutManager.onRestoreInstanceState(mEstadoLista);
        }
    }

    private ArrayList<ListItem> getDatos() {
        ArrayList<ListItem> datos = new ArrayList<>();
        // Primer grupo.
        datos.add(new Grupo("CFGM Sistemas Microinformáticos y Redes"));
        datos.add(new Alumno("Baldomero", 16, "CFGM", "2º"));
        datos.add(new Alumno("Sergio", 27, "CFGM", "1º"));
        datos.add(new Alumno("Atanasio", 17, "CFGM", "1º"));
        datos.add(new Alumno("Oswaldo", 26, "CFGM", "1º"));
        datos.add(new Alumno("Rodrigo", 22, "CFGM", "2º"));
        datos.add(new Alumno("Antonio", 16, "CFGM", "1º"));
        // Segundo grupo.
        datos.add(new Grupo("CFGS Desarrollo de Aplicaciones Multiplataforma"));
        datos.add(new Alumno("Pedro", 22, "CFGS", "2º"));
        datos.add(new Alumno("Pablo", 22, "CFGS", "2º"));
        datos.add(new Alumno("Rodolfo", 21, "CFGS", "1º"));
        datos.add(new Alumno("Gervasio", 24, "CFGS", "2º"));
        datos.add(new Alumno("Prudencia", 20, "CFGS", "2º"));
        datos.add(new Alumno("Gumersindo", 17, "CFGS", "2º"));
        datos.add(new Alumno("Gerardo", 18, "CFGS", "1º"));
        datos.add(new Alumno("Óscar", 21, "CFGS", "2º"));
        return datos;
    }

    @Override
    public void onItemClick(View view, Alumno alumno, int position) {
        Toast.makeText(this, getString(R.string.datos_alumno,
                alumno.getNombre(), alumno.getCurso(), alumno.getCiclo()),
                Toast.LENGTH_SHORT).show();
    }

}