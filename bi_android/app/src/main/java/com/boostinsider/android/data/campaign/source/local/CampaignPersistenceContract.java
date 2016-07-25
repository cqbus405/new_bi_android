package com.boostinsider.android.data.campaign.source.local;

import android.provider.BaseColumns;

/**
 * Created by qiongchen on 7/10/16.
 */
public class CampaignPersistenceContract {

    public CampaignPersistenceContract() {
    }

    public static abstract class CampaignEntry implements BaseColumns {
        public static final String TABLE_NAME = "campaigndetail";
        public static final String COLUMN_NAME_CAMPAIGN_ID = "campaignid";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_REMAIN_BUDGET = "remainbudget";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_TYPE = "type";
        public static final String COLUMN_NAME_INSTRUCTION = "instruction";
        public static final String COLUMN_NAME_NOTE = "note";
        public static final String COLUMN_NAME_EARN_TYPE = "earntype";
        public static final String COLUMN_NAME_PRODUCT_LINK = "productlink";
        public static final String COLUMN_NAME_PHOTOS = "photos";
    }
}
