package com.boostinsider.android.data.comment.source.local;

import com.boostinsider.android.data.comment.source.CommentDataSource;

/**
 * Created by qiongchen on 6/13/16.
 */
public class CommentLocalDataSource implements CommentDataSource {

    private static CommentLocalDataSource INSTANCE = null;

    public static CommentLocalDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CommentLocalDataSource();
        }

        return INSTANCE;
    }

    private CommentLocalDataSource() {
    }

    @Override
    public void getComments(int campaignId, int from, GetCommentsCallback callback) {

    }
}
