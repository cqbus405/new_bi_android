package com.boostinsider.android.data.campaign;

import com.google.gson.annotations.Expose;

/**
 * Created by qiongchen on 6/5/16.
 */
public class Campaigns {

    @Expose
    int status;

    @Expose
    Data data;

    @Expose
    String message;

    public Campaigns(int status, Data data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
