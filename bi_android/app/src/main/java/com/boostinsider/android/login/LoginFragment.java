package com.boostinsider.android.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.boostinsider.android.R;
import com.boostinsider.android.campaigns.CampaignsActivity;
import com.boostinsider.android.singup.SignUpActivity;
import com.boostinsider.android.util.ToastUtils;

/**
 * Created by qiongchen on 4/24/16.
 */
public class LoginFragment extends Fragment implements LoginContract.View {

    private LoginContract.Presenter mLoginPresenter;

    private EditText mEmailEditText;

    private EditText mPasswordEditText;

    private TextView mForgotPasswordTextView;

    private TextView mSignUpTextView;

    private Button mLoginButton;

    private ProgressBar mLoadingProgressBar;

    public static LoginFragment newInstance() {
        
        Bundle args = new Bundle();

        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mLoginPresenter = presenter;
    }

    @Override
    public void setLoadingIndicator(boolean isLoading) {
        if (isLoading) {
            mLoadingProgressBar.setVisibility(View.VISIBLE);
        } else {
            mLoadingProgressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void showMessage(String message) {
        ToastUtils.showToast(getActivity(), message);
    }

    @Override
    public void showSignUp() {
        Intent intent = new Intent(getContext(), SignUpActivity.class);
        startActivity(intent);
    }

    @Override
    public void showCampaigns() {
        Intent intent = new Intent(getActivity(), CampaignsActivity.class);
        getActivity().startActivity(intent);
        getActivity().finish();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.login_frag, container, false);

        mEmailEditText = (EditText) root.findViewById(R.id.emailEditText);
        mPasswordEditText = (EditText) root.findViewById(R.id.passwordEditText);

        mForgotPasswordTextView = (TextView) root.findViewById(R.id.forgotPassword);
        mForgotPasswordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoginPresenter.doFindPassword();
            }
        });

        mSignUpTextView = (TextView) root.findViewById(R.id.signUp);
        mSignUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoginPresenter.doSignUp();
            }
        });

        //Set up login button.
        mLoginButton = (Button) root.findViewById(R.id.loginButton);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmailEditText.getText().toString();
                String password = mPasswordEditText.getText().toString();
                mLoginPresenter.doLogin(email, password);
            }
        });

        mLoadingProgressBar = (ProgressBar) root.findViewById(R.id.loginProgressBar);

        return root;
    }
}
