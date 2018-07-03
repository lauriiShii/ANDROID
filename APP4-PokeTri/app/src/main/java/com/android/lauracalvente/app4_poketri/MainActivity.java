package com.android.lauracalvente.app4_poketri;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.android.lauracalvente.app4_poketri.Fragment.detailsFragment;
import com.android.lauracalvente.app4_poketri.Fragment.gameFragment;
import com.android.lauracalvente.app4_poketri.Fragment.mainFragment;
import com.android.lauracalvente.app4_poketri.Fragment.scoresFragment;
import com.android.lauracalvente.app4_poketri.Utils.Constantes;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.mainFrame)
    FrameLayout mainFrame;
    @BindView(R.id.frgDetails)
    FrameLayout frgDetails;

    static View v;
    static public String namePlayer;
    static FragmentManager gestor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        gestor = getSupportFragmentManager();
        cargarMainFragment();
        v = frgDetails;
    }


    static public void cargarMainFragment() {
        gestor.beginTransaction().replace(R.id.mainFrame, mainFragment.newInstance(), Constantes.TAG_FRAGMENT_MAIN).commit();
    }

    static public void cargarGameFragment() {
        gestor.beginTransaction().replace(R.id.mainFrame, gameFragment.newInstance(), Constantes.TAG_FRAGMENT_GAME).commit();
    }

    static public void cargarscoreFragment() {
        gestor.beginTransaction().replace(R.id.mainFrame, scoresFragment.newInstance(), Constantes.TAG_FRAGMENT_SCORES).commit();
    }

    static public void cargardetailsFragment(String name, String score, String time) {
        gestor.beginTransaction().replace(R.id.frgDetails, detailsFragment.newInstance(name, score, time), Constantes.TAG_FRAGMENT_SCORES).commit();
    }

    static public boolean getVisibilityDetails() {
        //false es GONE, true es VISIBLE
        if (v.getVisibility() == View.GONE)
            return false;
        else
            return true;
    }

    static public void setVisibilityDetails(Boolean see) {
        if (see)
            v.setVisibility(View.VISIBLE);
        else
            v.setVisibility(View.GONE);
    }

    //Para poder volver al fragmento principal desde el juego y las puntuaciones.
    //Hay que poner los tag cuando se cargan los otros fragmentos
    @Override
    public void onBackPressed() {
        if (gestor.findFragmentByTag(Constantes.TAG_FRAGMENT_MAIN) != null)
            super.onBackPressed();
        else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            cargarMainFragment();
            frgDetails.setVisibility(View.GONE);
        }
    }

}
