package com.example.laurishi.pruebarapida.actividades;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.laurishi.pruebarapida.R;
import com.example.laurishi.pruebarapida.adaptadores.ListaAdapter;
import com.example.laurishi.pruebarapida.base_de_datos.Dao;
import com.example.laurishi.pruebarapida.modelos.Coche;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ListaAdapter.OnItemClickListener {

    @BindView(R.id.lstCoches)
    RecyclerView lstCoches;
    private ListaAdapter listaAdapter;
    RecyclerView.ViewHolder holder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        opcionesGridView();

        // Drag & drop y Swipe to dismiss.
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                        ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView,
                                          RecyclerView.ViewHolder viewHolder,
                                          RecyclerView.ViewHolder target) {
                        final int fromPos = viewHolder.getAdapterPosition();
                        final int toPos = target.getAdapterPosition();
                        listaAdapter.onItemMove(fromPos, toPos);
                        return true;
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                        holder = viewHolder;

                        listaAdapter.remove(listaAdapter.datos.get(holder.getAdapterPosition()));
                    }
                });
        itemTouchHelper.attachToRecyclerView(lstCoches);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflador = getMenuInflater();
        inflador.inflate(R.menu.activity_main_items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnuAdd:
                abrirNuevo();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        opcionesGridView();

    }

    @Override
    public void onItemClick(View view, Coche coche, int position) {
        Intent intent = new Intent(this, NuevoCocheActivity.class);

        intent.putExtra(NuevoCocheActivity.EXTRA_COCHE, coche);
        startActivity(intent);
    }

    public void opcionesGridView (){
        lstCoches.setHasFixedSize(true);
        listaAdapter = new ListaAdapter(Dao.getInstance(this).getAllItems());
        listaAdapter.setOnItemClickListener(this);
        lstCoches.setAdapter(listaAdapter);
        lstCoches.setLayoutManager(new GridLayoutManager(this, 2));
        lstCoches.setItemAnimator(new DefaultItemAnimator());
    }

    private void abrirNuevo() {
        Coche coche = new Coche("","","dd-MM-yyyy");
        Intent intent = new Intent(this, NuevoCocheActivity.class);
        intent.putExtra(NuevoCocheActivity.EXTRA_COCHE, coche);
        startActivity(intent);
    }
}


