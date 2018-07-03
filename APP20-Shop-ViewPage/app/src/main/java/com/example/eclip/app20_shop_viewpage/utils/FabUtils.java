package com.example.eclip.app20_shop_viewpage.utils;

import android.animation.Animator;
import android.content.Context;
import android.os.Build;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

/**
 * Created by eclip on 16/01/2018.
 */

public class FabUtils {

    private FabUtils(){}

    public static void openFab (Context mContext, android.support.design.widget.FloatingActionButton fab){

        fab.setScaleX(0);
        fab.setScaleY(0);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            final Interpolator interpolador = AnimationUtils.loadInterpolator(mContext,
                    android.R.interpolator.fast_out_slow_in);

            fab.animate()
                    .scaleX(1)
                    .scaleY(1)
                    .setInterpolator(interpolador)
                    .setDuration(600);
        }
    }

    public static void closeFab (Context mContext, final android.support.design.widget.FloatingActionButton fab){

        fab.setScaleX(1);
        fab.setScaleY(1);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            final Interpolator interpolador = AnimationUtils.loadInterpolator(mContext,
                    android.R.interpolator.fast_out_slow_in);

            fab.animate().setListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            fab.animate()
                                    .scaleY(0)
                                    .scaleX(0)
                                    .setInterpolator(interpolador)
                                    .setDuration(600)
                                    .start();
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });
        }
    }
}
