package com.boostinsider.android.comments;

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
import com.boostinsider.android.data.comment.Comment;
import com.boostinsider.android.util.Constants;

import java.util.ArrayList;

/**
 * Created by qiongchen on 7/6/16.
 */
public class CommentsFragment extends Fragment implements CommentsContract.View {

    private CommentsContract.Presenter presenter;

    private RecyclerView mRecyclerView;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    private boolean isScrolling;

    private int lastVisibleItem;

    private CommentsAdapter adapter;

    private int campaignId;

    public static CommentsFragment newInstance(int campaignId) {
        Bundle args = new Bundle();
        args.putInt(Constants.KEY_CAMPAIGN_ID, campaignId);

        CommentsFragment fragment = new CommentsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        isScrolling = false;

        campaignId = getArguments().getInt(Constants.KEY_CAMPAIGN_ID);

        presenter.loadComments(campaignId, 0, true);

        adapter = new CommentsAdapter(getContext());

        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.comments_frag, container, false);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(false);
                presenter.loadComments(campaignId, 0, true);
            }
        });

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (adapter != null && !isScrolling && newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem == adapter.getItemCount() - 1) {
                    isScrolling = true;
                    presenter.loadComments(campaignId, adapter.getPosition(), false);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                isScrolling = false;
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                adapter.setFooterVisible(isScrolling);
            }
        });
        mRecyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mRecyclerView.setAdapter(null);
    }

    @Override
    public void setPresenter(CommentsContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void updateFooterView(boolean isVisible) {
        if (adapter != null) {
            if (isVisible) {
                adapter.setFooterVisible(true);
            } else {
                adapter.setFooterVisible(false);
            }
        }
    }

    @Override
    public void displayComments(ArrayList<Comment> comments, boolean isFirstTimeOrRefresh) {
        if (adapter != null && isFirstTimeOrRefresh) {
            adapter.setAdapter(comments);
        } else if (adapter != null && !isFirstTimeOrRefresh) {
            adapter.updateAdapter(comments);
        }
    }

    @Override
    public void showToast(String msg) {

    }
}
