package com.knight.taiepizoo.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.knight.taiepizoo.GlideApp;


public class ViewUtils {


    public static void loadImage(ImageView imageCourse, String url) {
        if (TextUtils.isEmpty(url)) return;
        GlideApp.with(imageCourse.getContext()).load(url).into(imageCourse);
    }

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }


}