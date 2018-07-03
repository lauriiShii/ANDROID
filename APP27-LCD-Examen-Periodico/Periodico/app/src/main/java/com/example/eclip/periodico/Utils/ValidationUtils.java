package com.example.eclip.periodico.Utils;

import android.text.TextUtils;
import android.util.Patterns;

/**
 *
 * Class that collects all the validations that must be done in the ProfileActivity
 * to be able to manage the intent
 *
 * @author Laura Calvente
 * @version 2017
 * @since 1.0
 */

public class ValidationUtils {

    /**
     * ValidationUtils: builder
     */
    private ValidationUtils() {
    }

    /**
     * IsValidEmail: if the text complies with the email pattern it returns true
     * @param email text
     * @return if valid
     */
    public static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    /**
     * IsValidPhone: if the text complies with the phone pattern it returns true
     * @param phoneNumber text
     * @return if valid
     */
    public static boolean isValidPhone(String phoneNumber) {
        return !TextUtils.isEmpty(phoneNumber) && Patterns.PHONE.matcher(phoneNumber).matches();
    }

    /**
     * IsValidUrl: if the text complies with the url pattern it returns true
     * @param url text
     * @return if valid
     */
    public static boolean isValidUrl(String url) {
        return !TextUtils.isEmpty(url) && Patterns.WEB_URL.matcher(url).matches();
    }

}
