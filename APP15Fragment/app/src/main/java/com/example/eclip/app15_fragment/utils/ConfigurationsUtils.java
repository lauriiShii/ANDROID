package com.example.eclip.app15_fragment.utils;

import android.content.Context;
import android.content.res.Configuration;

public class ConfigurationsUtils {

    private ConfigurationsUtils() {
    }

    public static boolean hasLandscapeOrientation(Context context) {
        return context.getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;
    }

}