package com.boostinsider.android.data.user.source.local;

import com.boostinsider.android.data.user.User;
import com.boostinsider.android.data.user.source.UserDataSource;
import com.boostinsider.android.util.Constants;
import com.pixplicity.easyprefs.library.Prefs;

/**
 * Created by qiongchen on 4/26/16.
 */
public class UserLocalDataSource implements UserDataSource {

    private static UserLocalDataSource INSTANCE = null;

    private UserLocalDataSource() {

    }

    public static UserLocalDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserLocalDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void doLogin(String username, String password, LoginOrSignUpCallback callback) {

    }

    @Override
    public void doSignUp(String username, String password, LoginOrSignUpCallback callback) {

    }

    @Override
    public void saveUser(User user) {
        Prefs.putString(Constants.KEY_EMAIL, user.getEmail());
        Prefs.putString(Constants.KEY_PASSWORD, user.getPassword());
        Prefs.putString(Constants.KEY_TOKEN, user.getToken());
        Prefs.putString(Constants.KEY_PAYPAL, user.getPaypal());
        Prefs.putString(Constants.KEY_NAME, user.getName());
        Prefs.putFloat(Constants.KEY_BALANCE, user.getBalance());
        Prefs.putInt(Constants.KEY_ID, user.getId());
        Prefs.putString(Constants.KEY_AVATAR, user.getAvatar());
    }

    @Override
    public void removeUser() {
        Prefs.putString(Constants.KEY_EMAIL, null);
        Prefs.putString(Constants.KEY_PASSWORD, null);
        Prefs.putString(Constants.KEY_TOKEN, null);
        Prefs.putString(Constants.KEY_PAYPAL, null);
        Prefs.putString(Constants.KEY_NAME, null);
        Prefs.putFloat(Constants.KEY_BALANCE, 0);
        Prefs.putInt(Constants.KEY_ID, 0);
        Prefs.putString(Constants.KEY_AVATAR, null);
    }
}
