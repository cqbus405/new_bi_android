package com.boostinsider.android.campaigns;

import com.boostinsider.android.data.campaign.Campaign;
import com.boostinsider.android.data.campaign.source.CampaignDataSource;
import com.boostinsider.android.data.campaign.source.CampaignRepository;
import com.boostinsider.android.data.user.source.UserRepository;

import java.util.ArrayList;

/**
 * Created by qiongchen on 4/24/16.
 */
public class CampaignsPresenter implements CampaignsContract.Presenter {

    private final CampaignsContract.View mView;

    private final CampaignRepository mRepository;

    private final UserRepository userRepository;

    public CampaignsPresenter(CampaignsContract.View mView, CampaignRepository mRepository, UserRepository userRepository) {
        this.mView = mView;
        this.mRepository = mRepository;
        this.userRepository = userRepository;

        mView.setPresenter(this);
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void loadCampaigns(int from, final boolean isFirstTimeOrRefresh) {
        if (!isFirstTimeOrRefresh) {
            mView.updateFooterView(true);
        }

        mRepository.getCampaigns(from, new CampaignDataSource.GetCampaignsCallback() {
            @Override
            public void onGetCampaignSuccessful(ArrayList<Campaign> campaigns) {
                mView.updateFooterView(false);
                mView.displayCampaigns(campaigns, isFirstTimeOrRefresh);
            }

            @Override
            public void onGetCampaignFailed(String error) {
                mView.updateFooterView(false);
                if (error.equals("500 Internal Server Error")) {
                    userRepository.removeUser();
                    mView.showLoginPage();
                } else {
                    mView.showToast(error);
                }
            }
        });
    }

    @Override
    public void addToMyCampaign(Campaign campaign) {
        mView.showCampaignDetail(campaign);
    }

    @Override
    public void openCampaignDetail(Campaign campaign) {
        mView.showCampaignDetail(campaign);
    }

    @Override
    public void start() {

    }
}
