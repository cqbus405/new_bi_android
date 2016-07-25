package com.boostinsider.android.data.campaign.source.remote;

import com.boostinsider.android.RetrofitAPI.ServerHelper;
import com.boostinsider.android.data.campaign.Campaign;
import com.boostinsider.android.data.campaign.Campaigns;
import com.boostinsider.android.data.campaign.source.CampaignDataSource;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by qiongchen on 6/5/16.
 */
public class CampaignRemoteDataSource implements CampaignDataSource {

    private static CampaignRemoteDataSource sInstance = null;

    public static CampaignRemoteDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new CampaignRemoteDataSource();
        }
        return sInstance;
    }

    private CampaignRemoteDataSource() {
    }

    @Override
    public void getCampaigns(int from, final GetCampaignsCallback callback) {
        ServerHelper.getInstance().getCampaigns(from, new Callback<Campaigns>() {
            @Override
            public void success(Campaigns campaigns, Response response) {
                int status = campaigns.getStatus();
                if (status == 200) {
                    ArrayList<Campaign> campaignList = campaigns.getData().getCampaigns();
                    callback.onGetCampaignSuccessful(campaignList);
                } else {
                    String errMsg = campaigns.getMessage();
                    callback.onGetCampaignFailed(errMsg);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                callback.onGetCampaignFailed(error.getMessage());
            }
        });
    }

    @Override
    public Campaign getCampaign() {
        return null;
    }

    @Override
    public void saveCampaign(Campaign campaign) {

    }

    @Override
    public void removeCampaign() {

    }
}
