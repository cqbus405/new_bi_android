package com.boostinsider.android.data.user.source;

import com.boostinsider.android.data.user.User;

/**
 * Created by qiongchen on 4/25/16.
 */
public class UserRepository implements UserDataSource {

    private static UserRepository INSTANCE = null;

    private final UserDataSource mUserLocalDataSource;

    private final UserDataSource mUserRemoteDataSource;

    private UserRepository(UserDataSource mUserLocalDataSource, UserDataSource mUserRemoteDataSource) {
        this.mUserLocalDataSource = mUserLocalDataSource;
        this.mUserRemoteDataSource = mUserRemoteDataSource;
    }

    public static UserRepository getInstance(UserDataSource userLocalDataSource, UserDataSource userRemoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new UserRepository(userLocalDataSource, userRemoteDataSource);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void doLogin(String username, String password, final LoginOrSignUpCallback callback) {
        mUserRemoteDataSource.doLogin(username, password, new LoginOrSignUpCallback() {
            @Override
            public void onLoginOrSignUpSuccessful(User user) {
                callback.onLoginOrSignUpSuccessful(user);
                mUserLocalDataSource.saveUser(user);
            }

            @Override
            public void onLoginOrSignUpFailed(String error) {
                callback.onLoginOrSignUpFailed(error);
            }
        });
    }

    @Override
    public void doSignUp(String username, String password, final LoginOrSignUpCallback callback) {
        mUserRemoteDataSource.doSignUp(username, password, new LoginOrSignUpCallback() {
            @Override
            public void onLoginOrSignUpSuccessful(User user) {
                callback.onLoginOrSignUpSuccessful(user);
                mUserLocalDataSource.saveUser(user);
            }

            @Override
            public void onLoginOrSignUpFailed(String error) {
                callback.onLoginOrSignUpFailed(error);
            }
        });
    }

    @Override
    public void saveUser(User user) {

    }

    @Override
    public void removeUser() {
        mUserLocalDataSource.removeUser();
    }
}
