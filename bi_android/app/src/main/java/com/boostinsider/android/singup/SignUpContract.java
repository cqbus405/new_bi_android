package com.boostinsider.android.singup;

import com.boostinsider.android.BasePresenter;
import com.boostinsider.android.BaseView;

/**
 * Created by qiongchen on 4/26/16.
 */
public interface SignUpContract {

    interface View extends BaseView<Presenter> {

        void showLogin();

        void showLoadingIndicator(boolean isLoading);

        void showErrorMessage(int type, String message);

        void showMessage(String message);

        void showCampaigns();
    }

    interface Presenter extends BasePresenter {

        void backToLogin();

        void doSignUp(String email, String password, String rePassword);
    }
}
