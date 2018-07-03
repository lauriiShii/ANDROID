package es.android.lauracalvente.examenprimertrimestre;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    FrameLayout frgContenido;
    FragmentManager gestor;
    PersonalAdapterLista adaptador;
    GridView lsPersonaje;
    static RelativeLayout raiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        views();
    }

    private void views() {

        //Raiz
        raiz = (RelativeLayout) findViewById(R.id.raiz);

        //Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Fragmento y su gestor
        frgContenido = (FrameLayout) findViewById(R.id.frgContenedor);
        gestor = getSupportFragmentManager();
        cargarFragmento("Personaje"," ");

        //Adaptador del GridView y GridView adaptado
        adaptador = new PersonalAdapterLista(this, Coleccion.getpersonajes());
        lsPersonaje = (GridView) findViewById(R.id.lstPersonajes);
        lsPersonaje.setAdapter(adaptador);

        lsPersonaje.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cargarFragmento(Coleccion.getPersonajeAtIndex(position).getNombrePersonaje(), Coleccion.getPersonajeAtIndex(position).getDescripcion());
            }
        });
    }

    // Cuando se crea el menú de opciones.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Se inflo el menú a partir de la especificación XML.
        getMenuInflater().inflate(R.menu.menu, menu);
        // Se da la posibilidad al sistema de agregar ítems al menú.
        return super.onCreateOptionsMenu(menu);
    }

    // Cuando se pulsa un elemento del menú.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Dependiendo del item pulsado realizo la acción deseada.
        switch (item.getItemId()) {
            case R.id.menuAgregar:
                irAgregar();
                break;
            case R.id.botonAgregar:
                irAgregar();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    public  void irAgregar (){
        Intent llamadaPuntuaciones = new Intent(this, NuevoPersonaje.class);
        startActivity(llamadaPuntuaciones);
    }

    private void cargarFragmento(String nombre, String descripcion){
        gestor.beginTransaction().replace(R.id.frgContenedor,FragmentoDescripcion.newInstance(nombre, descripcion)).commit();
    }
}
