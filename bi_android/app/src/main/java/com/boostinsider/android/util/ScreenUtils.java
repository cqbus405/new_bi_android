package com.boostinsider.android.util;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Point;
import android.view.Display;

/**
 * Created by qiongchen on 6/5/16.
 */
public class ScreenUtils {

    public static int getScreenWidth(Activity activity, boolean fixed) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        int i = activity.getResources().getConfiguration().orientation;
        Point size = new Point();
        display.getSize(size);
        int height;
        if (fixed && i == Configuration.ORIENTATION_LANDSCAPE)
            height = size.y;
        else
            height = size.x;
        return height;
    }

}
