package com.boostinsider.android.campaigns;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.boostinsider.android.util.CircleTransform;
import com.boostinsider.android.R;
import com.boostinsider.android.data.campaign.source.CampaignRepository;
import com.boostinsider.android.data.campaign.source.local.CampaignLocalDataSource;
import com.boostinsider.android.data.campaign.source.remote.CampaignRemoteDataSource;
import com.boostinsider.android.data.user.source.UserRepository;
import com.boostinsider.android.data.user.source.local.UserLocalDataSource;
import com.boostinsider.android.data.user.source.remote.UserRemoteDataSource;
import com.boostinsider.android.util.ActivityUtils;
import com.boostinsider.android.util.Constants;
import com.boostinsider.android.util.PermissionUtils;
import com.boostinsider.android.util.ToastUtils;
import com.pixplicity.easyprefs.library.Prefs;
import com.squareup.picasso.Picasso;

/**
 * Created by qiongchen on 4/24/16.
 */
public class CampaignsActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.campaigns_act);

        //Set up toolbar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setDisplayShowTitleEnabled(false);
        ActivityUtils.setImageTranslucent(this);

        //Set up navigation drawer.
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setStatusBarBackground(R.color.colorPrimaryDark);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        //Set up fragment.
        CampaignsFragment campaignsFragment = (CampaignsFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (campaignsFragment == null) {
            //Create the fragment.
            campaignsFragment = CampaignsFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), campaignsFragment, R.id.contentFrame);
        }

        //Create the presenter.
        new CampaignsPresenter(campaignsFragment,
                CampaignRepository.getInstance(CampaignLocalDataSource.getInstance(getApplicationContext()), CampaignRemoteDataSource.getInstance()),
                UserRepository.getInstance(UserLocalDataSource.getInstance(), UserRemoteDataSource.getInstance()));

        //Load the previously saved state, if available.
        if (savedInstanceState != null) {

        }

        PermissionUtils.getPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case Constants.MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission granted
                } else {
                    // permission denied
                }
                return;

            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //Open the navigation drawer when the home icon is selected from the toolbar.
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        View headView = navigationView.getHeaderView(0);
        ImageView avatar = (ImageView) headView.findViewById(R.id.nav_header_avatar);
        TextView name = (TextView) headView.findViewById(R.id.nav_header_name);

        String avatarUrl = Prefs.getString(Constants.KEY_AVATAR, null);
        if (avatarUrl != null || !avatarUrl.isEmpty()) {
            Picasso.with(this)
                    .load(Constants.IMAGE_BASE_URL + avatarUrl)
                    .transform(new CircleTransform())
                    .into(avatar);
        }

        String nameStr = Prefs.getString(Constants.KEY_NAME, null);
        if (nameStr != null || !nameStr.isEmpty()) {
            name.setText(nameStr);
        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
//                    case R.id.campaigns_menu_item:
//                        ToastUtils.showToast(CampaignsActivity.this, "Campaigns selected.");
//                        break;

                    case R.id.my_campaigns_menu_item:
                        ToastUtils.showToast(CampaignsActivity.this, "My campaigns selected.");
                        break;

                    case R.id.withdraw_menu_item:
                        ToastUtils.showToast(CampaignsActivity.this, "Withdraw selected.");
                        break;

                    default:
                        break;
                }
                // Close the navigation drawer when an item is selected.
                item.setChecked(true);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }
}
