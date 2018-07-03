package com.android.lauracalvente.app4_poketri.Fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.android.lauracalvente.app4_poketri.MainActivity;
import com.android.lauracalvente.app4_poketri.R;
import com.android.lauracalvente.app4_poketri.Utils.Constantes;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class gameFragment extends Fragment {


    Unbinder unbinder;
    @BindView(R.id.frgGameHueco)
    FrameLayout frgGameHueco;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        cargarPlayFragment();
    }

    public static gameFragment newInstance() {
        gameFragment fragmento = new gameFragment();
        return fragmento;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    static public void cargarPlayFragment() {
        MainActivity.gestor.beginTransaction().replace(R.id.frgGameHueco, playFragment.newInstance(), Constantes.TAG_FRAGMENT_SCORES).commit();
    }

    static public void cargarQuestFragment() {
        MainActivity.playGame = true;
        MainActivity.gestor.beginTransaction().replace(R.id.frgGameHueco, questFragment.newInstance(), Constantes.TAG_FRAGMENT_SCORES).commit();
    }

    static public void cargarOverFragment(String score) {
        MainActivity.gestor.beginTransaction().replace(R.id.frgGameHueco, overFragment.newInstance(score), Constantes.TAG_FRAGMENT_SCORES).commit();
    }
}
