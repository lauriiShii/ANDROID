package com.example.eclip.app25_fct.preference;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

import com.example.eclip.app25_fct.R;
import com.example.eclip.app25_fct.utils.FragmentUtilsPreference;

import static com.example.eclip.app25_fct.utils.Constantes.TAG_SETTINGS_FRAGMENT;

public class PreferenceActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);
        Toolbar toolbar = findViewById(R.id.settingsToolbar);
        setActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        loadSettingsFragment();
    }

    private void loadSettingsFragment() {
        if (getFragmentManager().findFragmentById(R.id.frameLayout) == null) {
            FragmentUtilsPreference.replaceFragment(getFragmentManager(), R.id.frameLayout,
                    new PreferenceSettingFragment(), TAG_SETTINGS_FRAGMENT);
        }
    }
}
