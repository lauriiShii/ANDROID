package com.example.eclip.app22_bottomsheet.MainActivity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.eclip.app22_bottomsheet.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.example.eclip.app22_bottomsheet.utils.Constants.ARG_TEXT;

public class GenericFragment extends Fragment {

    @BindView(R.id.btnAction)
    Button btnAction;
    Unbinder unbinder;

    private Callback mListener; // listener who listens to the callback call from the activity
    private String text;

    public interface Callback {
        void open(String text);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setRetainInstance(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_generic, container, false);
        unbinder = ButterKnife.bind(this, view);
        text = getArguments().getString(ARG_TEXT);
        btnAction.setText(text);
        return view;
    }

    @OnClick(R.id.btnAction)
    public void onViewClicked() {
        openActivity();
    }

    private void openActivity() {
        mListener.open(text);
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        try {
            mListener = (Callback) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + getString(R.string.no_CallBack));
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public static GenericFragment newInstance(String text) {
        GenericFragment frg = new GenericFragment();
        Bundle argumentos = new Bundle();
        argumentos.putString(ARG_TEXT, text);
        frg.setArguments(argumentos);
        return frg;
    }
}
