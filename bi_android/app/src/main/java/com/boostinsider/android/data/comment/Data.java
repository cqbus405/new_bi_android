package com.boostinsider.android.data.comment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by qiongchen on 6/13/16.
 */
public class Data {

    @Expose
    private int total;

    @Expose
    @SerializedName("rows")
    private ArrayList<Comment> comments;

    public Data(int total, ArrayList<Comment> comments) {
        this.total = total;
        this.comments = comments;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }
}
