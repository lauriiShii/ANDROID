package com.example.eclip.app25_fct.mainActivity.Students;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
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
import com.example.eclip.app25_fct.bdd.model.Student;
import com.example.eclip.app25_fct.databinding.FragmentListStudentsBinding;
import com.example.eclip.app25_fct.mainActivity.MainActivityViewModel;
import com.example.eclip.app25_fct.mainActivity.MainActivityViewModelFactory;

import java.util.ArrayList;
import java.util.List;

public class ListStudentsFragment extends Fragment {

    // RecyclerView's adapter
    private AdaptadorStudent mAdapter;
    // RecyclerView's manager
    private LinearLayoutManager mLayoutManager;
    //MainActivity's viewModel. It's the data base manager
    private static MainActivityViewModel mViewModel;
    // Bind between the class and its layout
    private FragmentListStudentsBinding mBinding;
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
    public static ListStudentsFragment newInstance() {
        ListStudentsFragment frg = new ListStudentsFragment();
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
        mBinding = FragmentListStudentsBinding.inflate(inflater, container, false);
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

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupViewModel();
        configItemTouchHelper();
    }

    private void configItemTouchHelper(){
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(
                        ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                        ItemTouchHelper.RIGHT) {

                    // Cuando se detecta un gesto drag & drop.
                    @Override
                    public boolean onMove(RecyclerView recyclerView,
                                          RecyclerView.ViewHolder viewHolder,
                                          RecyclerView.ViewHolder target) {
                        final int fromPos = viewHolder.getAdapterPosition();
                        final int toPos = target.getAdapterPosition();
                        mAdapter.swapItems(fromPos, toPos);
                        return true;
                    }

                    // Cuando se detecta un gesto swipe to dismiss.
                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                        // Se elimina el elemento.
                        mAdapter.removeItem(viewHolder.getAdapterPosition(), mBinding.lblEmptyView);
                    }
                });
        // Se enlaza con el RecyclerView.
        itemTouchHelper.attachToRecyclerView(mBinding.lstStudents);
    }

    private void setupViewModel() {

        mRepositoryBusiness = RepositoryCompanyImpl.getInstance(BD.getInstance(getActivity()).getDb());
        mRepositoryTeacher = RepositoryTeacherImpl.getInstance(BD.getInstance(getActivity()).getDb());
        mRepositoryVisit = RepositoryVisitImpl.getInstance(BD.getInstance(getActivity()).getDb());
        mRepositoryStudent = RepositoryStudentImpl.getInstance(BD.getInstance(getActivity()).getDb());
        mViewModel = ViewModelProviders.of(getActivity(), new MainActivityViewModelFactory(mRepositoryBusiness, mRepositoryStudent, mRepositoryTeacher, mRepositoryVisit)).get(
                MainActivityViewModel.class);

        mViewModel = ViewModelProviders.of(getActivity()).get(MainActivityViewModel.class);
        setupRecyclerView(mViewModel);
        mViewModel.getStudents().observe(getActivity(), new Observer<List<Student>>() {
            @Override
            public void onChanged(@Nullable List<Student> students) {
                mAdapter = new AdaptadorStudent((ArrayList<Student>) students, getContext());
                mBinding.lstStudents.setAdapter(mAdapter);
                lstIsEmpty((ArrayList<Student>) students);
            }
        });
    }

    /***
     * Setups the recycler view
     * @param viewModel
     */
    private void setupRecyclerView(MainActivityViewModel viewModel) {
        mBinding.lstStudents.setHasFixedSize(true);
        mAdapter = new AdaptadorStudent((ArrayList<Student>) viewModel.getStudents().getValue(), getContext());
        mBinding.lstStudents.setAdapter(mAdapter);
        mLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        mBinding.lstStudents.setLayoutManager(mLayoutManager);
        mBinding.lstStudents.setItemAnimator(new DefaultItemAnimator());
        mBinding.lstStudents.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
    }

    /***
     * Checks if the list of business is empty, then shows the emptyview, else, hides the emptyview and shows the list
     * @param business
     */
    private void lstIsEmpty(ArrayList<Student> business) {
        if (business.isEmpty()) {
            mBinding.lstStudents.setVisibility(View.GONE);
            mBinding.lblEmptyView.setVisibility(View.VISIBLE);
        } else {
            mBinding.lstStudents.setVisibility(View.VISIBLE);
            mBinding.lblEmptyView.setVisibility(View.GONE);
        }
    }
}