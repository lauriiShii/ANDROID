package com.android.lauracalvente.app4_poketri.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.WindowManager;

import com.android.lauracalvente.app4_poketri.Fragment.gameFragment;
import com.android.lauracalvente.app4_poketri.Fragment.mainFragment;
import com.android.lauracalvente.app4_poketri.Fragment.scoresFragment;
import com.android.lauracalvente.app4_poketri.MainActivity;
import com.android.lauracalvente.app4_poketri.Utils.Constantes;

public class PagerAdapter extends FragmentStatePagerAdapter {

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new scoresFragment();
            case 1:
                return new mainFragment();
            case 2:
                return new gameFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return Constantes.NUMBER_OF_PAGES;
    }
}