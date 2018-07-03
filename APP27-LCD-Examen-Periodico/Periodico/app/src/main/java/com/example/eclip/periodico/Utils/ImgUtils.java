package com.example.eclip.periodico.Utils;

import android.content.Context;
import android.widget.ImageView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.eclip.periodico.R;
import com.squareup.picasso.Picasso;

/**
 * Created by eclip on 01/12/2017.
 */

public class ImgUtils {

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
                .placeholder(mDrawableBuilder.build(name, ColorGenerator.MATERIAL.getColor(name.substring(0,1))))
                .error(mDrawableBuilder.build(name, ColorGenerator.MATERIAL.getColor(name.substring(0,1))))
                .into(image);
    }

    static public void loadImgName (Context context, ImageView image, String name){

        TextDrawable.IBuilder mDrawableBuilder = TextDrawable.builder()
                .beginConfig()
                .height(60)
                .width(300)
                .withBorder(4)
                .toUpperCase()
                .endConfig()
                .roundRect(5);

        Picasso.with(context)
                .load("noURL")
                .placeholder(mDrawableBuilder.build(name, ColorGenerator.MATERIAL.getColor(name.substring(0,1))))
                .error(mDrawableBuilder.build(name, ColorGenerator.MATERIAL.getColor(name.substring(0,1))))
                .into(image);
    }

    static public void loadImgHeader (Context context, ImageView image, String src){
        Picasso.with(context)
                .load(src)
                .placeholder(R.drawable.ic_photo_camera_purple_24dp)
                .error(R.drawable.ic_photo_camera_purple_24dp)
                .into(image);
    }
}
