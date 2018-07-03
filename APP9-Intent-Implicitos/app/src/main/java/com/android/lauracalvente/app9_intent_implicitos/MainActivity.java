package com.android.lauracalvente.app9_intent_implicitos;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.android.lauracalvente.app9_intent_implicitos.MessageManager.ToastMessageManager;
import com.android.lauracalvente.app9_intent_implicitos.Utils.Constantes;
import com.android.lauracalvente.app9_intent_implicitos.Utils.IntentsUtils;
import com.android.lauracalvente.app9_intent_implicitos.Utils.NetworkUtils;
import com.android.lauracalvente.app9_intent_implicitos.Utils.PermissionUtils;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@SuppressWarnings({"FieldCanBeLocal", "SameParameterValue"})
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btnShowInBrowser)
    Button btnShowInBrowser;
    @BindView(R.id.btnSearch)
    Button btnSearch;
    @BindView(R.id.btnCall)
    Button btnCall;
    @BindView(R.id.btnDial)
    Button btnDial;
    @BindView(R.id.btnShowInMap)
    Button btnShowInMap;
    @BindView(R.id.btnSearchInMap)
    Button btnSearchInMap;
    @BindView(R.id.btnShowContacts)
    Button btnShowContacts;


    private ToastMessageManager mMessageManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mMessageManager = new ToastMessageManager();
    }

    @OnClick(R.id.btnShowInBrowser)
    public void onBtnShowInBrowserClicked() {
        showInBrowser(Constantes.WEB_URL);
    }

    @OnClick(R.id.btnSearch)
    public void onBtnSearchClicked() {
        search(Constantes.SEARCH_TEXT);
    }

    @OnClick(R.id.btnCall)
    public void onBtnCallClicked() {
        wantsToCall(Constantes.PHONE_NUMBER);
    }

    @OnClick(R.id.btnDial)
    public void onBtnDialClicked() {
        dial(Constantes.PHONE_NUMBER);
    }

    @OnClick(R.id.btnShowInMap)
    public void onBtnShowInMapClicked() {
        showInMap(Constantes.LONGITUDE, Constantes.LATITUDE, Constantes.ZOOM);
    }

    @OnClick(R.id.btnSearchInMap)
    public void onBtnSearchInMapClicked() {
        searchInMap(Constantes.MAP_SEARCH_TEXT);
    }

    @OnClick(R.id.btnShowContacts)
    public void onBtnShowContactsClicked() {
        showContacts();
    }

    private void showInBrowser(String url) {
        if (NetworkUtils.isConnectionAvailable(getApplicationContext())) {
            Intent intent = IntentsUtils.newViewUriIntent(Uri.parse(url));
            if (IntentsUtils.isActivityAvailable(getApplicationContext(), intent)) {
                startActivity(intent);
            } else {
                mMessageManager.showMessage(btnShowInBrowser, getString(R.string.main_activity_no_web_browser));
            }
        } else {
            mMessageManager.showMessage(btnShowInBrowser, getString(R.string.main_activity_no_connection));
        }
    }

    private void search(String text) {
        if (NetworkUtils.isConnectionAvailable(getApplicationContext())) {
            Intent intent = IntentsUtils.newWebSearchIntent(text);
            if (IntentsUtils.isActivityAvailable(getApplicationContext(), intent)) {
                startActivity(intent);
            } else {
                mMessageManager.showMessage(btnSearch, getString(R.string.main_activity_no_web_search));
            }
        } else {
            mMessageManager.showMessage(btnSearch, getString(R.string.main_activity_no_connection));
        }
    }

    private void wantsToCall(String phoneNumber) {
        if (!PermissionUtils.canCall(this)) {
            requestCallPermission();
        } else {
            call(phoneNumber);
        }
    }

    private void requestCallPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE},
                Constantes.RP_CALL);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == Constantes.RP_CALL && PermissionUtils.canCall(this)) {
            call(Constantes.PHONE_NUMBER);
        } else {
            // Check if the user set "Don't ask again"
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CALL_PHONE)) {
                reportRationale();
            } else {
                mMessageManager.showMessage(btnCall, getString(R.string.main_activity_no_call_permission_rationale));
            }
        }
    }

    private void reportRationale() {
        Snackbar.make(btnCall, R.string.general_permission_required, Snackbar.LENGTH_LONG).setAction(
                R.string.general_configure,
                view -> IntentsUtils.startInstalledAppDetailsActivity(MainActivity
                        .this)).show();
    }

    private void call(String phoneNumber) {
        Intent intent = IntentsUtils.newCallIntent(phoneNumber);
        if (IntentsUtils.isActivityAvailable(getApplicationContext(), intent)) {
            startActivity(intent);
        } else {
            mMessageManager.showMessage(btnCall,
                    getString(R.string.main_activity_no_call_app));
        }
    }

    private void dial(String phoneNumber) {
        Intent intent = IntentsUtils.newDialIntent(phoneNumber);
        if (IntentsUtils.isActivityAvailable(getApplicationContext(), intent)) {
            startActivity(intent);
        } else {
            mMessageManager.showMessage(btnDial, getString(R.string.main_activity_no_dial_app));
        }
    }

    private void showInMap(double longitude, double latitude, int zoom) {
        Intent intent = IntentsUtils.newShowInMapIntent(longitude, latitude, zoom);
        if (IntentsUtils.isActivityAvailable(getApplicationContext(), intent)) {
            startActivity(intent);
        } else {
            mMessageManager.showMessage(btnShowInMap, getString(R.string.main_activity_no_maps_app));
        }
    }

    private void searchInMap(String text) {
        Intent intent = IntentsUtils.newSearchInMapIntent(text);
        if (IntentsUtils.isActivityAvailable(getApplicationContext(), intent)) {
            startActivity(intent);
        } else {
            mMessageManager.showMessage(btnShowInMap, getString(R.string.main_activity_no_maps_app));
        }
    }

    private void showContacts() {
        Intent intent = IntentsUtils.newContactsIntent();
        if (IntentsUtils.isActivityAvailable(getApplicationContext(), intent)) {
            startActivity(intent);
        } else {
            mMessageManager.showMessage(btnShowContacts,
                    getString(R.string.main_activity_no_contacts_app));
        }
    }
}
