package com.example.eclip.periodico.mainActivity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.eclip.periodico.R;
import com.example.eclip.periodico.bdd.local.BD;
import com.example.eclip.periodico.bdd.model.Periodico;
import com.example.eclip.periodico.bdd.repository.RepositoryPeriodico;
import com.example.eclip.periodico.bdd.repository.RepositoryPeriodicoImpl;
import com.example.eclip.periodico.databinding.ActivityMainBinding;
import com.example.eclip.periodico.detailsActivity.DetailsActivity;
import com.example.eclip.periodico.preferenceActivity.PreferenceSettingActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;
    private RepositoryPeriodico mRepository;
    private MainActivityViewModel mViewModel;
    private PeriodicoAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.setActivity(this);
        setupViewModel();
        setupItemTouchHelper();
    }

    // Abre la actividad de detalles donde se puede crear un nuevo periodico
    public void fabOnClick(View view){
        startActivity(new Intent(this, DetailsActivity.class));
    }

    // Configuracion del viewModel junto a la obtención de los datos por parte de un observador, lo que hace que
    // la lista siempre este actualizada
    private void setupViewModel() {

        mRepository = RepositoryPeriodicoImpl.getInstance(BD.getInstance(this).getDb());
        mViewModel = ViewModelProviders.of(this, new MainActivityViewModelFactory(mRepository)).get(
                MainActivityViewModel.class);

        mViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        setupRecyclerView(mViewModel);
        mViewModel.getPeriodicos().observe(this, new Observer<List<Periodico>>() {
            @Override
            public void onChanged(@Nullable List<Periodico> periodicos) {
                mAdapter = new PeriodicoAdapter((ArrayList<Periodico>) periodicos, MainActivity.this);
                mBinding.lstPeriodicos.setAdapter(mAdapter);
                lstIsEmpty((ArrayList<Periodico>) periodicos);
            }
        });
    }

    // Configuración del RecycleView junto a su separador
    private void setupRecyclerView(MainActivityViewModel viewModel){
        mBinding.lstPeriodicos.setHasFixedSize(true);
        mAdapter = new PeriodicoAdapter((ArrayList<Periodico>) viewModel.getPeriodicos().getValue(), this);
        mBinding.lstPeriodicos.setAdapter(mAdapter);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mBinding.lstPeriodicos.setLayoutManager(mLayoutManager);
        mBinding.lstPeriodicos.setItemAnimator(new DefaultItemAnimator());
        mBinding.lstPeriodicos.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
    }

    // Configuración del EmptyView del recycleView
    private void lstIsEmpty(ArrayList<Periodico> periodicos) {
        if (periodicos.isEmpty()) {
            mBinding.lstPeriodicos.setVisibility(View.GONE);
            mBinding.lblEmptyView.setVisibility(View.VISIBLE);
        } else {
            mBinding.lstPeriodicos.setVisibility(View.VISIBLE);
            mBinding.lblEmptyView.setVisibility(View.GONE);
        }
    }

    // Configuración del dismiss para borrar, este se implementa en el adaptador
    private void setupItemTouchHelper(){
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(
                        ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                        ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView,
                                          RecyclerView.ViewHolder viewHolder,
                                          RecyclerView.ViewHolder target) {
                        final int fromPos = viewHolder.getAdapterPosition();
                        final int toPos = target.getAdapterPosition();
                        mAdapter.swapItems(fromPos, toPos);
                        return true;
                    }
                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                        mAdapter.removeItem(viewHolder.getAdapterPosition(), mBinding.lblEmptyView);
                    }
                });
        itemTouchHelper.attachToRecyclerView(mBinding.lstPeriodicos);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // Abre la actividad de preferencias
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, PreferenceSettingActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
