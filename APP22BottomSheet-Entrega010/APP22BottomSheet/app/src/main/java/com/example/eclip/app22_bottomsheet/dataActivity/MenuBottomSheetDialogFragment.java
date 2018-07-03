package com.example.eclip.app22_bottomsheet.dataActivity;

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
import com.example.eclip.app22_bottomsheet.bdd.model.User;

import static com.example.eclip.app22_bottomsheet.utils.Constants.ARG_NAME;
import static com.example.eclip.app22_bottomsheet.utils.Constants.ARG_PHONE;
import static com.example.eclip.app22_bottomsheet.utils.Constants.SHEET_PEAK_HEIGHT;

/**
 * Created by eclip on 26/01/2018.
 */
public class MenuBottomSheetDialogFragment extends BottomSheetDialogFragment implements
        NavigationView.OnNavigationItemSelectedListener {

    private String name; // User name
    private String phone; // USer phone
    private NavigationView navigationView; // View for menu

    /***
     * New instance for menu.
     * @param name
     * @param phone
     * @return
     */
    static MenuBottomSheetDialogFragment newInstance(String name, String phone) {
        MenuBottomSheetDialogFragment frg = new MenuBottomSheetDialogFragment();
        Bundle arguments = new Bundle();
        arguments.putString(ARG_NAME, name);
        arguments.putString(ARG_PHONE, phone);
        frg.setArguments(arguments);
        return frg;
    }

    /**
     * View menu on inferface.
     */
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

    /**
     * Instance and configuration menu.
     * @param view
     */
    private void initViews(View view) {
        navigationView = view.findViewById(R.id.navigationView);
        setupBottomSheet(view);
    }


    /**
     * Configuration menu´s botton.
     * @param view
     */
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

    /**
     * Configuration menu.
     */
    private void setupNavigationView() {
        navigationView.setNavigationItemSelectedListener(this);
        if (name != null) {
            navigationView.getMenu().findItem(R.id.mnuTitle).setTitle(name);
        }
    }

    /**
     * Argument for user, name and phone.
     */
    private void obtainArguments() {
        if (getArguments() != null) {
            name = getArguments().getString(ARG_NAME);
            phone = getArguments().getString(ARG_PHONE);
        }
    }

    /**
     * Action for menu´s item.
     * @param item
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnuCall:
                call();
                dismiss();
                return true;
            case R.id.mnuSave:
                save();
                dismiss();
                return true;
        }
        return false;
    }

    /**
     * Call user.
     */
    private void call() {
        Toast.makeText(getContext(), getString(R.string.menubottomsheetfragment_call, name),
                Toast.LENGTH_SHORT).show();
    }

    /**
     * Save new user data.
     */
    private void save() {
        User.getInstance().setName(name);
        User.getInstance().setPhone(phone);
        Toast.makeText(getContext(), "Se ha actualizado la informacion del usuario\nNombre: "+name+"\nTelefono: "+ phone,
                Toast.LENGTH_SHORT).show();
    }
}
