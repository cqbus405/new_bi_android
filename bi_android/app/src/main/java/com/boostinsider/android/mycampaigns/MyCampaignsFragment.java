package com.boostinsider.android.mycampaigns;

import android.support.v4.app.Fragment;

/**
 * Created by qiongchen on 7/20/16.
 */
public class MyCampaignsFragment extends Fragment implements MyCampaignsContract.View {

    private MyCampaignsContract.Presenter mMyCampaignsPresenter;

    @Override
    public void setPresenter(MyCampaignsContract.Presenter presenter) {
        mMyCampaignsPresenter = presenter;
    }
}
