package es.android.lauracalvente.examenprimertrimestre;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

/**
 * Created by Laura on 09/02/2017.
 */

public class NuevoPersonaje extends AppCompatActivity {

    Toolbar toolbar;
    TextView txtNombre, txtPersonaje, txtUri, txtTemporada, txtDescripcion;
    CheckBox chkPrincipal;
    ImageView imgPortada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nuevo_personaje);
        views();
    }

    private void views() {

        //Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Datos
        txtNombre = (TextView) findViewById(R.id.txtActor);
        txtPersonaje = (TextView) findViewById(R.id.txtPersonaje);
        txtUri = (TextView) findViewById(R.id.txtUri);
        txtTemporada = (TextView) findViewById(R.id.txtTemporada);
        txtDescripcion = (TextView) findViewById(R.id.txtDescripcion);
        chkPrincipal = (CheckBox) findViewById(R.id.chkPrincipal);
        imgPortada = (ImageView) findViewById(R.id.imgPortada);

        txtUri.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus && !TextUtils.isEmpty(txtUri.getText().toString()))
                    Picasso.with(txtUri.getContext()).load(txtUri.getText().toString()).into(imgPortada);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nuevo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.guardar:
                agregarPersonaje();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void agregarPersonaje(){
        String nombre = txtNombre.getText().toString();
        String personaje = txtPersonaje.getText().toString();
        String temporada = txtTemporada.getText().toString();
        String descripcion = txtDescripcion.getText().toString();
        Boolean principal = chkPrincipal.isChecked();
        String url;

        if (!TextUtils.isEmpty(nombre)||!TextUtils.isEmpty(personaje)) {
            if (!TextUtils.isEmpty(txtUri.getText()))
                url = txtUri.getText().toString();
            else
                url = " ";

            Personaje p = new Personaje(personaje,nombre, temporada, descripcion,url,principal);
            Coleccion.agregarPersonaje(p);
            finish();
        } else
            Toast.makeText(this, "El nombre del actor y el personaje deben estar rellenados", Toast.LENGTH_SHORT).show();
    }

}
