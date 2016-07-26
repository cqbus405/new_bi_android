package com.boostinsider.android.singup;

import com.boostinsider.android.data.user.User;
import com.boostinsider.android.data.user.source.UserDataSource;
import com.boostinsider.android.data.user.source.UserRepository;

/**
 * Created by qiongchen on 4/26/16.
 */
public class SignUpPresenter implements SignUpContract.Presenter {

    private enum Type {
        ZERO(0), ONE(1), TWO(2);

        private int value;

        Type(int value) {
            this.value = value;
        }
    }

    private final UserRepository mUserRepository;

    private final SignUpContract.View mSignUpView;

    public SignUpPresenter(UserRepository mUserRepository, SignUpContract.View mSignUpView) {
        this.mUserRepository = mUserRepository;
        this.mSignUpView = mSignUpView;

        mSignUpView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void doSignUp(String email, String password, String rePassword) {
        if (isValidInput(email, password, rePassword)) {
            mSignUpView.showLoadingIndicator(true);
            mUserRepository.doSignUp(email, password, new UserDataSource.LoginOrSignUpCallback() {
                @Override
                public void onLoginOrSignUpSuccessful(User user) {
                    mSignUpView.showLoadingIndicator(false);
                    mSignUpView.showCampaigns();
                }

                @Override
                public void onLoginOrSignUpFailed(String error) {
                    mSignUpView.showLoadingIndicator(false);
                    if (error != null) {
                        mSignUpView.showMessage(error);
                    }
                }
            });
        }
    }

    /**
     * TYPE 0: EMAIL
     * TYPE 1: PASSWORD
     * TYPE 2: REPASSWORD
     *
     * @param email
     * @param password
     * @param rePassword
     * @return
     */
    private boolean isValidInput(String email, String password, String rePassword) {
        if (email.trim() == null || email.trim().isEmpty()) {
            mSignUpView.showErrorMessage(Type.ZERO.value, "email can not be empty");
            return false;
        } else if (!email.trim().matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$")) {
            mSignUpView.showErrorMessage(Type.ZERO.value, "invalid email address");
            return false;
        } else if (password.trim() == null || password.trim().isEmpty()) {
            mSignUpView.showErrorMessage(Type.ONE.value, "password can not be empty");
            return false;
        } else if (password.trim().length() < 8) {
            mSignUpView.showErrorMessage(Type.ONE.value, "at least 8 characters");
            return false;
        } else if (!password.trim().equals(rePassword.trim())) {
            mSignUpView.showErrorMessage(Type.TWO.value, "passwords are not match");
            return false;
        } else {
            return true;
        }
    }
}
