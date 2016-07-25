package com.boostinsider.android.data.other;

import com.google.gson.annotations.Expose;

/**
 * Created by qiongchen on 7/1/16.
 */
public class LoginInfo {

    @Expose
    private String email;

    @Expose
    private String password;

    public LoginInfo(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
