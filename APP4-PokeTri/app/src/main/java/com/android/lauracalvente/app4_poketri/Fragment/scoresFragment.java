package com.android.lauracalvente.app4_poketri.Fragment;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
//import com.android.lauracalvente.app4_poketri.Adapters.MyAdapterListView;
import com.android.lauracalvente.app4_poketri.Adapters.MyAdapterListView;
import com.android.lauracalvente.app4_poketri.MainActivity;
import com.android.lauracalvente.app4_poketri.R;
import com.android.lauracalvente.app4_poketri.Utils.Coleccion;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import butterknife.Unbinder;

public class scoresFragment extends Fragment {

    @BindView(R.id.lvScores)
    ListView lvScores;
    @BindView(R.id.frgScores)
    RelativeLayout frgScores;
    Unbinder unbinder;

    static int index;

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

        if (!MainActivity.getVisibilityDetails() || index != position) {
            //Cargamos el fragmento con los datos del item pulsado
            MainActivity.cargardetailsFragment(Coleccion.players.get(position).name.toString(), Coleccion.players.get(position).score.toString(), Coleccion.players.get(position).time.toString());
            MainActivity.setVisibilityDetails(true);
        } else if (MainActivity.getVisibilityDetails() && index == position) {
            //cerramos el fragmento
            MainActivity.setVisibilityDetails(false);
        }

        //Almacenamos el indice del objeto sobre el que pulsamos
        index = position;
    }
}
