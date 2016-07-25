package com.boostinsider.android.util;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;


/**
 * Created by qiongchen on 9/21/15.
 */
public class ItemDecoration extends RecyclerView.ItemDecoration {

    private int mSource;
    private int mSpace;
    private Context mContext;

    public ItemDecoration(int space, Context context, int source) {
        mContext = context;
        mSpace = dp2px(mContext, space);
        mSource = source;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        switch (mSource) {
            case 0:
                outRect.right = mSpace;
                outRect.top = mSpace;
                outRect.bottom = mSpace;
                if (parent.getChildAdapterPosition(view) == 0)
                    outRect.left = mSpace;
                break;
            case 1:
                outRect.bottom = 0;
                outRect.right = 0;
                outRect.left = 0;
                if (parent.getChildAdapterPosition(view) != 0)
                    outRect.top = mSpace;
                break;
        }
    }

    private int dp2px(Context context, int dp) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
