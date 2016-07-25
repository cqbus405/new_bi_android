//package com.boostinsider.android.onboarding;
//
//import android.content.Intent;
//import android.graphics.Color;
//import android.os.Build;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.view.WindowManager;
//
//import com.boostinsider.android.R;
//import com.boostinsider.android.login.LoginActivity;
//import com.boostinsider.android.util.ActivityUtils;
//import com.pixplicity.easyprefs.library.Prefs;
//
///**
// * Created by qiongchen on 10/5/15.
// */
//public class OnboardingActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        if (!Prefs.getBoolean("first_time", true)) {
//            Prefs.putBoolean("first_time", false);
//            Intent intent = new Intent(this, LoginActivity.class);
//            startActivity(intent);
//            finish();
//        }
//
//        // Making notification bar transparent
//        if (Build.VERSION.SDK_INT < 16) {
//            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        } else if (Build.VERSION.SDK_INT >= 21) {
//            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
//        }
//
//        setContentView(R.layout.onboarding_act);
//
//        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), new OnBoardingFragment(), R.id.onboarding_container);
//    }
//}
