package com.example.eclip.app25_fct.utils;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;

/**
 * Esto es asi porq no se puede usar el supooort porq sino no muestra la action bar ......
 * */
public class FragmentUtilsPreference {

    private FragmentUtilsPreference() {
    }

    @SuppressWarnings("SameParameterValue")
    public static void replaceFragment(@NonNull FragmentManager fragmentManager,
                                       @IdRes int parentResId, @NonNull Fragment fragment, @NonNull String tag) {
        fragmentManager.beginTransaction().replace(parentResId, fragment, tag).commit();
    }

}
