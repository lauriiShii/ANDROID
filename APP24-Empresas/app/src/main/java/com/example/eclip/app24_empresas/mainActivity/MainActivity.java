package com.example.eclip.app24_empresas.mainActivity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.eclip.app24_empresas.R;
import com.example.eclip.app24_empresas.bdd.local.BD;
import com.example.eclip.app24_empresas.bdd.local.CompanyDAO;
import com.example.eclip.app24_empresas.bdd.local.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private android.support.v7.widget.RecyclerView lstBusiness;
    private AdaptadorBusiness mAdapter;
    private LinearLayoutManager mLayoutManager;
    private CompanyDAO companyDAO;
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        companyDAO = BD.getInstance(this).getDb().companyDAO();
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        db = DbHelper.getInstance(this).getWritableDatabase();

        lstBusiness = findViewById(R.id.lstBusiness);
        setupRecyclerView();

        viewModel.getBusiness().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> strings) {
                mAdapter = new AdaptadorBusiness((ArrayList<String>) strings);
                lstBusiness.setAdapter(mAdapter);
            }
        });
    }

    private void setupRecyclerView() {
        lstBusiness.setHasFixedSize(true);
        mAdapter = new AdaptadorBusiness((ArrayList<String>) viewModel.getBusiness().getValue());
        lstBusiness.setAdapter(mAdapter);
        mLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        lstBusiness.setLayoutManager(mLayoutManager);
        lstBusiness.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.mmuClear) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
