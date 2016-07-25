package com.boostinsider.android.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by qiongchen on 4/24/16.
 */
public class ToastUtils {

    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void showToast(Context context, int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
    }
}
