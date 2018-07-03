package com.example.carlo.preferencias_epicas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.carlo.preferencias_epicas.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    SharedPreferences preferencias;
    ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        preferencias = PreferenceManager.getDefaultSharedPreferences(this);

        String value = preferencias.getString(getString(R.string.pref_init_view_key), "?¿¿?¿");

        int days = Integer.parseInt(preferencias.getString(getString(R.string.pref_def_days), "123"));

        mBinding.lblDefaultView.setText(value);

        mBinding.lblNumberDays.setText(String.format("%d", days));
    }


    public void abrirPreferencias(View view) {
        Intent intent = new Intent(this, ActivitySettings.class);
        startActivity(intent);
    }

}
