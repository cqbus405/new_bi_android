package com.boostinsider.android.data.comment.source;

import com.boostinsider.android.data.comment.Comment;

import java.util.ArrayList;

/**
 * Created by qiongchen on 6/13/16.
 */
public interface CommentDataSource {

    interface GetCommentsCallback {

        void onGetCommentsSuccessful(ArrayList<Comment> comments);

        void onGetCommentsFailed(String errMsg);
    }

    void getComments(int campaignId, int from, GetCommentsCallback callback);
}
