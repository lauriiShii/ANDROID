package com.example.laurishi.pruebarapida.actividades;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laurishi.pruebarapida.R;
import com.example.laurishi.pruebarapida.base_de_datos.Dao;
import com.example.laurishi.pruebarapida.fragmentos.DatePicker_DialogFragment;
import com.example.laurishi.pruebarapida.modelos.Coche;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NuevoCocheActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    @BindView(R.id.txtMarca)
    EditText txtMarca;
    @BindView(R.id.txtModelo)
    EditText txtModelo;
    @BindView(R.id.txtFecha)
    TextView txtFecha;
    boolean nuevoCoche = false;
    Coche coche;

    public static final String EXTRA_COCHE = "EXTRA_COCHE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_coche);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        coche = intent.getParcelableExtra(EXTRA_COCHE);

        if (coche.fecha_matriculacion.equals("dd-MM-yyyy"))
            nuevoCoche = true;

        txtMarca.setText(coche.marca);
        txtModelo.setText(coche.modelo);
        txtFecha.setText(coche.fecha_matriculacion);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflador = getMenuInflater();
        inflador.inflate(R.menu.nuevo_coche_items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnuGuardar:
                guardar();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    private void guardar() {
        if (txtMarca.getText().toString().trim().equals("") || txtModelo.getText().toString().trim().equals("") || txtFecha.getText().toString().trim().equals(""))
            Toast.makeText(this, "Tienes que completar correctamente el formulario", Toast.LENGTH_SHORT).show();
        else {
            coche.modelo=txtModelo.getText().toString().trim();
            coche.marca=txtMarca.getText().toString().trim();
            coche.fecha_matriculacion = txtFecha.getText().toString().trim();
            if (nuevoCoche)
                Dao.getInstance(this).createItem(coche);
            else
                Dao.getInstance(this).updateItem(coche);

            finish();
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        txtFecha.setText(dayOfMonth + "-" + month + "-" + year);
    }

    @OnClick(R.id.txtFecha)
    public void onViewClicked() {
        new DatePicker_DialogFragment().show(getSupportFragmentManager(), "DatePickerDialogFragment");
    }


}
