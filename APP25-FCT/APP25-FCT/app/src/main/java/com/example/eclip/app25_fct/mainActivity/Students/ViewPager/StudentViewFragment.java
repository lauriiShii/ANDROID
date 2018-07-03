package com.example.eclip.app25_fct.mainActivity.Students.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eclip.app25_fct.R;
import com.example.eclip.app25_fct.bdd.model.Student;
import com.example.eclip.app25_fct.databinding.FragmentStudentViewBinding;

import static com.example.eclip.app25_fct.utils.Constantes.ARG_STUDENT;

/**
 * Created by eclip on 01/03/2018.
 */

public class StudentViewFragment extends Fragment {

    private FragmentStudentViewBinding mBinding;
    private AdapterViewPager adapter;
    private TabLayout mTab;
    private ViewPager mPager;
    private Student mStudent;
    /***
     * Method that is triggered after the activity is created
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    /***
     * Method that instances the fragment
     * @return
     */
    public static StudentViewFragment newInstance() {
        StudentViewFragment frg = new StudentViewFragment();
        return frg;
    }

    public static StudentViewFragment newInstance(Student student) {
        StudentViewFragment frg = new StudentViewFragment();
        Bundle argumentos = new Bundle();
        argumentos.putParcelable(ARG_STUDENT, student);
        frg.setArguments(argumentos);
        return frg;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setRetainInstance(true);
        super.onCreate(savedInstanceState);
    }

    /***
     * Databinding configuration
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentStudentViewBinding.inflate(inflater, container, false);
        getData();
        setupViewPager();
        return mBinding.getRoot();
    }

    private void getData() {
        if(getArguments() != null) {
            mStudent = getArguments().getParcelable(ARG_STUDENT);
        }
        else {
            mStudent = new Student();
        }
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /***
     * Setups the view model
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void setupViewPager() {
        mBinding.viewPager.setAdapter(new AdapterViewPager(getActivity().getSupportFragmentManager(), mStudent));
        mBinding.tabs.setupWithViewPager(mBinding.viewPager);
    }
}
