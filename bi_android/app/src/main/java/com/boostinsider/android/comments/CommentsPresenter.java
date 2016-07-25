package com.boostinsider.android.comments;

import com.boostinsider.android.data.comment.Comment;
import com.boostinsider.android.data.comment.source.CommentDataSource;
import com.boostinsider.android.data.comment.source.CommentRepository;

import java.util.ArrayList;

/**
 * Created by qiongchen on 7/6/16.
 */
public class CommentsPresenter implements CommentsContract.Presenter {

    private final CommentsContract.View view;

    private final CommentRepository repository;

    public CommentsPresenter(CommentsContract.View view, CommentRepository repository) {
        this.view = view;
        this.repository = repository;

        view.setPresenter(this);
    }

    @Override
    public void loadComments(int campaignId, int from, final boolean isFirstTimeOrRefresh) {
        if (!isFirstTimeOrRefresh) {
            view.updateFooterView(true);
        }

        repository.getComments(campaignId, from, new CommentDataSource.GetCommentsCallback() {
            @Override
            public void onGetCommentsSuccessful(ArrayList<Comment> comments) {
                view.updateFooterView(false);
                view.displayComments(comments, isFirstTimeOrRefresh);
            }

            @Override
            public void onGetCommentsFailed(String errMsg) {
                view.updateFooterView(false);
                view.showToast(errMsg);
            }
        });
    }

    @Override
    public void start() {

    }
}
