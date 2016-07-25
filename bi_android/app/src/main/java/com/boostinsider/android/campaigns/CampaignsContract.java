package com.boostinsider.android.campaigns;

import com.boostinsider.android.BasePresenter;
import com.boostinsider.android.BaseView;
import com.boostinsider.android.data.campaign.Campaign;

import java.util.ArrayList;

/**
 * Created by qiongchen on 4/24/16.
 */
public interface CampaignsContract {

    interface View extends BaseView<Presenter> {

        void displayCampaigns(ArrayList<Campaign> campaignses, boolean isFirstTimeOrRefresh);

        void updateFooterView(boolean isVisible);

        void showToast(String message);

        void showCampaignDetail(Campaign campaign);

        void showLoginPage();
    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void loadCampaigns(int from, boolean isFirstTimeOrRefresh);

        void addToMyCampaign(Campaign campaign);

        void openCampaignDetail(Campaign campaign);
    }
}
