package com.example.eclip.app14_galeria.utils;

import android.content.Context;
import android.content.res.Configuration;

/**
 * Created by eclip on 25/11/2017.
 */

public class ConfigurationUtils {

    private ConfigurationUtils() {
    }

    public static boolean hasLandscapeOrientation(Context context) {
        return context.getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;
    }
}
