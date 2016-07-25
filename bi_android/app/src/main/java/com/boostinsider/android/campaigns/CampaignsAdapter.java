package com.boostinsider.android.campaigns;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.boostinsider.android.R;
import com.boostinsider.android.data.campaign.Campaign;
import com.boostinsider.android.util.Constants;
import com.boostinsider.android.util.Font;
import com.boostinsider.android.util.ScreenUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by qiongchen on 6/5/16.
 */
public class CampaignsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Campaign> campaigns;

    private Context mContext;

    private Activity mActivity;

    public OnItemClickListener mOnItemClickListener;

    private View mFooterView;

    public CampaignsAdapter(Context mContext, Activity activity) {
        this.mContext = mContext;
        this.mActivity = activity;
        campaigns = new ArrayList<>();
    }

    private class CampaignsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView mCoverImage;

        TextView mTitle;

        TextView mDescription;

//        ImageButton mShare;
//
//        ImageButton mComments;

//        ImageButton mLike;

        public CampaignsViewHolder(View itemView) {
            super(itemView);

            mCoverImage = (ImageView) itemView.findViewById(R.id.campaign_img);

            mTitle = (TextView) itemView.findViewById(R.id.campaign_title);

            mDescription = (TextView) itemView.findViewById(R.id.campaign_description);

//            mShare = (ImageButton) itemView.findViewById(R.id.campaign_share);
//
//            mComments = (ImageButton) itemView.findViewById(R.id.campaign_comment);

//            mLike = (ImageButton) itemView.findViewById(R.id.campaign_like);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(itemView, campaigns.get(getAdapterPosition()));
            }
        }
    }

    private class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == Constants.TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_campaign, null);
            return new CampaignsViewHolder(view);
        } else if (viewType == Constants.TYPE_FOOTER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.footerview, null);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            mFooterView = view;
            return new FooterViewHolder(view);
        }
        throw new RuntimeException("There is no type that matches the type " + viewType + ". Make sure your using types are correct.");
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return Constants.TYPE_FOOTER;
        } else {
            return Constants.TYPE_ITEM;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CampaignsViewHolder) {
            holder.setIsRecyclable(false);

            String coverImage = campaigns.get(position).getImageDisplay();
            String title = campaigns.get(position).getName();
            String description = campaigns.get(position).getDescription();

            if (coverImage != null) {
                Picasso.with(mContext)
                        .load(coverImage)
                        .into(((CampaignsViewHolder) holder).mCoverImage);
            }

            ((CampaignsViewHolder) holder).mTitle.setText(title);
            ((CampaignsViewHolder) holder).mDescription.setText(Html.fromHtml(description));

            Font.setAveniLig(((CampaignsViewHolder) holder).mTitle, mContext);
            Font.setOpenSansRegular(((CampaignsViewHolder) holder).mDescription, mContext);

//            ((CampaignsViewHolder) holder).mShare.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });
//
//            ((CampaignsViewHolder) holder).mComments.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });

//            ((CampaignsViewHolder) holder).mLike.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });
        }
    }

    @Override
    public int getItemCount() {
        return campaigns.size() + 1;
    }

    public void setAdapter(ArrayList<Campaign> campaigns) {
        this.campaigns.clear();
        this.campaigns.addAll(campaigns);
        this.notifyDataSetChanged();
    }

    public void updateAdapter(ArrayList<Campaign> campaigns) {
        this.campaigns.addAll(campaigns);
        this.notifyItemRangeInserted(this.campaigns.size(), campaigns.size());
    }

    public interface OnItemClickListener {

        void onItemClick(View view, Campaign campaign);
    }

    public void setOnItemClickListener(final OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void setFooterVisible(boolean isVisible) {
        if (mFooterView != null) {
            mFooterView.setVisibility(isVisible ? View.VISIBLE : View.INVISIBLE);
        }
    }

    public int getPosition() {
        return campaigns.size();
    }
}
