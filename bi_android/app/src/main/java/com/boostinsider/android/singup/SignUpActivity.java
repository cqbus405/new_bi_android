package com.boostinsider.android.singup;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.boostinsider.android.R;
import com.boostinsider.android.data.user.source.UserRepository;
import com.boostinsider.android.data.user.source.local.UserLocalDataSource;
import com.boostinsider.android.data.user.source.remote.UserRemoteDataSource;
import com.boostinsider.android.util.ActivityUtils;

/**
 * Created by qiongchen on 4/26/16.
 */
public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_act);

        // Making notification bar transparent
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        SignUpFragment signUpFragment = (SignUpFragment) getSupportFragmentManager().findFragmentById(R.id.signUpContentFrame);
        if (signUpFragment == null) {
            signUpFragment = SignUpFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), signUpFragment, R.id.signUpContentFrame);
        }

        new SignUpPresenter(UserRepository.getInstance(UserLocalDataSource.getInstance(), UserRemoteDataSource.getInstance()), signUpFragment);

        if (savedInstanceState != null) {

        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
