package com.boostinsider.android.data.campaign.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.boostinsider.android.data.campaign.Campaign;
import com.boostinsider.android.data.campaign.Photo;
import com.boostinsider.android.data.campaign.source.CampaignDataSource;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by qiongchen on 6/5/16.
 */
public class CampaignLocalDataSource implements CampaignDataSource {

    private static CampaignLocalDataSource sInstance = null;

    private CampaignDbHelper mDbHelper;

    private SQLiteDatabase mDb;

    public static CampaignLocalDataSource getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new CampaignLocalDataSource(context);
        }
        return sInstance;
    }

    private CampaignLocalDataSource(Context context) {
        mDbHelper = new CampaignDbHelper(context);
        mDb = mDbHelper.getWritableDatabase();
    }

    @Override
    public void getCampaigns(int from, GetCampaignsCallback campaignsCallback) {

    }

    @Override
    public Campaign getCampaign() {
        Campaign campaign = null;

        try {
            String[] projection = {
                    CampaignPersistenceContract.CampaignEntry.COLUMN_NAME_CAMPAIGN_ID,
                    CampaignPersistenceContract.CampaignEntry.COLUMN_NAME_TITLE,
                    CampaignPersistenceContract.CampaignEntry.COLUMN_NAME_REMAIN_BUDGET,
                    CampaignPersistenceContract.CampaignEntry.COLUMN_NAME_DESCRIPTION,
                    CampaignPersistenceContract.CampaignEntry.COLUMN_NAME_TYPE,
                    CampaignPersistenceContract.CampaignEntry.COLUMN_NAME_INSTRUCTION,
                    CampaignPersistenceContract.CampaignEntry.COLUMN_NAME_NOTE,
                    CampaignPersistenceContract.CampaignEntry.COLUMN_NAME_EARN_TYPE,
                    CampaignPersistenceContract.CampaignEntry.COLUMN_NAME_PRODUCT_LINK,
                    CampaignPersistenceContract.CampaignEntry.COLUMN_NAME_PHOTOS,
            };

            Cursor cursor = mDb.query(CampaignPersistenceContract.CampaignEntry.TABLE_NAME, projection, null, null, null, null, null);

            if (cursor != null && cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    int campaignId = cursor.getInt(cursor.getColumnIndexOrThrow(CampaignPersistenceContract.CampaignEntry.COLUMN_NAME_CAMPAIGN_ID));
                    String title = cursor.getString(cursor.getColumnIndexOrThrow(CampaignPersistenceContract.CampaignEntry.COLUMN_NAME_TITLE));
                    String remainBudget = cursor.getString(cursor.getColumnIndexOrThrow(CampaignPersistenceContract.CampaignEntry.COLUMN_NAME_REMAIN_BUDGET));
                    String description = cursor.getString(cursor.getColumnIndexOrThrow(CampaignPersistenceContract.CampaignEntry.COLUMN_NAME_DESCRIPTION));
                    String type = cursor.getString(cursor.getColumnIndexOrThrow(CampaignPersistenceContract.CampaignEntry.COLUMN_NAME_TYPE));
                    String instruction = cursor.getString(cursor.getColumnIndexOrThrow(CampaignPersistenceContract.CampaignEntry.COLUMN_NAME_INSTRUCTION));

                    String note = cursor.getString(cursor.getColumnIndexOrThrow(CampaignPersistenceContract.CampaignEntry.COLUMN_NAME_NOTE));
                    String[] countryArray;
                    if (note.contains(", ")) {
                        String[] countries = note.substring(1, note.length() - 1).split(", ");
                        countryArray = countries;
                    } else {
                        String[] countries = {
                                note.substring(1, note.length() - 1)
                        };
                        countryArray = countries;
                    }

                    String earnType = cursor.getString(cursor.getColumnIndexOrThrow(CampaignPersistenceContract.CampaignEntry.COLUMN_NAME_EARN_TYPE));
                    String productLink = cursor.getString(cursor.getColumnIndexOrThrow(CampaignPersistenceContract.CampaignEntry.COLUMN_NAME_PRODUCT_LINK));

                    String photos = cursor.getString(cursor.getColumnIndexOrThrow(CampaignPersistenceContract.CampaignEntry.COLUMN_NAME_PHOTOS));
                    JSONArray jsonArray1 = new JSONArray(photos);
                    ArrayList<Photo> photos1 = new ArrayList<>();
                    for (int i = 0; i < jsonArray1.length(); i++) {
                        JSONObject jsonObject = jsonArray1.getJSONObject(i);
                        Photo photo = new Photo(jsonObject.getString("image_url"), jsonObject.getString("thumbnail"));
                        photos1.add(photo);
                    }

                    campaign = new Campaign(title, description, instruction, productLink, null, null, null, remainBudget, null, false, countryArray, type, earnType, photos1, campaignId);
                }
            }

            if (cursor != null) {
                cursor.close();
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return campaign;
    }

    @Override
    public void saveCampaign(Campaign campaign) {
        try {
            ContentValues values = new ContentValues();
            values.put(CampaignPersistenceContract.CampaignEntry._ID, 1);
            values.put(CampaignPersistenceContract.CampaignEntry.COLUMN_NAME_CAMPAIGN_ID, campaign.getId());
            values.put(CampaignPersistenceContract.CampaignEntry.COLUMN_NAME_TITLE, campaign.getName());
            values.put(CampaignPersistenceContract.CampaignEntry.COLUMN_NAME_REMAIN_BUDGET, campaign.getBudgetLeftDisplay());
            values.put(CampaignPersistenceContract.CampaignEntry.COLUMN_NAME_DESCRIPTION, campaign.getDescription());
            values.put(CampaignPersistenceContract.CampaignEntry.COLUMN_NAME_TYPE, campaign.getTypeDisplay());
            values.put(CampaignPersistenceContract.CampaignEntry.COLUMN_NAME_INSTRUCTION, campaign.getInstruction());
            values.put(CampaignPersistenceContract.CampaignEntry.COLUMN_NAME_NOTE, Arrays.toString(campaign.getCountries()));
            values.put(CampaignPersistenceContract.CampaignEntry.COLUMN_NAME_EARN_TYPE, campaign.getConversionName());
            values.put(CampaignPersistenceContract.CampaignEntry.COLUMN_NAME_PRODUCT_LINK, campaign.getUrl());
            values.put(CampaignPersistenceContract.CampaignEntry.COLUMN_NAME_PHOTOS, new Gson().toJson(campaign.getPhotos()));

            mDb.insertWithOnConflict(CampaignPersistenceContract.CampaignEntry.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        } catch (IllegalStateException e) {

        }
    }

    @Override
    public void removeCampaign() {
        mDb.execSQL("DELETE FROM " + CampaignPersistenceContract.CampaignEntry.TABLE_NAME);
    }
}
