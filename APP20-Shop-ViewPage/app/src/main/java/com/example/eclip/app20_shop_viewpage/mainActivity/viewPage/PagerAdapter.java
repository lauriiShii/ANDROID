package com.example.eclip.app20_shop_viewpage.mainActivity.viewPage;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.eclip.app20_shop_viewpage.bdd.Shop.DataBaseItemsShop;
import com.example.eclip.app20_shop_viewpage.bdd.Shop.RepositoryImpItems;
import com.example.eclip.app20_shop_viewpage.bdd.Shop.RepositoryItems;
import com.example.eclip.app20_shop_viewpage.mainActivity.fragment.ItemFragment;

import static com.example.eclip.app20_shop_viewpage.utils.FabUtils.openFab;

/**
 * Created by eclip on 14/01/2018.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {

    private RepositoryItems mData; // Shopping Items repository
    private Context mContext; // MainActivity Context

    /**
     * Builder
     * @param fm FragmentManager
     * @param c Context
     */
    public PagerAdapter(FragmentManager fm, Context c) {
        super(fm);
        mData = RepositoryImpItems.getInstance(DataBaseItemsShop.getInstance());
        mContext = c;
    }

    @Override
    public Fragment getItem(int position) {

        return ItemFragment.newInstance(mContext, mData.getItems().get(position));
    }

    @Override
    public int getCount() {
        return mData.getItems().size();
    }
}