//package com.boostinsider.android.onboarding;
//
//import android.content.Context;
//import android.support.v4.view.PagerAdapter;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.boostinsider.android.R;
//
///**
// * Created by qiongchen on 10/5/15.
// */
//public class OnboardingAdapter extends PagerAdapter {
//
//    private LayoutInflater mLayoutInflater;
//
//    private Context mContext;
//
//    private int[] layouts = {
//        R.layout.onboarding_slide1,
//        R.layout.onboarding_slide2,
//        R.layout.onboarding_slide3
//    };
//
//    public OnboardingAdapter(Context mContext) {
//        this.mContext = mContext;
//    }
//
//    @Override
//    public Object instantiateItem(ViewGroup container, int position) {
//        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//        View view = mLayoutInflater.inflate(layouts[position], container, false);
//        container.addView(view);
//
//        return view;
//    }
//
//    @Override
//    public int getCount() {
//        return layouts.length;
//    }
//
//    @Override
//    public boolean isViewFromObject(View view, Object object) {
//        return view == object;
//    }
//
//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//        View view = (View) object;
//        container.removeView(view);
//    }
//}
