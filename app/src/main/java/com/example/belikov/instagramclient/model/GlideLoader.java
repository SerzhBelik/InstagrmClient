package com.example.belikov.instagramclient.model;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class GlideLoader {

    private Context context;

    public GlideLoader(Context context) {
        this.context = context;
    }

    public GlideLoader() {

    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void loadImage(String url, ImageView imageView) {
        Glide
                .with(context)
                .load(url)
                .into(imageView);
    }
}