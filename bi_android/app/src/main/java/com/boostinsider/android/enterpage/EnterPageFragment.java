package com.boostinsider.android.enterpage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.boostinsider.android.R;
import com.boostinsider.android.login.LoginActivity;
import com.boostinsider.android.singup.SignUpActivity;
import com.boostinsider.android.util.Font;

/**
 * Created by qiongchen on 7/25/16.
 */
public class EnterPageFragment extends Fragment implements EnterPageContract.View {

    private EnterPageContract.Presenter mPresenter;

    public static EnterPageFragment newInstance() {

        Bundle args = new Bundle();

        EnterPageFragment fragment = new EnterPageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.enter_frag, container, false);

        Button loginBtn = (Button) view.findViewById(R.id.ep_btn2);
        Button signUpBtn = (Button) view.findViewById(R.id.ep_btn1);
        TextView welcomeTxt = (TextView) view.findViewById(R.id.ep_txt);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.enterLoginPage();
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.enterRegisterPage();
            }
        });

        Font.setMontserrat_regular(welcomeTxt, getContext());

        return view;
    }

    @Override
    public void showLoginPage() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        getActivity().startActivity(intent);
    }

    @Override
    public void showRegisterPage() {
        Intent intent = new Intent(getActivity(), SignUpActivity.class);
        getActivity().startActivity(intent);
    }

    @Override
    public void setPresenter(EnterPageContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
