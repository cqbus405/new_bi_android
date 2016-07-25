package com.boostinsider.android.campaigndetail;

import com.boostinsider.android.BasePresenter;
import com.boostinsider.android.BaseView;
import com.boostinsider.android.data.campaign.Campaign;

/**
 * Created by qiongchen on 6/6/16.
 */
public interface CampaignDetailContract {

    interface View extends BaseView<Presenter> {

        void showProduction(String url);

        void showShareOption(String msg);

        void showComments(int campaignId);
    }

    interface Presenter extends BasePresenter {

        void checkProduction(String url);

        void shareCampaign(String msg);

        void displayComments(int campaignId);

        void saveCampaign(Campaign campaign);

        Campaign getCampaign();

        void removeCampaign();
    }
}
