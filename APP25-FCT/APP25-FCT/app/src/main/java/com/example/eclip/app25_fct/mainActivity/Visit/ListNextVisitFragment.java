package com.example.eclip.app25_fct.mainActivity.Visit;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.eclip.app25_fct.bdd.repositories.RepositoryCompany;
import com.example.eclip.app25_fct.bdd.repositories.RepositoryCompanyImpl;
import com.example.eclip.app25_fct.bdd.repositories.RepositoryStudent;
import com.example.eclip.app25_fct.bdd.repositories.RepositoryStudentImpl;
import com.example.eclip.app25_fct.bdd.repositories.RepositoryTeacher;
import com.example.eclip.app25_fct.bdd.repositories.RepositoryTeacherImpl;
import com.example.eclip.app25_fct.bdd.repositories.RepositoryVisit;
import com.example.eclip.app25_fct.bdd.repositories.RepositoryVisitImpl;
import com.example.eclip.app25_fct.bdd.local.BD;
import com.example.eclip.app25_fct.bdd.model.Visit;
import com.example.eclip.app25_fct.databinding.FragmentListNextVisitsBinding;
import com.example.eclip.app25_fct.mainActivity.MainActivityViewModel;
import com.example.eclip.app25_fct.mainActivity.MainActivityViewModelFactory;

import java.util.ArrayList;
import java.util.List;

public class ListNextVisitFragment extends Fragment {

    // RecyclerView's adapter
    private AdaptadorVisit mAdapter;
    // RecyclerView's manager
    private LinearLayoutManager mLayoutManager;
    //MainActivity's viewModel. It's the data base manager
    private static MainActivityViewModel mViewModel;
    // Bind between the class and its layout
    private FragmentListNextVisitsBinding mBinding;
    // It contains all the methods than manages the access to the database
    private RepositoryCompany mRepositoryBusiness;
    private RepositoryStudent mRepositoryStudent;
    private RepositoryTeacher mRepositoryTeacher;
    private RepositoryVisit mRepositoryVisit;

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
    public static ListNextVisitFragment newInstance() {
        ListNextVisitFragment frg = new ListNextVisitFragment();
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
        mBinding = FragmentListNextVisitsBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
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
        setupViewModel();
    }

    /***
     * Setups the view model. It's responsable of the emptyview's setup.
     */
    private void setupViewModel() {

        mRepositoryBusiness = RepositoryCompanyImpl.getInstance(BD.getInstance(getActivity()).getDb());
        mRepositoryTeacher = RepositoryTeacherImpl.getInstance(BD.getInstance(getActivity()).getDb());
        mRepositoryVisit = RepositoryVisitImpl.getInstance(BD.getInstance(getActivity()).getDb());
        mRepositoryStudent = RepositoryStudentImpl.getInstance(BD.getInstance(getActivity()).getDb());
        mViewModel = ViewModelProviders.of(getActivity(), new MainActivityViewModelFactory(mRepositoryBusiness, mRepositoryStudent, mRepositoryTeacher, mRepositoryVisit)).get(
                MainActivityViewModel.class);

        mViewModel = ViewModelProviders.of(getActivity()).get(MainActivityViewModel.class);
        setupRecyclerView(mViewModel);
        mViewModel.getVisit().observe(getActivity(), new Observer<List<Visit>>() {
            @Override
            public void onChanged(@Nullable List<Visit> visits) {
                mAdapter = new AdaptadorVisit((ArrayList<Visit>) mRepositoryVisit.loadAllVisitNotOK(), getContext());
                mBinding.lstVisit.setAdapter(mAdapter);
                lstIsEmpty((ArrayList<Visit>) mRepositoryVisit.loadAllVisitNotOK());
            }
        });
    }

    /***
     * Setups the recycler view
     * @param viewModel
     */
    private void setupRecyclerView(MainActivityViewModel viewModel) {
        mBinding.lstVisit.setHasFixedSize(true);
        mAdapter = new AdaptadorVisit((ArrayList<Visit>) mRepositoryVisit.loadAllVisitNotOK(), getContext());
        mBinding.lstVisit.setAdapter(mAdapter);
        mLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        mBinding.lstVisit.setLayoutManager(mLayoutManager);
        mBinding.lstVisit.setItemAnimator(new DefaultItemAnimator());
        mBinding.lstVisit.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
    }

    /***
     * Checks if the list of business is empty, then shows the emptyview, else, hides the emptyview and shows the list
     * @param business
     */
    private void lstIsEmpty(ArrayList<Visit> business) {
        if (business.isEmpty()) {
            mBinding.lstVisit.setVisibility(View.GONE);
            mBinding.lblEmptyView.setVisibility(View.VISIBLE);
        } else {
            mBinding.lstVisit.setVisibility(View.VISIBLE);
            mBinding.lblEmptyView.setVisibility(View.GONE);
        }
    }
}