package com.android.lauracalvente.app9_intent_implicitos.MessageManager;


import android.view.View;
import android.widget.Toast;

@SuppressWarnings("unused")
public interface MessageManager {

    default void showMessage(View refView, String message) {
        Toast.makeText(refView.getContext(), message, Toast.LENGTH_SHORT).show();
    }

}