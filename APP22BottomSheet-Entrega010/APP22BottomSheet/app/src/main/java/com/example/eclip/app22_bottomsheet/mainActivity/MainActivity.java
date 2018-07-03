package com.example.eclip.app22_bottomsheet.mainActivity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.eclip.app22_bottomsheet.R;
import com.example.eclip.app22_bottomsheet.mainActivity.fragments.AboutFragment;
import com.example.eclip.app22_bottomsheet.mainActivity.fragments.DataFragment;
import com.example.eclip.app22_bottomsheet.mainActivity.fragments.PictureFragment;
import com.example.eclip.app22_bottomsheet.utils.FragmentUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.eclip.app22_bottomsheet.utils.Constants.TAG_ABOUT;
import static com.example.eclip.app22_bottomsheet.utils.Constants.TAG_DATA;
import static com.example.eclip.app22_bottomsheet.utils.Constants.TAG_PICTURE;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.message)
    TextView mTextMessage; // Title of the fragment shown
    @BindView(R.id.frgInfo)
    FrameLayout frgInfo; // Fragment to load
    @BindView(R.id.navigation)
    BottomNavigationView navigation; // Navigation menu
    private static String TAG = TAG_DATA; // Stores the TAG of the fragment that is currently displayed
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        /**
         * Whenever a button is pressed, the tag of the fragment that must be shown and displayed is updated.
         * @param item
         * @return
         */
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_profile:
                    TAG = TAG_DATA;
                    openFragment();
                    return true;
                case R.id.navigation_build:
                    TAG = TAG_PICTURE;
                    openFragment();
                    return true;
                case R.id.navigation_notifications:
                    TAG = TAG_ABOUT;
                    openFragment();
                    return true;
            }
            return false;
        }
    };

    /**
     * The activity is created, the listener that manages the menu is called, the first fragment is shown.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        openFragment();
    }

    /**
     * Remember that the menu button was pressed and shows it. Update the title of the fragment
     * to be displayed according to the selected button.
     */
    private void updateInterface (){
        if(TAG.equals(TAG_DATA)) {
            mTextMessage.setText(R.string.title_home);
            navigation.getMenu().getItem(0).setChecked(true);
        }
        else if (TAG.equals(TAG_PICTURE)) {
            mTextMessage.setText(R.string.title_dashboard);
            navigation.getMenu().getItem(1).setChecked(true);
        }
        else {
            mTextMessage.setText(R.string.title_notifications);
            navigation.getMenu().getItem(2).setChecked(true);
        }
    }

    /**
     * Call the corresponding Utils class method to display the fragment and update the entire interface.
     */
    private void openFragment(){

        updateInterface();

        if (TAG.equals(TAG_DATA) && getSupportFragmentManager().findFragmentByTag(TAG) == null) {
            FragmentUtils.replaceFragment(getSupportFragmentManager(), R.id.frgInfo, DataFragment.newInstance(), TAG);
        }
        else if (TAG.equals(TAG_ABOUT) && getSupportFragmentManager().findFragmentByTag(TAG) == null) {
            FragmentUtils.replaceFragment(getSupportFragmentManager(), R.id.frgInfo, AboutFragment.newInstance(), TAG);
        }
        else if (TAG.equals(TAG_PICTURE) && getSupportFragmentManager().findFragmentByTag(TAG) == null) {
            FragmentUtils.replaceFragment(getSupportFragmentManager(), R.id.frgInfo, PictureFragment.newInstance(), TAG);
        }
    }
}