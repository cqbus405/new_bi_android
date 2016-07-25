package com.boostinsider.android.data.other;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by qiongchen on 7/6/16.
 */
public class CommentInfo {

    @Expose
    @SerializedName("campaign_id")
    private Integer campaignId;

    @Expose
    private Integer from;

    @Expose
    private Integer size;

    public CommentInfo(Integer campaignId, Integer from, Integer size) {
        this.campaignId = campaignId;
        this.from = from;
        this.size = size;
    }
}
