package com.android.lauracalvente.app4_poketri.Fragment;

import android.app.Activity;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.android.lauracalvente.app4_poketri.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class playFragment extends Fragment {

    Unbinder unbinder;
    @BindView(R.id.btnPlay)
    Button btnPlay;
    @BindView(R.id.imgPikachu)
    ImageView imgPikachu;
    AnimationDrawable animacion;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_play, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/pokemon.ttf");
        btnPlay.setTypeface(custom_font);

        imgPikachu.setBackgroundResource(R.drawable.pikachu);
        animacion = (AnimationDrawable) imgPikachu.getBackground();
        animacion.start();
    }

    public static playFragment newInstance() {
        playFragment fragmento = new playFragment();
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

    @OnClick(R.id.btnPlay)
    public void onViewClicked() {
        //Muestra questFragment
        gameFragment.cargarQuestFragment();
    }
}
