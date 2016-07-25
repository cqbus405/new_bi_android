package com.boostinsider.android.util;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

/**
 * Created by qiongchen on 9/8/15.
 */
public class Font {

    public static void setMontserrat_regular(TextView textview, Context context) {
        textview.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/montserrat_regular.ttf"));
    }

    public static void setMontserrat_bold(TextView textView, Context context) {
        textView.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/montserrat_bold.ttf"));
    }

    public static void setOpenSansLight(TextView textview, Context context) {
        textview.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/OpenSans-Light.ttf"));
    }

    public static void setOpenSansRegular(TextView textView, Context context) {
        textView.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/OpenSans-Regular.ttf"));
    }

    public static void setAveniLig(TextView textView, Context context) {
        textView.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/Avenir-Book.ttf"));
    }
}