package com.boostinsider.android.data.campaign.source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by qiongchen on 7/10/16.
 */
public class CampaignDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "Campaign.db";

    private static final String TEXT_TYPE = " TEXT";

    private static final String INTEGER_TYPE = " INTEGER";

    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + CampaignPersistenceContract.CampaignEntry.TABLE_NAME + " (" +
                    CampaignPersistenceContract.CampaignEntry._ID + INTEGER_TYPE + " PRIMARY KEY," +
                    CampaignPersistenceContract.CampaignEntry.COLUMN_NAME_CAMPAIGN_ID + INTEGER_TYPE + COMMA_SEP +
                    CampaignPersistenceContract.CampaignEntry.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
                    CampaignPersistenceContract.CampaignEntry.COLUMN_NAME_REMAIN_BUDGET + TEXT_TYPE + COMMA_SEP +
                    CampaignPersistenceContract.CampaignEntry.COLUMN_NAME_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
                    CampaignPersistenceContract.CampaignEntry.COLUMN_NAME_TYPE + TEXT_TYPE + COMMA_SEP +
                    CampaignPersistenceContract.CampaignEntry.COLUMN_NAME_INSTRUCTION + TEXT_TYPE + COMMA_SEP +
                    CampaignPersistenceContract.CampaignEntry.COLUMN_NAME_NOTE + TEXT_TYPE + COMMA_SEP +
                    CampaignPersistenceContract.CampaignEntry.COLUMN_NAME_EARN_TYPE + TEXT_TYPE + COMMA_SEP +
                    CampaignPersistenceContract.CampaignEntry.COLUMN_NAME_PRODUCT_LINK + TEXT_TYPE + COMMA_SEP +
                    CampaignPersistenceContract.CampaignEntry.COLUMN_NAME_PHOTOS + TEXT_TYPE +
                    " )";

    public CampaignDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
