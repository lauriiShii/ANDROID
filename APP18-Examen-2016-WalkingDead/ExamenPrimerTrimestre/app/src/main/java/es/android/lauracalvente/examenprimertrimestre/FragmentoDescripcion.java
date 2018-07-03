package es.android.lauracalvente.examenprimertrimestre;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Laura on 09/02/2017.
 */

public class FragmentoDescripcion extends Fragment {

    private static final String KEY_PERSONAJE = "personaje";
    private static final String KEY_DESCRIPCION = "descripcion";

    TextView lblPersonaje, lblDescripcion;
    ImageButton boton;
    static boolean desplegado = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmento_descripcion, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        lblPersonaje = (TextView) getView().findViewById(R.id.lblNomPersonaje);
        lblDescripcion = (TextView) getView().findViewById(R.id.lblDescripcion);
        boton = (ImageButton) getView().findViewById(R.id.btnDesplegar);

        Bundle argumentos = getArguments();
        String nombre = argumentos.getString(KEY_PERSONAJE);
        String descripcion = argumentos.getString(KEY_DESCRIPCION);

        lblDescripcion.setText(descripcion);
        lblPersonaje.setText(nombre);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (desplegado) {
                    lblDescripcion.setVisibility(View.VISIBLE);
                    desplegado = false;
                }
                else {
                    lblDescripcion.setVisibility(View.GONE);
                    if(lblDescripcion.getText() != " ")
                        desplegado = true;
                }
            }
        });

    }


    public static FragmentoDescripcion newInstance(String nombre, String puntuacion){
        FragmentoDescripcion fragmento = new FragmentoDescripcion();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_PERSONAJE, nombre);
        bundle.putString(KEY_DESCRIPCION, puntuacion);
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
}
