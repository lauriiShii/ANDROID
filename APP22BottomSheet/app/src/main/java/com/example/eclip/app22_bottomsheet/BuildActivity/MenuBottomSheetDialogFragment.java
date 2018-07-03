package com.example.eclip.app22_bottomsheet.BuildActivity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.eclip.app22_bottomsheet.R;

/**
 * Created by eclip on 26/01/2018.
 */
public class MenuBottomSheetDialogFragment extends BottomSheetDialogFragment implements
        NavigationView.OnNavigationItemSelectedListener {

    private static final String ARG_STUDENT = "ARG_STUDENT";
    private static final int SHEET_PEAK_HEIGHT = 650;

    private String name;
    private NavigationView navigationView;

    static MenuBottomSheetDialogFragment newInstance(String name) {
        MenuBottomSheetDialogFragment frg = new MenuBottomSheetDialogFragment();
        Bundle arguments = new Bundle();
        arguments.putString(ARG_STUDENT, name);
        frg.setArguments(arguments);
        return frg;
    }

    private final BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new
            BottomSheetBehavior.BottomSheetCallback() {

                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {
                    if (newState == BottomSheetBehavior.STATE_HIDDEN
                            || newState == BottomSheetBehavior.STATE_SETTLING) {
                        dismiss();
                    }

                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                }

            };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bottomsheet, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        obtainArguments();
        initViews(getView());
    }

    private void initViews(View view) {
        navigationView = view.findViewById(R.id.navigationView);
        setupBottomSheet(view);
    }

    private void setupBottomSheet(View view) {
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) view
                .getParent())
                .getLayoutParams();
        CoordinatorLayout.Behavior behavior = params.getBehavior();
        if (behavior != null && behavior instanceof BottomSheetBehavior) {
            BottomSheetBehavior bottomSheetBehavior = (BottomSheetBehavior) behavior;
            bottomSheetBehavior.setBottomSheetCallback(mBottomSheetBehaviorCallback);
            // To assure sheet is completely shown.
            bottomSheetBehavior.setPeekHeight(SHEET_PEAK_HEIGHT);//get the height dynamically
        }
        setupNavigationView();
    }

    private void setupNavigationView() {
        navigationView.setNavigationItemSelectedListener(this);
        if (name != null) {
            navigationView.getMenu().findItem(R.id.mnuTitle).setTitle(name);
        }
    }

    private void obtainArguments() {
        if (getArguments() != null) {
            name = getArguments().getString(ARG_STUDENT);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnuCall:
                call();
                dismiss();
                return true;
            case R.id.mnuSendMessage:
                sendMessage();
                dismiss();
                return true;
            case R.id.mnuNotes:
                seeNotes();
                dismiss();
                return true;
        }
        return false;
    }

    private void call() {
        Toast.makeText(getContext(), getString(R.string.menubottomsheetfragment_call, name),
                Toast.LENGTH_SHORT).show();
    }

    private void sendMessage() {
        Toast.makeText(getContext(),
                getString(R.string.menubottomsheetfragment_send_message, name),
                Toast.LENGTH_SHORT).show();
    }

    private void seeNotes() {
        Toast.makeText(getContext(), getString(R.string.menubottomsheetfragment_see_notes, name),
                Toast.LENGTH_SHORT).show();
    }
}
