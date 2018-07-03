package com.example.eclip.app25_fct.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.squareup.picasso.Picasso;

/**
 * Created by eclip on 01/12/2017.
 */

public class ImgUtils {

    static public void loadImg (Context context, ImageView image, String src, String name){

        TextDrawable.IBuilder mDrawableBuilder = TextDrawable.builder()
                .beginConfig()
                .width(60)
                .height(60)
                .toUpperCase()
                .endConfig()
                .round();

        Picasso.with(context)
                .load(src)
                .placeholder(mDrawableBuilder.build(name.substring(0,1), ColorGenerator.MATERIAL.getColor(name.substring(0,1))))
                .error(mDrawableBuilder.build(name.substring(0,1), ColorGenerator.MATERIAL.getColor(name.substring(0,1))))
                .into(image);
    }

    static public void loadImg (Context context, ImageView image, String name){

        TextDrawable.IBuilder mDrawableBuilder = TextDrawable.builder()
                .beginConfig()
                .width(60)
                .height(60)
                .withBorder(4) /* thickness in px */
                .toUpperCase()
                .endConfig()
                .roundRect(5);

        Picasso.with(context)
                .load("noURL")
                .placeholder(mDrawableBuilder.build(name.substring(0,1), ColorGenerator.MATERIAL.getColor(name.substring(0,1))))
                .error(mDrawableBuilder.build(name.substring(0,1), ColorGenerator.MATERIAL.getColor(name.substring(0,1))))
                .into(image);
    }

    static public void loadImgVisit (Context context, ImageView image, String observation){

        TextDrawable.IBuilder mDrawableBuilder = TextDrawable.builder()
                .beginConfig()
                .width(60)
                .height(60)
                .withBorder(4) /* thickness in px */
                .toUpperCase()
                .endConfig()
                .roundRect(1);

                if(observation == null || observation.equals("")) {
                    Picasso.with(context)
                            .load("noURL")
                            .placeholder(mDrawableBuilder.build("¿?", ColorGenerator.MATERIAL.getColor("¿?".substring(0, 1))))
                            .error(mDrawableBuilder.build("¿?", ColorGenerator.MATERIAL.getColor("¿?".substring(0, 1))))
                            .into(image);
                }
                else{
                    Picasso.with(context)
                            .load("noURL")
                            .placeholder(mDrawableBuilder.build("OK", ColorGenerator.MATERIAL.getColor("OK".substring(0, 1))))
                            .error(mDrawableBuilder.build("OK", ColorGenerator.MATERIAL.getColor("OK".substring(0, 1))))
                            .into(image);
                }
    }
}
