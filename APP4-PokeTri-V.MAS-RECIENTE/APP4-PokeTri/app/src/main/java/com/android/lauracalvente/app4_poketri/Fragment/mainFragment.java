package com.android.lauracalvente.app4_poketri.Fragment;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.lauracalvente.app4_poketri.MainActivity;
import com.android.lauracalvente.app4_poketri.R;
import com.android.lauracalvente.app4_poketri.Utils.Constantes;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import butterknife.Unbinder;

public class mainFragment extends Fragment {
    @BindView(R.id.txtName)
    EditText txtName;
    @BindView(R.id.lblError)
    TextView lblError;
    @BindView(R.id.lblName)
    TextView lblName;
    @BindView(R.id.btnGame)
    Button btnGame;
    @BindView(R.id.btnScore)
    Button btnScore;
    @BindView(R.id.imgLogo)
    ImageView imgLogo;
    @BindView(R.id.lytMain)
    RelativeLayout lytMain;

    Unbinder unbinder;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/pokemon.ttf");
        btnGame.setTypeface(custom_font);
        btnScore.setTypeface(custom_font);
        lblName.setTypeface(custom_font);
        lblError.setTypeface(custom_font);

        restoreInstanceState(savedInstanceState);
        //Si habia algo escrito en name lo vuelve a mostrar
        if (MainActivity.namePlayer.equals("")) {
            txtName.setText(String.valueOf(MainActivity.namePlayer));
        }
    }

    //Si hay algo escrito en name lo almacena
    private void restoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            txtName.setText(savedInstanceState.getString(Constantes.STATE_LIKES));
        }
    }

    public static mainFragment newInstance() {
        mainFragment fragmento = new mainFragment();
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

    @OnClick({R.id.btnGame, R.id.btnScore})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnGame:
                MainActivity.cargarGameFragment();
                break;
            case R.id.btnScore:
                MainActivity.cargarSxoreFragment();
                break;
        }
    }

    @OnTextChanged(R.id.txtName)
    public void onTextChanged (){
        if(TextUtils.isEmpty(txtName.getText().toString())) {
            btnGame.setEnabled(false);
            lblError.setVisibility(View.VISIBLE);
        }
        else {
            btnGame.setEnabled(true);
            lblError.setVisibility(View.INVISIBLE);
        }
        //Almacenamos el nombre con el fin de que al cambiar la vista se pueda comprobar si
        //esta vacio o lleno
        MainActivity.namePlayer = txtName.getText().toString();
    }
}