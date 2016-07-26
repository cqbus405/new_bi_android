package com.boostinsider.android.enterpage;

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
import com.boostinsider.android.util.ActivityUtils;
import com.pixplicity.easyprefs.library.Prefs;

/**
 * Created by qiongchen on 7/25/16.
 */
public class EnterPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // Making notification bar transparent
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        super.onCreate(savedInstanceState);

        if (Prefs.getString("token", null) != null) {
            Intent intent = new Intent(this, CampaignsActivity.class);
            startActivity(intent);
            finish();
        }

        setContentView(R.layout.enter_act);

        EnterPageFragment enterPageFragment = (EnterPageFragment) getSupportFragmentManager().
                findFragmentById(R.id.fragmentContainer);

        if (enterPageFragment == null) {
            enterPageFragment = EnterPageFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), enterPageFragment, R.id.fragmentContainer);
        }

        new EnterPagePresenter(enterPageFragment);
    }
}
