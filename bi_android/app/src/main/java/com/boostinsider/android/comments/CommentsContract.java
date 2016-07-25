package com.boostinsider.android.comments;

import com.boostinsider.android.BasePresenter;
import com.boostinsider.android.BaseView;
import com.boostinsider.android.data.comment.Comment;

import java.util.ArrayList;

/**
 * Created by qiongchen on 7/6/16.
 */
public interface CommentsContract {

    interface View extends BaseView<Presenter> {

        void updateFooterView(boolean isVisible);

        void displayComments(ArrayList<Comment> comments, boolean isFirstTimeOrRefresh);

        void showToast(String msg);
    }

    interface Presenter extends BasePresenter {

        void loadComments(int campaignId, int from, boolean isFirstTimeOrRefresh);
    }
}
