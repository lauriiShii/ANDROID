package com.android.lauracalvente.app4_poketri.Fragment;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.lauracalvente.app4_poketri.MainActivity;
import com.android.lauracalvente.app4_poketri.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class overFragment extends Fragment {

    Unbinder unbinder;
    @BindView(R.id.btnGameOver)
    Button btnGameOver;
    @BindView(R.id.lblPlayerScore)
    TextView lblPlayerScore;

    static String scorePlayer = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_over, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/pokemon.ttf");
        btnGameOver.setTypeface(custom_font);
        lblPlayerScore.setTypeface(custom_font);
        lblPlayerScore.setText(scorePlayer);
    }

    public static overFragment newInstance(String score) {
        overFragment fragmento = new overFragment();
        scorePlayer = score;
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

    @OnClick(R.id.btnGameOver)
    public void onViewClicked() {
        //Volver a mainFragment y se llama a play nuevamente
        MainActivity.playGame = false;
        MainActivity.cargarMainFragment();
        gameFragment.cargarPlayFragment();
    }
}
