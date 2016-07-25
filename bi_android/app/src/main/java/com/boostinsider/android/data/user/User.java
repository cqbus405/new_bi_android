package com.boostinsider.android.data.user;

/**
 * Created by qiongchen on 4/25/16.
 */
public class User {

    private final String email;

    private final String password;

    private final String token;

    private final String paypal;

    private final String name;

    private final float balance;

    private final int id;

    private final String avatar;

    public User(String email, String password, String token, String paypal, String name, float balance, int id, String avatar) {
        this.email = email;
        this.password = password;
        this.token = token;
        this.paypal = paypal;
        this.name = name;
        this.balance = balance;
        this.id = id;
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }

    public String getPaypal() {
        return paypal;
    }

    public String getName() {
        return name;
    }

    public float getBalance() {
        return balance;
    }

    public int getId() {
        return id;
    }

    public String getAvatar() {
        return avatar;
    }
}
