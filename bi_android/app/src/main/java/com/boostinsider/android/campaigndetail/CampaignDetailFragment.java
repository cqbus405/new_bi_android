package com.boostinsider.android.campaigndetail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.boostinsider.android.R;
import com.boostinsider.android.comments.CommentsActivity;
import com.boostinsider.android.data.campaign.Campaign;
import com.boostinsider.android.util.Constants;
import com.boostinsider.android.util.Font;
import com.boostinsider.android.util.ToastUtils;

import java.util.Arrays;

/**
 * Created by qiongchen on 6/6/16.
 */
public class CampaignDetailFragment extends Fragment implements CampaignDetailContract.View {

    private Campaign campaign;

    private ViewPager mViewPager;

    private ImageAdapter mAdapter;

    private CampaignDetailContract.Presenter mPresenter;

    private TextView mTitle, mBudgetRemain, mEarn, mDescription, mType, mInstruction, mNote, mlink, mEarnType, mGo, mCopyContent, mProductionLink;
    private TextView mTitle2, mTitle3, mTitle4, mTitle5;
    private TextView mRemainingBudget;
    private TextView mImageNumber;

    private Button mCopy;

    private ImageView mTypeIcon;

    private int imageCount;

    public static CampaignDetailFragment newInstance(Campaign campaign) {

        Bundle args = new Bundle();
        args.putSerializable(Constants.KEY_CAMPAIGN, campaign);

        CampaignDetailFragment fragment = new CampaignDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        campaign = (Campaign) getArguments().getSerializable(Constants.KEY_CAMPAIGN);
        campaign = campaign == null ? mPresenter.getCampaign() : campaign;

        mAdapter = new ImageAdapter(campaign.getPhotos(), getContext());
        imageCount = campaign.getPhotos().size();

        mPresenter.removeCampaign();

        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.campaign_detail_frag, container, false);

        mImageNumber = (TextView) view.findViewById(R.id.viewpager_item_indicator);
        mImageNumber.setText("1/" + imageCount);

        mViewPager = (ViewPager) view.findViewById(R.id.detail_view_pager);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mImageNumber.setText(position + 1 + "/" + imageCount);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewPager.setAdapter(mAdapter);

        mTitle = (TextView) view.findViewById(R.id.detail_title);
        mTitle.setText(campaign.getName());

        mBudgetRemain = (TextView) view.findViewById(R.id.detail_budget_remain);
        mBudgetRemain.setText("$" + campaign.getBudgetLeftDisplay());

//        mEarn = (TextView) view.findViewById(R.id.detail_earn);
//        if (campaign.getCpcDisplay().equals("0.00")) {
//            mEarn.setText("$" + campaign.getCpiDisplay());
//        } else {
//            mEarn.setText("$" + campaign.getCpcDisplay());
//        }

        mEarnType = (TextView) view.findViewById(R.id.detail_earn_type);
        mEarnType.setText("Per " + campaign.getConversionName());

        mDescription = (TextView) view.findViewById(R.id.detail_description_content);
        mDescription.setText(Html.fromHtml(campaign.getDescription()));

        mProductionLink = (TextView) view.findViewById(R.id.detail_production_link);
        mProductionLink.setText(Html.fromHtml("Learn more about this product <font color='#19BC9C'>here</font>."));
        mProductionLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.checkProduction(campaign.getUrl());
            }
        });

        mType = (TextView) view.findViewById(R.id.detail_type_content);
        mType.setText(campaign.getTypeDisplay());

        mTypeIcon = (ImageView) view.findViewById(R.id.detail_type_icon);
        switch (campaign.getTypeDisplay()) {
            case "iOS app":
                mTypeIcon.setImageResource(R.drawable.ic_apple_gray_24dp);
                break;

            case "Android app":
                mTypeIcon.setImageResource(R.drawable.ic_android_black_18dp);
                break;

            case "Website":
                mTypeIcon.setImageResource(R.drawable.ic_laptop_black_18dp);
                break;

            case "App":
                mTypeIcon.setImageResource(R.drawable.ic_smartphone_black_18dp);
                break;

            default:
                break;
        }

        mInstruction = (TextView) view.findViewById(R.id.detail_instructions_content);
        mInstruction.setText(Html.fromHtml(campaign.getInstruction()));

        mNote = (TextView) view.findViewById(R.id.detail_note_content);
        if (campaign.getCountries().length != 0) {
            String countries = Arrays.toString(campaign.getCountries());
            mNote.setText(Html.fromHtml("Only clicks from <b>" + countries.substring(1, countries.length() - 1) + "</b> will be credited."));
        }

//        mGo = (TextView) view.findViewById(R.id.detail_link_title);
//        mlink = (TextView) view.findViewById(R.id.detail_link_content);
//        mCopyContent = (TextView) view.findViewById(R.id.detail_copy_content);

        mCopy = (Button) view.findViewById(R.id.detail_copy_btn);
        if (campaign.isLocked()) {
            mCopy.setText(R.string.request_campaign);
        } else {
            mCopy.setText(R.string.start_campaign);
        }

        mTitle2 = (TextView) view.findViewById(R.id.detail_description_title);
        mTitle3 = (TextView) view.findViewById(R.id.detail_type_title);
        mTitle4 = (TextView) view.findViewById(R.id.detail_instructions_title);
        mTitle5 = (TextView) view.findViewById(R.id.detail_note_title);

        mRemainingBudget = (TextView) view.findViewById(R.id.detail_remaining_budget);

        setFont();

        return view;
    }

//    @Override
//    public void onPause() {
//        super.onPause();
//
//        mPresenter.saveCampaign(campaign);
//    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_campaign_detail, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.menu_share:
//                mPresenter.shareCampaign(campaign.getUrl());
//                return true;

            case R.id.menu_comments:
                mPresenter.saveCampaign(campaign);
                mPresenter.displayComments(campaign.getId());
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void setPresenter(CampaignDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showProduction(String url) {
        if (url != null && !url.isEmpty()) {
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                getActivity().startActivity(intent);
            } catch (android.content.ActivityNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            ToastUtils.showToast(getContext(), R.string.production_unavailable);
        }
    }

    @Override
    public void showShareOption(String msg) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, msg);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    @Override
    public void showComments(int campaignId) {
        Intent intent = new Intent(getActivity(), CommentsActivity.class);
        intent.putExtra(Constants.KEY_CAMPAIGN_ID, campaignId);
        getActivity().startActivity(intent);
    }

    private void setFont() {
        Font.setAveniLig(mTitle, getContext());
        Font.setAveniLig(mTitle2, getContext());
        Font.setAveniLig(mTitle3, getContext());
        Font.setAveniLig(mTitle4, getContext());
        Font.setAveniLig(mTitle5, getContext());
        Font.setAveniLig(mBudgetRemain, getContext());

        Font.setOpenSansRegular(mDescription, getContext());
        Font.setOpenSansRegular(mType, getContext());
        Font.setOpenSansRegular(mInstruction, getContext());
        Font.setOpenSansRegular(mNote, getContext());
        Font.setOpenSansRegular(mRemainingBudget, getContext());
        Font.setOpenSansRegular(mProductionLink, getContext());

        Font.setOpenSansLight(mImageNumber, getContext());
    }
}
