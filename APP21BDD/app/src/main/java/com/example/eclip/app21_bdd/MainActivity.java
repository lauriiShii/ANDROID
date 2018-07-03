package com.example.eclip.app21_bdd;

import android.arch.lifecycle.ViewModelProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.eclip.app21_bdd.bdd.AlumnoDAO;
import com.example.eclip.app21_bdd.bdd.BD;
import com.example.eclip.app21_bdd.bdd.BdHelper;
import com.example.eclip.app21_bdd.bdd.DbContrat;
import com.example.eclip.app21_bdd.bdd.models.Alumno;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //private SQLiteDatabase bd;
    Button btnAgregar;
    EditText txtNombre;
    ListView lstNombres;
    ArrayAdapter<String> adaptador;
    //AlumnoDAO alumnoDAO;
    MainActivityViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //bd = BdHelper.getInstance(this).getWritableDatabase();
        //alumnoDAO = BD.getInstance(this).getDb().alumnoDAO();
        viewModel = ViewModelProvider.of(this)
        btnAgregar = findViewById(R.id.button);
        txtNombre = findViewById(R.id.text);
        lstNombres = findViewById(R.id.lstNombres);
        btnAgregar.setOnClickListener(View -> agregarAlumno());
        adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, obtenerAlumnos());
        lstNombres.setAdapter(adaptador);
    }

    private List<String> obtenerAlumnos() {
        /*Cursor c = bd.rawQuery("SELECT * FROM alumnos", null);
        ArrayList<String> alumnos = new ArrayList<String>();
        if(c != null){
            c.moveToFirst();
            int id;
            String nombre;
            while (!c.isAfterLast()){
                id = c.getInt(c.getColumnIndexOrThrow(DbContrat.Alumno._ID));
                nombre = c.getString(c.getColumnIndexOrThrow(DbContrat.Alumno.NOMBRE));
                alumnos.add(nombre);
                c.moveToNext();
            }
        }
        return alumnos;*/

        //List<String> alumnos = alumnoDAO.loadAllNombres();
        //return alumnos;
    }

    private void agregarAlumno() {
        //bd.execSQL("INSERT INTO alumnos VALUES(NULL, '"+txtNombre.getText()+"')");
        //ContentValues contentValues = new ContentValues();
        //contentValues.put(DbContrat.Alumno.NOMBRE, txtNombre.getText().toString());
        //if (bd.insert(DbContrat.Alumno.TABLA, null, contentValues) >= 1){
        //    adaptador.add(txtNombre.getText().toString());
        //}

        //alumnoDAO.insertAlumno(new Alumno(0, txtNombre.getText().toString()));
    }
}
