package com.android.lauracalvente.app10_gatitos.Utils;

import android.text.TextUtils;
import android.util.Patterns;

public class ValidationUtils {

    private ValidationUtils() {
    }

    //if email is valid, return bool = true;
    public static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    //if phone is valid, return bool = true;
    public static boolean isValidPhone(String phoneNumber) {
        return !TextUtils.isEmpty(phoneNumber) && Patterns.PHONE.matcher(phoneNumber).matches();
    }

    //if uri is valid, return bool = true;
    public static boolean isValidUrl(String url) {
        return !TextUtils.isEmpty(url) && Patterns.WEB_URL.matcher(url).matches();
    }

}
