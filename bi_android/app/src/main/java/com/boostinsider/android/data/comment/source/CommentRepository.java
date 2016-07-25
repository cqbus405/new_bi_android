package com.boostinsider.android.data.comment.source;

import com.boostinsider.android.data.comment.Comment;
import com.boostinsider.android.data.comment.source.local.CommentLocalDataSource;
import com.boostinsider.android.data.comment.source.remote.CommentRemoteDataSource;

import java.util.ArrayList;

/**
 * Created by qiongchen on 6/13/16.
 */
public class CommentRepository implements CommentDataSource {

    private final CommentLocalDataSource localDataSource;

    private final CommentRemoteDataSource remoteDataSource;

    private static CommentRepository INSTANCE = null;

    private CommentRepository(CommentLocalDataSource localDataSource, CommentRemoteDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    public static CommentRepository getInstance(CommentLocalDataSource localDataSource, CommentRemoteDataSource remoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new CommentRepository(localDataSource, remoteDataSource);
        }

        return INSTANCE;
    }

    @Override
    public void getComments(int campaignId, int from, final GetCommentsCallback callback) {
        remoteDataSource.getComments(campaignId, from, new GetCommentsCallback() {
            @Override
            public void onGetCommentsSuccessful(ArrayList<Comment> comments) {
                callback.onGetCommentsSuccessful(comments);
            }

            @Override
            public void onGetCommentsFailed(String errMsg) {
                callback.onGetCommentsFailed(errMsg);
            }
        });
    }
}
