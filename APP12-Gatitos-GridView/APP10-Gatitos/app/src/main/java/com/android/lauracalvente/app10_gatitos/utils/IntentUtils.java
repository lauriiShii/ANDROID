package com.android.lauracalvente.app10_gatitos.utils;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;

import java.util.List;

/**
 *
 * Class that collects all the explicit intent that will be used in this application
 *
 * @author Laura Calvente
 * @version 2017
 * @since 1.0
 */

public class IntentUtils {

    /**
     * IsActivityAvailable: method used in ProfileActivity to check if the intent can be launched explicitly or not
     * (it is checked whether there are applications installed or not)
     * @param ctx context of the activity where it is used
     * @param intent to launch
     * @return if there are applications that can read the installed intent
     */
    public static boolean isActivityAvailable(Context ctx, Intent intent) {
        final PackageManager packageManager = ctx.getApplicationContext().getPackageManager();
        List<ResolveInfo> appList = packageManager.queryIntentActivities(intent,
                PackageManager.MATCH_DEFAULT_ONLY);
        return appList.size() > 0;
    }

    /**
     * NewSearchInMapIntent: look for a direction in a map application
     * @param text search
     * @return the intent
     */
    public static Intent newSearchInMapIntent(String text) {
        return new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + text));
    }

    /**
     * NewWebSearchIntent: look for a url in a navegation application
     * @param text search
     * @return the intent
     */
    public static Intent newWebSearchIntent(String text) {
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        intent.putExtra(SearchManager.QUERY, text);
        return intent;
    }

    /**
     * NewDialIntent: dial the phone number in an application for calls
     * @param phoneNumber number
     * @return the intent
     */
    public static Intent newDialIntent(String phoneNumber) {
        return new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber.trim()));
    }

    /**
     * SendEmail: prepare the messaging application to send a message
     * @param destiny recipient of the message
     * @return the intent
     */
    public static Intent sendEmail (String [] destiny) {
        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.setType("text/html");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, destiny);
        return emailIntent;
    }
}
