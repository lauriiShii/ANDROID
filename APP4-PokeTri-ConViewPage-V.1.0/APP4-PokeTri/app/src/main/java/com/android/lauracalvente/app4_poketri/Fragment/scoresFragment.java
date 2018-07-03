package com.android.lauracalvente.app4_poketri.Fragment;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import com.android.lauracalvente.app4_poketri.Adapters.MyAdapterListView;
import com.android.lauracalvente.app4_poketri.MainActivity;
import com.android.lauracalvente.app4_poketri.R;
import com.android.lauracalvente.app4_poketri.Utils.Coleccion;
import com.android.lauracalvente.app4_poketri.Utils.Constantes;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import butterknife.Unbinder;

public class scoresFragment extends Fragment {

    @BindView(R.id.lvScores)
    ListView lvScores;
    @BindView(R.id.frgScores)
    LinearLayout frgScores;
    @BindView(R.id.frgDetails)
    FrameLayout frgDetails;
    Unbinder unbinder;

    static int index;
    static View vDetalles;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scores, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        vDetalles = frgDetails;
        adaperElement();
    }

    public static scoresFragment newInstance(){
        scoresFragment fragmento = new scoresFragment();
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

    private void adaperElement() {
        // establecemos el adaptador en la lista
        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/pokemon.ttf");
        lvScores.setAdapter(new MyAdapterListView(getContext(), Coleccion.getPlayers(), custom_font));
    }

    @OnItemClick(R.id.lvScores)
    void onItemClick(int position) {

        if (!getVisibilityDetails() || index != position) {
            //Cargamos el fragmento con los datos del item pulsado
            cargardetailsFragment(Coleccion.players.get(position).name.toString(), Coleccion.players.get(position).score.toString(), Coleccion.players.get(position).time.toString());
            setVisibilityDetails(true);
        } else if (getVisibilityDetails() && index == position) {
            //cerramos el fragmento
            setVisibilityDetails(false);
        }

        //Almacenamos el indice del objeto sobre el que pulsamos
        index = position;
    }

    static public boolean getVisibilityDetails() {
        //false es GONE, true es VISIBLE
        if (vDetalles.getVisibility() == View.GONE)
            return false;
        else
            return true;
    }

    static public void setVisibilityDetails(Boolean see) {
        if (see)
            vDetalles.setVisibility(View.VISIBLE);
        else
            vDetalles.setVisibility(View.GONE);
    }

    static public void cargardetailsFragment(String name, String score, String time) {
        MainActivity.gestor.beginTransaction().replace(R.id.frgDetails, detailsFragment.newInstance(name, score, time), Constantes.TAG_FRAGMENT_SCORES).commit();
    }

}
