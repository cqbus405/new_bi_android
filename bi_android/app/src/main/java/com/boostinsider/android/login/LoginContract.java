package com.boostinsider.android.login;

import com.boostinsider.android.BasePresenter;
import com.boostinsider.android.BaseView;

/**
 * Created by qiongchen on 4/24/16.
 */
public interface LoginContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean isLoading);

        void showMessage(String message);

        void showSignUp();

        void showCampaigns();
    }

    interface Presenter extends BasePresenter {

        void doLogin(String email, String password);

        void doSignUp();

        void doFindPassword();
    }

}
