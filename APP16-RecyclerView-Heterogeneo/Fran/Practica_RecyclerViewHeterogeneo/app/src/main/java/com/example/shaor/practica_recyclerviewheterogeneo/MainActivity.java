package com.example.shaor.practica_recyclerviewheterogeneo;

import android.app.LauncherActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rcvList;
    private MainActivityAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        rcvList = (RecyclerView) findViewById(R.id.rcvList);
        mAdapter = new MainActivityAdapter();
        ArrayList<ListElement> datos = new ArrayList<>();
        datos.add(new Classroom("1ºCFGS"));
        datos.add(new Student("Pepe",54));
        datos.add(new Student("Alejandro",23));
        datos.add(new Classroom("1ºCFGS"));
        datos.add(new Student("Fran",23));
        datos.add(new Student("Pipo",10));
        datos.add(new Student("Juakin",999));
        mAdapter.setData(datos);
        rcvList.setHasFixedSize(true);
        rcvList.setAdapter(mAdapter);
        rcvList.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rcvList.setItemAnimator(new DefaultItemAnimator());
    }
}
