package com.example.eclip.app15_fragment.utils;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public class FragmentUtils {

    private FragmentUtils() {
    }

    public static void replaceFragment(@NonNull FragmentManager fragmentManager,
                                       @IdRes int parentResId, @NonNull Fragment fragment, @NonNull String tag) {
        fragmentManager.beginTransaction().replace(parentResId, fragment, tag).commit();
    }

    @SuppressWarnings("SameParameterValue")
    public static void replaceFragmentAddToBackstack(@NonNull FragmentManager fragmentManager,
                                                     @IdRes int parentResId, @NonNull Fragment fragment, @NonNull String tag,
                                                     @NonNull String backstackTag, int transition) {
        fragmentManager.beginTransaction().replace(parentResId, fragment, tag).setTransition(
                transition).addToBackStack(backstackTag).commit();
    }

}