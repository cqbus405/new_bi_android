package com.boostinsider.android.login;

import com.boostinsider.android.data.user.User;
import com.boostinsider.android.data.user.source.UserDataSource;
import com.boostinsider.android.data.user.source.UserRepository;

/**
 * Created by qiongchen on 4/24/16.
 */
public class LoginPresenter implements LoginContract.Presenter {

    private final LoginContract.View mLoginView;

    private final UserRepository mUserRepository;

    public LoginPresenter(LoginContract.View mLoginView, UserRepository mUserRepository) {
        this.mLoginView = mLoginView;
        this.mUserRepository = mUserRepository;

        mLoginView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void doLogin(String email, String password) {
        mLoginView.setLoadingIndicator(true);
        mUserRepository.doLogin(email, password, new UserDataSource.LoginOrSignUpCallback() {
            @Override
            public void onLoginOrSignUpSuccessful(User user) {
                mLoginView.setLoadingIndicator(false);
                mLoginView.showCampaigns();
            }

            @Override
            public void onLoginOrSignUpFailed(String error) {
                mLoginView.setLoadingIndicator(false);
                if (error != null) {
                    mLoginView.showMessage("The username or password is incorrect.");
                }
            }
        });
    }

    @Override
    public void doSignUp() {
        mLoginView.showSignUp();
    }

    @Override
    public void doFindPassword() {
    }
}
