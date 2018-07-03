package com.android.lauracalvente.app4_poketri.Fragment;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.lauracalvente.app4_poketri.R;
import com.android.lauracalvente.app4_poketri.Utils.Coleccion;
import com.android.lauracalvente.app4_poketri.Utils.Constantes;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class detailsFragment extends Fragment {

    @BindView(R.id.imgPokemon)
    ImageView imgPokemon;
    @BindView(R.id.lblDName)
    TextView lblDName;
    @BindView(R.id.lblDScore)
    TextView lblDScore;
    @BindView(R.id.lblDTime)
    TextView lblDTime;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle argument = getArguments();
        String name = argument.getString(Constantes.KEY_NAME);
        String time = argument.getString(Constantes.KEY_TIME);
        String score = argument.getString(Constantes.KEY_SCORES);

        lblDScore.setText(score);
        lblDTime.setText(time);
        lblDName.setText(name);

        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/pokemon.ttf");

        lblDName.setTypeface(custom_font);
        lblDTime.setTypeface(custom_font);
        lblDScore.setTypeface(custom_font);

        asignarImg();
    }

    public static detailsFragment newInstance(String name, String score, String time) {
        detailsFragment fragmento = new detailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constantes.KEY_NAME, name);
        bundle.putString(Constantes.KEY_SCORES, score);
        bundle.putString(Constantes.KEY_TIME, time);
        fragmento.setArguments(bundle);
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

    public void asignarImg(){
        String url = Coleccion.dameImg();

        if (url.equals(""))
            Picasso.with(getContext()).load(R.drawable.pokeball).into(imgPokemon);
        else
            Picasso.with(getContext()).load(url).into(imgPokemon);
    }
}
