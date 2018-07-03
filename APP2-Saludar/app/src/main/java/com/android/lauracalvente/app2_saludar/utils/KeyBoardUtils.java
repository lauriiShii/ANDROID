package com.android.lauracalvente.app2_saludar.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.android.lauracalvente.app2_saludar.MainActivity;

/**
 * Created by Laura on 25/09/2017.
 */

public class KeyBoardUtils {

    private KeyBoardUtils(){}

    static public void hideOffKeyBoard(View view, Context c){
        InputMethodManager imm =
                (InputMethodManager)c.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
