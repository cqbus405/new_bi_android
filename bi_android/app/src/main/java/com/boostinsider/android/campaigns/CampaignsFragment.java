package com.boostinsider.android.campaigns;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.boostinsider.android.R;
import com.boostinsider.android.campaigndetail.CampaignDetailActivity;
import com.boostinsider.android.data.campaign.Campaign;
import com.boostinsider.android.login.LoginActivity;
import com.boostinsider.android.util.Constants;
import com.boostinsider.android.util.ToastUtils;

import java.util.ArrayList;

/**
 * Created by qiongchen on 4/24/16.
 */
public class CampaignsFragment extends Fragment implements CampaignsContract.View {

    private CampaignsContract.Presenter mPresenter;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    private RecyclerView mRecyclerView;

    private CampaignsAdapter mAdapter;

    private boolean mIsScrolling;

    private int mLastVisibleItem;

    public static CampaignsFragment newInstance() {

        Bundle args = new Bundle();

        CampaignsFragment fragment = new CampaignsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mIsScrolling = false;

        mPresenter.loadCampaigns(0, true);

        mAdapter = new CampaignsAdapter(getContext(), getActivity());
        mAdapter.setOnItemClickListener(new CampaignsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Campaign campaign) {
                mPresenter.openCampaignDetail(campaign);
            }
        });

        // handle configuration changes
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.campaigns_frag, container, false);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_widget);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(false);
                mPresenter.loadCampaigns(0, true);
            }
        });

        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());

        mRecyclerView = (RecyclerView) view.findViewById(R.id.campaigns_recyclerview);
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (mAdapter != null && !mIsScrolling && newState == RecyclerView.SCROLL_STATE_IDLE && mLastVisibleItem == mAdapter.getItemCount() - 1) {
                    mIsScrolling = true;
                    mPresenter.loadCampaigns(mAdapter.getPosition(), false);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                mIsScrolling = false;
                mLastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
                mAdapter.setFooterVisible(mIsScrolling);
            }
        });
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mRecyclerView.setAdapter(null);
    }

    @Override
    public void setPresenter(CampaignsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void displayCampaigns(ArrayList<Campaign> campaigns, boolean isFirstTimeOrRefresh) {
        if (mAdapter != null && isFirstTimeOrRefresh) {
            mAdapter.setAdapter(campaigns);
        } else if (mAdapter != null && !isFirstTimeOrRefresh) {
            mAdapter.updateAdapter(campaigns);
        }
    }

    @Override
    public void updateFooterView(boolean isVisible) {
        if (mAdapter != null) {
            if (isVisible) {
                mAdapter.setFooterVisible(true);
            } else {
                mAdapter.setFooterVisible(false);
            }
        }
    }

    @Override
    public void showToast(String message) {
        ToastUtils.showToast(getContext(), message);
    }

    @Override
    public void showCampaignDetail(Campaign campaign) {
        Intent intent = new Intent(getActivity(), CampaignDetailActivity.class);
        intent.putExtra(Constants.KEY_CAMPAIGN, campaign);
        startActivity(intent);
    }

    @Override
    public void showLoginPage() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        getActivity().startActivity(intent);
        getActivity().finish();
    }
}
