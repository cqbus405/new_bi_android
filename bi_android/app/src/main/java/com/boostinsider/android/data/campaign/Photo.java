package com.boostinsider.android.data.campaign;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by qiongchen on 6/5/16.
 */
public class Photo implements Serializable {

    @SerializedName("image_url")
    @Expose
    String imageUrl;

    @Expose
    String thumbnail;

    public Photo(String imageUrl, String thumbnail) {
        this.imageUrl = imageUrl;
        this.thumbnail = thumbnail;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
