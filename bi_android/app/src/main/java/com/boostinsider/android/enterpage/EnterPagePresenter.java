package com.boostinsider.android.enterpage;

/**
 * Created by qiongchen on 7/25/16.
 */
public class EnterPagePresenter implements EnterPageContract.Presenter {

    private final EnterPageContract.View mView;

    public EnterPagePresenter(EnterPageContract.View view) {
        this.mView = view;

        mView.setPresenter(this);
    }

    @Override
    public void enterLoginPage() {
        mView.showLoginPage();
    }

    @Override
    public void enterRegisterPage() {
        mView.showRegisterPage();
    }

    @Override
    public void start() {

    }
}
