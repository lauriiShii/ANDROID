package com.example.eclip.app22_bottomsheet.utils;

import android.content.Context;
import android.widget.ImageView;

import com.example.eclip.app22_bottomsheet.R;
import com.squareup.picasso.Picasso;

/**
 * Created by eclip on 01/12/2017.
 */

public class ImgUtils {

    static public void loadImg (Context context, ImageView image, String src){
        Picasso.with(context)
                .load(src)
                .placeholder(R.drawable.ic_healing_green_24dp)
                .error(R.drawable.ic_healing_green_24dp)
                .into(image);
    }
}
