package com.boostinsider.android.enterpage;

import com.boostinsider.android.BasePresenter;
import com.boostinsider.android.BaseView;

/**
 * Created by qiongchen on 7/25/16.
 */
public interface EnterPageContract {

    interface View extends BaseView<Presenter> {

        void showLoginPage();

        void showRegisterPage();
    }

    interface Presenter extends BasePresenter {

        void enterLoginPage();

        void enterRegisterPage();
    }
}
