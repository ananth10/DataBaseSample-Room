package com.ananth.databasesample.adapter;

import android.databinding.BindingAdapter;
import android.text.TextUtils;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;

public class ImageBindingAdapter {

    @BindingAdapter({"bind:imageView"})
    public static void loadImage(ImageView imageView, String url) {
        System.out.println("ImageUrl 1:"+url);
        if(!TextUtils.isEmpty(url)) {
                File file = new File(url);
                Picasso.with(imageView.getContext()).load(file).resize(200, 200).into(imageView);

        }
    }
}
