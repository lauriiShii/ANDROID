package com.example.eclip.app22_bottomsheet.MainActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eclip.app22_bottomsheet.BuildActivity.BuildActivity;
import com.example.eclip.app22_bottomsheet.ProfileActivity.ProfileActivity;
import com.example.eclip.app22_bottomsheet.R;
import com.example.eclip.app22_bottomsheet.utils.FragmentUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.eclip.app22_bottomsheet.utils.Constants.TAG_BUILD;
import static com.example.eclip.app22_bottomsheet.utils.Constants.TAG_NOTIFICATIONS;
import static com.example.eclip.app22_bottomsheet.utils.Constants.TAG_PROFILE;

public class MainActivity extends AppCompatActivity implements GenericFragment.Callback {

    @BindView(R.id.message)
    TextView mTextMessage;
    @BindView(R.id.frgInfo)
    FrameLayout frgInfo;
    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    private static int btnNavigation = R.id.navigation_profile;
    private static String TAG = TAG_PROFILE;
    private String text;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_profile:
                    btnNavigation = R.id.navigation_profile;
                    TAG = TAG_PROFILE;
                    openFragment();
                    return true;
                case R.id.navigation_build:
                    btnNavigation =  R.id.navigation_build;
                    TAG = TAG_BUILD;
                    openFragment();
                    return true;
                case R.id.navigation_notifications:
                    btnNavigation = R.id.navigation_notifications;
                    TAG = TAG_NOTIFICATIONS;
                    openFragment();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        openFragment();
    }

    private void openFragment(){
        //Update interface
        updateText();
        updateButtons();

        //Open fragment
        if (getSupportFragmentManager().findFragmentByTag(TAG) == null) {
            FragmentUtils.replaceFragment(getSupportFragmentManager(), R.id.frgInfo, GenericFragment.newInstance(text), TAG);
        }
    }

    private void updateText (){
        if(TAG.equals(TAG_PROFILE)) {
            mTextMessage.setText(R.string.title_home);
            text = "open profile";
        }
        else if (TAG.equals(TAG_BUILD)) {
            mTextMessage.setText(R.string.title_dashboard);
            text = "open build";
        }
        else {
            mTextMessage.setText(R.string.title_notifications);
            text = "empty";
        }
    }

    private void updateButtons (){
        if(btnNavigation != navigation.getSelectedItemId()) {
            if(btnNavigation == R.id.navigation_profile)
                navigation.getMenu().getItem(0).setChecked(true);
            if(btnNavigation == R.id.navigation_build)
                navigation.getMenu().getItem(1).setChecked(true);
            if(btnNavigation == R.id.navigation_notifications)
                navigation.getMenu().getItem(2).setChecked(true);
        }
    }

    @Override
    public void open(String text) {
        if(TAG.equals(TAG_PROFILE)){
            startActivity(new Intent(this,  ProfileActivity.class));
        }
        else if (TAG.equals(TAG_BUILD)){
            startActivity(new Intent(this,  BuildActivity.class));
        }
        else{
            Toast.makeText(this, "You haven´t notifications", Toast.LENGTH_SHORT).show();
        }
    }
}
