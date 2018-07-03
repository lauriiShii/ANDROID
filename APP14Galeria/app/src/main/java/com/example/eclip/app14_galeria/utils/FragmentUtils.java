package com.example.eclip.app14_galeria.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.eclip.app14_galeria.model.artWorks.pojo.ArtWork;

/**
 * Created by eclip on 24/11/2017.
 */

public class FragmentUtils {

    private FragmentUtils(){}

    public static void replaceFragment(FragmentManager fragmentManager,
                                       int parentResId, Fragment fragment, String tag) {
        fragmentManager.beginTransaction().replace(parentResId, fragment, tag).commit();
    }

    public static void replaceFragmentAddToBackstack(FragmentManager fragmentManager,
                                                     int parentResId, Fragment fragment, String tag,
                                                     ArtWork backstackTag, int transition) {
        fragmentManager.beginTransaction().replace(parentResId, fragment, tag).setTransition(
                transition).addToBackStack(backstackTag.getName()).commit();
    }
}
