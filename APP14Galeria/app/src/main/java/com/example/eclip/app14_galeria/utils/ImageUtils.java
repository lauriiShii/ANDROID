package com.example.eclip.app14_galeria.utils;

import android.content.Context;
import android.widget.ImageView;

import com.example.eclip.app14_galeria.R;
import com.squareup.picasso.Picasso;

/**
 * Created by eclip on 01/12/2017.
 */

public class ImageUtils {

    static public void loadImg (Context context, ImageView image, String src){
        Picasso.with(context)
                .load(src)
                .placeholder(R.drawable.ic_photo_camera_purple_24dp)
                .error(R.drawable.ic_photo_camera_purple_24dp)
                .into(image);
    }
}
