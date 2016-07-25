package com.boostinsider.android.data.campaign.source;

import com.boostinsider.android.data.campaign.Campaign;
import com.boostinsider.android.data.campaign.source.local.CampaignLocalDataSource;
import com.boostinsider.android.data.campaign.source.remote.CampaignRemoteDataSource;

import java.util.ArrayList;

/**
 * Created by qiongchen on 6/5/16.
 */
public class CampaignRepository implements CampaignDataSource {

    private final CampaignLocalDataSource mLocalDataSource;

    private final CampaignRemoteDataSource mRemoteDataSource;

    private static CampaignRepository sInstance = null;

    private CampaignRepository(CampaignLocalDataSource mLocalDataSource, CampaignRemoteDataSource mRemoteDataSource) {
        this.mLocalDataSource = mLocalDataSource;
        this.mRemoteDataSource = mRemoteDataSource;
    }

    public static CampaignRepository getInstance(CampaignLocalDataSource campaignLocalDataSource, CampaignRemoteDataSource campaignRemoteDataSource) {
        if (sInstance == null) {
            sInstance = new CampaignRepository(campaignLocalDataSource, campaignRemoteDataSource);
        }
        return sInstance;
    }

    @Override
    public void getCampaigns(int from, final GetCampaignsCallback campaignsCallback) {
        mRemoteDataSource.getCampaigns(from, new GetCampaignsCallback() {
            @Override
            public void onGetCampaignSuccessful(ArrayList<Campaign> campaigns) {
                campaignsCallback.onGetCampaignSuccessful(campaigns);
            }

            @Override
            public void onGetCampaignFailed(String error) {
                campaignsCallback.onGetCampaignFailed(error);
            }
        });
    }

    @Override
    public Campaign getCampaign() {
        return mLocalDataSource.getCampaign();
    }

    @Override
    public void saveCampaign(Campaign campaign) {
        mLocalDataSource.saveCampaign(campaign);
    }

    @Override
    public void removeCampaign() {
        mLocalDataSource.removeCampaign();
    }
}
