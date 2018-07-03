package com.example.eclip.examen_11_12_2017.main;

import android.arch.lifecycle.ViewModelProviders;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.eclip.examen_11_12_2017.R;
import com.example.eclip.examen_11_12_2017.Utils.ListViewUtils;
import com.example.eclip.examen_11_12_2017.boot.BootActivity;
import com.example.eclip.examen_11_12_2017.boot.bdd.BDDRespuestas;
import com.example.eclip.examen_11_12_2017.main.adaptadores.ListMensajesAdapter;
import com.example.eclip.examen_11_12_2017.main.bdd.BDDMensajes;
import com.example.eclip.examen_11_12_2017.main.bdd.RepositoryImplMensajes;
import com.example.eclip.examen_11_12_2017.main.bdd.RepositoryMensajes;
import com.example.eclip.examen_11_12_2017.main.model.Mensaje;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.lstMensajes)
    ListView lstMensajes;
    @BindView(R.id.txtMensaje)
    EditText txtMensaje;

    private ListMensajesAdapter mAdapter;
    private RepositoryMensajes repository;
    private static MainActivityViewModel mViewModel;
    private ClipboardManager clipboard;
    private ClipData clip;
    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        createViewModel();
        inflateMensajes();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> add());

        clipboard = (ClipboardManager)
                getSystemService(this.CLIPBOARD_SERVICE);
    }

    private void createViewModel() {
        repository = RepositoryImplMensajes.getInstance(BDDMensajes.getInstance());
        mViewModel = ViewModelProviders.of(this, new MainActivityViewModelFactory(repository)).get(
                MainActivityViewModel.class);
    }

    private void inflateMensajes() {
        mAdapter = new ListMensajesAdapter(this, mViewModel.getData());
        lstMensajes.setAdapter(mAdapter);
        lstMensajes.setChoiceMode(lstMensajes.CHOICE_MODE_MULTIPLE_MODAL);
        lstMensajes.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode actionMode, int i, long l, boolean b) {
                actionMode.setTitle(getString(R.string.oneofother, lstMensajes.getCheckedItemCount(), lstMensajes.getCount()));
            }

            @Override
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                actionMode.getMenuInflater().inflate(R.menu.contextual_mzin_activity, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.mnu_Delete) {
                    deleteSelected(ListViewUtils.getSelectedItems(lstMensajes, false));
                    return true;
                }
                if (menuItem.getItemId() == R.id.mnu_favorito) {
                    favoritoSelected(ListViewUtils.getSelectedItems(lstMensajes, false));
                    return true;
                }
                if (menuItem.getItemId() == R.id.mnu_Copy) {
                    copySelected(ListViewUtils.getSelectedItems(lstMensajes, false));
                    return true;
                }
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode actionMode) {
            }
        });
    }

    private void add() {
        if (!txtMensaje.getText().toString().equals("")) {
            repository.getMensajes().add(new Mensaje(txtMensaje.getText().toString(), 'M', false));
            mAdapter.setData(mViewModel.getData());
            lstMensajes.smoothScrollToPosition(mAdapter.getCount() - 1);
            txtMensaje.setText("");
            final Runnable r = new Runnable() {
                public void run() {
                    dameRespuesta();
                }
            };
            handler.postDelayed(r, 1000);
        }
    }

    private void dameRespuesta() {

        Random r = new Random();
        int valor = r.nextInt(BDDRespuestas.getRespuestas().size());
        repository.getMensajes().add(new Mensaje(BDDRespuestas.getRespuestas().get(valor), 'B', false));
        mAdapter.setData(mViewModel.getData());
        lstMensajes.smoothScrollToPosition(mAdapter.getCount() - 1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.poup_main_activity_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.mmu_clear) {
            clear();
            return true;
        }
        if (item.getItemId() == R.id.mmu_bot) {
            openBot();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void clear() {
        repository.getMensajes().clear();
        mAdapter.setData(mViewModel.getData());
    }

    private void openBot() {
        startActivity(new Intent(this, BootActivity.class));
    }

    private void deleteSelected(List<Object> selectedItems) {
        for (Object mensaje : selectedItems) {
            lstMensajes.setItemChecked(mViewModel.indexMensaje((Mensaje) mensaje), false);
        }
        for (Object mensaje : selectedItems) {
            mViewModel.deleteMensaje((Mensaje) mensaje);
        }
        mAdapter.setData(mViewModel.getData());

    }

    private void favoritoSelected(List<Object> selectedItems) {
        for (Object mensaje : selectedItems) {
            lstMensajes.setItemChecked(mViewModel.indexMensaje((Mensaje) mensaje), false);
        }
        for (Object mensaje : selectedItems) {
            Mensaje mensajeActual = (Mensaje) mensaje;
            if (mensajeActual.isFavorito())
                mensajeActual.setFavorito(false);
            else
                mensajeActual.setFavorito(true);
            int posicion = repository.getMensajes().indexOf(mensaje);
            repository.getMensajes().remove(posicion);
            repository.getMensajes().add(posicion, mensajeActual);
        }
        mAdapter.setData(mViewModel.getData());

    }

    private void copySelected(List<Object> selectedItems) {
        String texto = "";
        for (Object mensaje : selectedItems) {
            lstMensajes.setItemChecked(mViewModel.indexMensaje((Mensaje) mensaje), false);
        }
        for (Object mensaje : selectedItems) {
            if (((Mensaje) mensaje).getPropietario() == 'B')
                texto += "Bot: " + ((Mensaje) mensaje).getMensaje() + "\n";
            else
                texto += "Usuario: " + ((Mensaje) mensaje).getMensaje() + "\n";
        }
        clip = ClipData.newPlainText("Mensajes copiados", texto);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(this, "Mensajes copiados", Toast.LENGTH_SHORT).show();

    }
}
