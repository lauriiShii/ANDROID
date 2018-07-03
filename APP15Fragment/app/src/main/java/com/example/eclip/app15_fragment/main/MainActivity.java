package com.example.eclip.app15_fragment.main;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.eclip.app15_fragment.R;
import com.example.eclip.app15_fragment.detail.DetailActivity;
import com.example.eclip.app15_fragment.detail.DetailFragment;
import com.example.eclip.app15_fragment.utils.ConfigurationsUtils;
import com.example.eclip.app15_fragment.utils.FragmentUtils;


public class MainActivity extends AppCompatActivity implements MainFragment.Callback,
        DetailFragment.Callback {

    private static final String TAG_MAIN_FRAGMENT = "TAG_MAIN_FRAGMENT";
    private static final String TAG_DETAIL_FRAGMENT = "TAG_DETAIL_FRAGMENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // This is the correct order to load the fragments.
        if (getSupportFragmentManager().findFragmentByTag(TAG_MAIN_FRAGMENT) == null) {
            FragmentUtils.replaceFragment(getSupportFragmentManager(), R.id.flMain,
                    MainFragment.newInstance(), TAG_MAIN_FRAGMENT);
        }
        if (ConfigurationsUtils.hasLandscapeOrientation(this)
                && getSupportFragmentManager().findFragmentByTag(TAG_DETAIL_FRAGMENT) == null) {
            FragmentUtils.replaceFragment(getSupportFragmentManager(), R.id.flDetail,
                    DetailFragment.newInstance(getString(R.string.main_activity_no_item),
                            MainFragment.NO_ITEM_SELECTED), TAG_MAIN_FRAGMENT);
        }
    }

    @Override
    public void onItemSelected(String item, int position) {
        if (ConfigurationsUtils.hasLandscapeOrientation(this)) {
            showDetailFragment(item, position);
        } else {
            DetailActivity.start(this, item, position);
        }
    }

    private void showDetailFragment(String item, int position) {
        FragmentUtils.replaceFragmentAddToBackstack(getSupportFragmentManager(), R.id.flDetail,
                DetailFragment.newInstance(item, position), TAG_DETAIL_FRAGMENT, item,
                FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
    }

    // When detail shown (even from backstack).
    @Override
    public void onDetailShown(int position) {
        MainFragment mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(
                R.id.flMain);
        mainFragment.selectItem(position);
    }

    @Override
    public void onBackPressed() {
        if (!ConfigurationsUtils.hasLandscapeOrientation(this)) {
            // No backstack.
            getSupportFragmentManager().popBackStack(null,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        super.onBackPressed();
    }

}