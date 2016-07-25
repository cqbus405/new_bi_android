package com.boostinsider.android.comments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.boostinsider.android.R;
import com.boostinsider.android.data.comment.source.CommentRepository;
import com.boostinsider.android.data.comment.source.local.CommentLocalDataSource;
import com.boostinsider.android.data.comment.source.remote.CommentRemoteDataSource;
import com.boostinsider.android.util.ActivityUtils;
import com.boostinsider.android.util.Constants;

/**
 * Created by qiongchen on 7/6/16.
 */
public class CommentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comments_act);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        actionBar.setDisplayShowTitleEnabled(false);
        ActivityUtils.setStatusBarColor(this);

        CommentsFragment fragment = (CommentsFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (fragment == null) {
            fragment = CommentsFragment.newInstance(getIntent().getIntExtra(Constants.KEY_CAMPAIGN_ID, -1));
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.contentFrame);
        }

        new CommentsPresenter(fragment,
                CommentRepository.getInstance(CommentLocalDataSource.getInstance(), CommentRemoteDataSource.getInstance())
        );
    }
}
