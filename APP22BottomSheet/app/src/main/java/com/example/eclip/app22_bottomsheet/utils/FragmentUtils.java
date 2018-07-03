package com.example.eclip.app22_bottomsheet.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * Created by eclip on 24/11/2017.
 */

public class FragmentUtils {

    private FragmentUtils(){}

    public static void replaceFragment(FragmentManager fragmentManager,
                                       int parentResId, Fragment fragment, String tag) {
        fragmentManager.beginTransaction().replace(parentResId, fragment, tag).commit();
    }
}
