package com.boostinsider.android.data.campaign;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

/**
 * Created by qiongchen on 6/5/16.
 */
public class Data {

    @Expose
    int total;

    @Expose
    ArrayList<Campaign> campaigns;

    public Data(int total, ArrayList<Campaign> campaigns) {
        this.total = total;
        this.campaigns = campaigns;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ArrayList<Campaign> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(ArrayList<Campaign> campaigns) {
        this.campaigns = campaigns;
    }
}
