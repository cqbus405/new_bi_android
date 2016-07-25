package com.boostinsider.android.singup;

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

import com.boostinsider.android.R;
import com.boostinsider.android.login.LoginActivity;
import com.boostinsider.android.util.ToastUtils;

/**
 * Created by qiongchen on 4/26/16.
 */
public class SignUpFragment extends Fragment implements SignUpContract.View {

    private SignUpContract.Presenter mSignUpPresenter;

    private EditText mEmailEdtiText;

    private EditText mPasswordEditText;

    private EditText mRePasswordEditText;

    private Button mSignUpButton;

    private Button mLogInButton;

    private ProgressBar mProgressBar;

    public static SignUpFragment newInstance() {

        Bundle args = new Bundle();

        SignUpFragment fragment = new SignUpFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setPresenter(SignUpContract.Presenter presenter) {
        mSignUpPresenter = presenter;
    }

    @Override
    public void showLogin() {
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void showLoadingIndicator(boolean isLoading) {
        if (isLoading) {
            mProgressBar.setVisibility(View.VISIBLE);
        } else {
            mProgressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void showErrorMessage(int type, String message) {
        switch (type) {
            case 0:
                mEmailEdtiText.setError(message);
                break;
            case 1:
                mPasswordEditText.setError(message);
                break;
            case 2:
                mRePasswordEditText.setError(message);
                break;
            default:
                break;
        }
    }

    @Override
    public void showMessage(String message) {
        ToastUtils.showToast(getContext(), message);
    }

    @Override
    public void showCampaigns() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.signup_frag, container, false);

        mEmailEdtiText = (EditText) root.findViewById(R.id.emailEditText);
        mPasswordEditText = (EditText) root.findViewById(R.id.passwordEditText);
        mRePasswordEditText = (EditText) root.findViewById(R.id.rePasswordEditText);

        mSignUpButton = (Button) root.findViewById(R.id.signUpButton);
        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmailEdtiText.getText().toString();
                String password = mPasswordEditText.getText().toString();
                String rePassword = mRePasswordEditText.getText().toString();

                mSignUpPresenter.doSignUp(email, password, rePassword);
            }
        });

        mLogInButton = (Button) root.findViewById(R.id.loginButton);
        mLogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSignUpPresenter.backToLogin();
            }
        });

        mProgressBar = (ProgressBar) root.findViewById(R.id.progressBar);

        return root;
    }
}
