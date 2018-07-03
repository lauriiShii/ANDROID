package com.example.eclip.app22_bottomsheet.mainActivity.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.eclip.app22_bottomsheet.dataActivity.DataActivity;
import com.example.eclip.app22_bottomsheet.R;
import com.example.eclip.app22_bottomsheet.bdd.model.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class DataFragment extends Fragment {


    @BindView(R.id.btnOpcion)
    Button btnOpcion; // Button that opens the activity "Data Activity"
    @BindView(R.id.lblName)
    TextView lblName; // User name
    @BindView(R.id.lblPhone)
    TextView lblPhone; // User phone
    Unbinder unbinder;

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

    /**
     * Show the user information.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data, container, false);
        unbinder = ButterKnife.bind(this, view);
        lblName.setText(User.getInstance().getName());
        lblPhone.setText(User.getInstance().getPhone());
        return view;
    }


    /**
     * Open the activity "Data Activity".
     */
    private void openActivity() {
        startActivity(new Intent(getContext(), DataActivity.class));
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    /**
     * Generate an instance of this fragment.
     * @return
     */
    public static DataFragment newInstance() {
        DataFragment frg = new DataFragment();
        return frg;
    }

    /**
     * Open the activity.
     */
    @OnClick(R.id.btnOpcion)
    public void onViewClicked() {
        openActivity();
    }
}