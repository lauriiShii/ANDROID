package com.android.lauracalvente.app10_gatitos.Utils;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;

import java.util.List;

/**
 * Created by Laura on 20/10/2017.
 */

public class IntentUtils {

    // Is any activity available to use the intent.
    public static boolean isActivityAvailable(Context ctx, Intent intent) {
        final PackageManager packageManager = ctx.getApplicationContext().getPackageManager();
        List<ResolveInfo> appList = packageManager.queryIntentActivities(intent,
                PackageManager.MATCH_DEFAULT_ONLY);
        return appList.size() > 0;
    }

    public static Intent newSearchInMapIntent(String text) {
        return new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + text));
    }

    public static Intent newWebSearchIntent(String text) {
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        intent.putExtra(SearchManager.QUERY, text);
        return intent;
    }

    public static Intent newDialIntent(String phoneNumber) {
        return new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber.trim()));
    }

    public static Intent sendEmail (String [] destiny) {
        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.setType("text/html");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, destiny);
        return emailIntent;
    }
}
