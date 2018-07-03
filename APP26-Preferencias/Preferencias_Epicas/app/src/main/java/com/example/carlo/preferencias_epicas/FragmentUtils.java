package com.example.carlo.preferencias_epicas;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;

/**
 * Created by carlo on 04/03/2018.
 */

public class FragmentUtils {

    private FragmentUtils() {
    }

    @SuppressWarnings("SameParameterValue")
    public static void replaceFragment(@NonNull FragmentManager fragmentManager,
                                       @IdRes int parentResId, @NonNull Fragment fragment, @NonNull String tag) {
        fragmentManager.beginTransaction().replace(parentResId, fragment, tag).commit();
    }

}
