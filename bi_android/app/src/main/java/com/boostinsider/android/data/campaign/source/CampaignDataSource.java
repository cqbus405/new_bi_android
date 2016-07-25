package com.boostinsider.android.data.campaign.source;

import com.boostinsider.android.data.campaign.Campaign;

import java.util.ArrayList;

/**
 * Created by qiongchen on 6/5/16.
 */
public interface CampaignDataSource {

    interface GetCampaignsCallback {

        void onGetCampaignSuccessful(ArrayList<Campaign> campaigns);

        void onGetCampaignFailed(String error);
    }

    void getCampaigns(int from, GetCampaignsCallback campaignsCallback);

    Campaign getCampaign();

    void saveCampaign(Campaign campaign);

    void removeCampaign();
}
