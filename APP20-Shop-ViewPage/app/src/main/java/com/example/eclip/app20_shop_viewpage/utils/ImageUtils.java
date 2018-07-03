package com.example.eclip.app20_shop_viewpage.utils;

import android.content.Context;
import android.widget.ImageView;

import com.example.eclip.app20_shop_viewpage.R;
import com.squareup.picasso.Picasso;

/**
 * Created by eclip on 01/12/2017.
 */

public class ImageUtils {

    static public void loadImg (Context context, ImageView image, String src){
        Picasso.with(context)
                .load(src)
                .placeholder(R.drawable.ic_extension_green_24dp)
                .error(R.drawable.ic_extension_green_24dp)
                .into(image);
    }
}
