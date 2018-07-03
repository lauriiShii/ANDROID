package com.example.carlo.preferencias_epicas;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

/**
 * Created by carlo on 04/03/2018.
 */

public class ActivitySettings extends Activity {

    private static final String TAG_SETTINGS_FRAGMENT = "TAG_SETTINGS_FRAGMENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_settings_activity);
        Toolbar toolbar = findViewById(R.id.settingsToolbar);
        setActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_nav);
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
            FragmentUtils.replaceFragment(getFragmentManager(), R.id.frameLayout,
                    new FragmentSettings(), TAG_SETTINGS_FRAGMENT);
        }
    }
}
