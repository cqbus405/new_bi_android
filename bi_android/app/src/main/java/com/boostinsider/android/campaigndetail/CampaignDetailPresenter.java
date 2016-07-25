package com.boostinsider.android.campaigndetail;

import com.boostinsider.android.data.campaign.Campaign;
import com.boostinsider.android.data.campaign.source.CampaignRepository;

/**
 * Created by qiongchen on 6/6/16.
 */
public class CampaignDetailPresenter implements CampaignDetailContract.Presenter {

    private final CampaignDetailContract.View mView;

    private final CampaignRepository mCampaignRepository;

    public CampaignDetailPresenter(CampaignDetailContract.View mView, CampaignRepository campaignRepository) {
        this.mView = mView;
        this.mCampaignRepository = campaignRepository;

        this.mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void checkProduction(String url) {
        mView.showProduction(url);
    }

    @Override
    public void shareCampaign(String msg) {
        mView.showShareOption(msg);
    }

    @Override
    public void displayComments(int campaignId) {
        mView.showComments(campaignId);
    }

    @Override
    public void saveCampaign(Campaign campaign) {
        mCampaignRepository.saveCampaign(campaign);
    }

    @Override
    public Campaign getCampaign() {
        return mCampaignRepository.getCampaign();
    }

    @Override
    public void removeCampaign() {
        mCampaignRepository.removeCampaign();
    }
}
