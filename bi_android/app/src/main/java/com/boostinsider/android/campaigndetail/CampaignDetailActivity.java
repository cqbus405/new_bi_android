package com.boostinsider.android.campaigndetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.boostinsider.android.R;
import com.boostinsider.android.data.campaign.Campaign;
import com.boostinsider.android.data.campaign.source.CampaignRepository;
import com.boostinsider.android.data.campaign.source.local.CampaignLocalDataSource;
import com.boostinsider.android.data.campaign.source.remote.CampaignRemoteDataSource;
import com.boostinsider.android.util.ActivityUtils;
import com.boostinsider.android.util.Constants;

/**
 * Created by qiongchen on 6/6/16.
 */
public class CampaignDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.campaign_detail_act);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        actionBar.setDisplayShowTitleEnabled(false);
        ActivityUtils.setImageTranslucent(this);

        Campaign campaign = (Campaign) getIntent().getSerializableExtra(Constants.KEY_CAMPAIGN);

        CampaignDetailFragment fragment = (CampaignDetailFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (fragment == null) {
            fragment = CampaignDetailFragment.newInstance(campaign);
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.contentFrame);
        }

        new CampaignDetailPresenter(fragment,
                CampaignRepository.getInstance(CampaignLocalDataSource.getInstance(getApplicationContext()),
                        CampaignRemoteDataSource.getInstance()));
    }
}