package com.lt.integrate.frame.img;

import android.content.Context;
import android.widget.ImageView;
import com.bumptech.glide.Glide;


/**
 * Created by Administrator on 2017/5/10.
 */
public class ImageLoader {

    public static void display(Context context, ImageView imageView, String url, int placeholder, int error) {
        if(imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url).placeholder(placeholder)
                .error(error).crossFade().into(imageView);
    }
    public static void display(Context context, ImageView imageView, String url, int placeholder) {
        if(imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url).placeholder(placeholder)
                .crossFade().into(imageView);
    }
    public static void display(Context context, ImageView imageView, String url) {
        if(imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url).into(imageView);
    }

}
