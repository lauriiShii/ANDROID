package com.example.eclip.periodico.preferenceActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

import com.example.eclip.periodico.R;
import com.example.eclip.periodico.Utils.FragmentUtilsPreference;

import static com.example.eclip.periodico.Utils.Constantes.TAG_SETTINGS_FRAGMENT;

public class PreferenceSettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference_setting);
        loadSettingsFragment();
    }

    private void loadSettingsFragment() {
        if (getFragmentManager().findFragmentById(R.id.frameLayout) == null) {
            FragmentUtilsPreference.replaceFragment(getFragmentManager(), R.id.frameLayout,
                    new PreferenceSettingFragment(), TAG_SETTINGS_FRAGMENT);
        }

    }
}
