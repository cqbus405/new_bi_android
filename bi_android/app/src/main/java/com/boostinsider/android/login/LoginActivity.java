package com.boostinsider.android.login;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.boostinsider.android.R;
import com.boostinsider.android.campaigns.CampaignsActivity;
import com.boostinsider.android.data.user.source.UserRepository;
import com.boostinsider.android.data.user.source.local.UserLocalDataSource;
import com.boostinsider.android.data.user.source.remote.UserRemoteDataSource;
import com.boostinsider.android.util.ActivityUtils;
import com.pixplicity.easyprefs.library.Prefs;

/**
 * Created by qiongchen on 4/24/16.
 */
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Prefs.getString("token", null) != null) {
            Intent intent = new Intent(this, CampaignsActivity.class);
            startActivity(intent);
            finish();
        }

        setContentView(R.layout.login_act);

        // Making notification bar transparent
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        //Set up fragment.
        LoginFragment loginFragment = (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.loginContentFrame);
        if (loginFragment == null) {
            loginFragment = LoginFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), loginFragment, R.id.loginContentFrame);
        }

        //Create the presenter
        new LoginPresenter(loginFragment, UserRepository.getInstance(UserLocalDataSource.getInstance(), UserRemoteDataSource.getInstance()));

        //Load previous saved state, if available.
        if (savedInstanceState != null) {

        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
