package com.boostinsider.android.data.comment.source.remote;

import com.boostinsider.android.RetrofitAPI.ServerHelper;
import com.boostinsider.android.data.comment.Comments;
import com.boostinsider.android.data.comment.source.CommentDataSource;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by qiongchen on 6/13/16.
 */
public class CommentRemoteDataSource implements CommentDataSource {

    private static CommentRemoteDataSource INSTANCE = null;

    public static CommentRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CommentRemoteDataSource();
        }

        return INSTANCE;
    }

    private CommentRemoteDataSource() {
    }

    @Override
    public void getComments(int campaignId, int from, final GetCommentsCallback callback) {
        ServerHelper.getInstance().getComments(campaignId, from, new Callback<Comments>() {
            @Override
            public void success(Comments comments, Response response) {
                int status = comments.getStatus();

                if (status == 200) {
                    callback.onGetCommentsSuccessful(comments.getData().getComments());
                } else {
                    callback.onGetCommentsFailed(comments.getMessage());
                }
            }

            @Override
            public void failure(RetrofitError error) {
                callback.onGetCommentsFailed(error.getMessage());
            }
        });
    }
}