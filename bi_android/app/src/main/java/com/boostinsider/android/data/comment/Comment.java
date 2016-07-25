package com.boostinsider.android.data.comment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by qiongchen on 6/13/16.
 */
public class Comment {

    @Expose
    private String content;

    @Expose
    @SerializedName("user_name")
    private String userName;

    @Expose
    @SerializedName("user_avatar")
    private String userAvatar;

    @Expose
    @SerializedName("created_display")
    private String created;

    @Expose
    private String image;

    public Comment(String content, String userName, String userAvatar, String created, String image) {
        this.content = content;
        this.userName = userName;
        this.userAvatar = userAvatar;
        this.created = created;
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
