package com.onemorebit.readthis.manager;

import android.databinding.BindingAdapter;
import android.net.Uri;
import android.support.v7.widget.AppCompatImageView;
import com.bumptech.glide.Glide;

/**
 * Created by Euro on 2/5/16 AD.
 */
public class DataBindingAdapter {
    @BindingAdapter({"android:src"})
    public static void setImageDrawable(AppCompatImageView imageView, Uri uri){
        if(uri != null) {
            Glide.with(imageView.getContext()).load(uri).into(imageView);
        }
    }
}
