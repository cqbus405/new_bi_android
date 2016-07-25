//package com.boostinsider.android.onboarding;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v4.view.ViewPager;
//import android.text.Html;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import com.boostinsider.android.R;
//import com.boostinsider.android.login.LoginActivity;
//import com.pixplicity.easyprefs.library.Prefs;
//
///**
// * Created by qiongchen on 10/5/15.
// */
//public class OnBoardingFragment extends Fragment {
//
//    private OnboardingAdapter mAdapter;
//
//    private ViewPager mViewPager;
//
//    private LinearLayout mDotsLayout;
//
//    private TextView[] mDots;
//
//    private Button mSkip, mNext;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_onboarding_page, container, false);
//
//        mViewPager = (ViewPager) view.findViewById(R.id.onboarding_vp);
//        mDotsLayout = (LinearLayout) view.findViewById(R.id.layoutDots);
//        mNext = (Button) view.findViewById(R.id.btn_next);
//        mSkip = (Button) view.findViewById(R.id.btn_skip);
//
//        addDots(0);
//
//        mAdapter = new OnboardingAdapter(getContext());
//        mViewPager.setAdapter(mAdapter);
//        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                addDots(position);
//
//                if (position == 2) {
//                    mNext.setText(getString(R.string.start));
//                    mSkip.setVisibility(View.GONE);
//                } else {
//                    mNext.setText(getString(R.string.next));
//                    mSkip.setVisibility(View.VISIBLE);
//                }
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
//
//        mNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int current = getItem(+1);
//                if (current < 3) {
//                    mViewPager.setCurrentItem(current);
//                } else {
//                    launchHomeScreen();
//                }
//            }
//        });
//
//        mSkip.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                launchHomeScreen();
//            }
//        });
//
//        return view;
//    }
//
//    private void addDots(int currentPage) {
//        mDots = new TextView[3];
//
//        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
//        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);
//
//        mDotsLayout.removeAllViews();
//
//        for (int i = 0; i < mDots.length; i++) {
//            mDots[i] = new TextView(getContext());
//            mDots[i].setText(Html.fromHtml("&#8226;"));
//            mDots[i].setTextSize(35);
//            mDots[i].setTextColor(colorsInactive[currentPage]);
//            mDotsLayout.addView(mDots[i]);
//        }
//
//        if (mDots.length > 0) {
//            mDots[currentPage].setTextColor(colorsActive[currentPage]);
//        }
//    }
//
//    private int getItem(int i) {
//        return mViewPager.getCurrentItem() + i;
//    }
//
//    private void launchHomeScreen() {
//        Prefs.putBoolean("first_time", false);
//        Intent intent = new Intent(getActivity(), LoginActivity.class);
//        startActivity(intent);
//        getActivity().finish();
//    }
//}
