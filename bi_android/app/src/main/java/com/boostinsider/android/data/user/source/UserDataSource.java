package com.boostinsider.android.data.user.source;

import com.boostinsider.android.data.user.User;

/**
 * Created by qiongchen on 4/25/16.
 */
public interface UserDataSource {

    interface LoginOrSignUpCallback {

        void onLoginOrSignUpSuccessful(User user);

        void onLoginOrSignUpFailed(String error);
    }

    void doLogin(String username, String password, LoginOrSignUpCallback callback);

    void doSignUp(String username, String password, LoginOrSignUpCallback callback);

    void saveUser(User user);

    void removeUser();
}
