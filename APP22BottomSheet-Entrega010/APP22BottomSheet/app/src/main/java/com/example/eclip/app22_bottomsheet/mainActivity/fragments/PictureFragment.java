package com.example.eclip.app22_bottomsheet.mainActivity.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.eclip.app22_bottomsheet.pictureActivity.PictureActivity;
import com.example.eclip.app22_bottomsheet.R;
import com.example.eclip.app22_bottomsheet.bdd.model.User;
import com.example.eclip.app22_bottomsheet.utils.ImgUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class PictureFragment extends Fragment {

    @BindView(R.id.imgUser)
    ImageView imgUser; // User image
    @BindView(R.id.btnOpcion)
    Button btnOpcion; // Button that leads to the picture activity
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_picture, container, false);
        unbinder = ButterKnife.bind(this, view);
        ImgUtils.loadImg(getContext(), imgUser, User.getInstance().getImagen());
        return view;
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
    public static PictureFragment newInstance() {
        PictureFragment frg = new PictureFragment();
        return frg;
    }

    /**
     * Open the picture activity
     */
    @OnClick(R.id.btnOpcion)
    public void onViewClicked() {
        openActivity();
    }

    /**
     * Open the picture activity
     */
    private void openActivity() {
        startActivity(new Intent(getContext(), PictureActivity.class));
    }

}
