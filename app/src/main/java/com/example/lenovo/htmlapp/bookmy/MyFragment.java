package com.example.lenovo.htmlapp.bookmy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.htmlapp.R;
import com.example.lenovo.htmlapp.bookmy.view.AccountSafeActivity;
import com.example.lenovo.htmlapp.bookmy.view.LoginActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zhy.autolayout.AutoRelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by lenovo on 2018/1/13.
 */

public class MyFragment extends Fragment {


    @BindView(R.id.my_image_view)
    SimpleDraweeView myImageView;
    @BindView(R.id.m_account)
    AutoRelativeLayout mAccount;
    @BindView(R.id.m_bookshelf)
    AutoRelativeLayout mBookshelf;
    @BindView(R.id.m_read)
    AutoRelativeLayout mRead;

    @BindView(R.id.m_night)
    AutoRelativeLayout mNight;
    @BindView(R.id.m_setup)
    AutoRelativeLayout mSetup;
    @BindView(R.id.m_accountsafe)
    AutoRelativeLayout mAccountsafe;
    @BindView(R.id.m_opinion)
    AutoRelativeLayout mOpinion;
    @BindView(R.id.m_newaccount)
    AutoRelativeLayout mNewaccount;
    @BindView(R.id.m_mobile)
    AutoRelativeLayout mMobile;
    Unbinder unbinder;
    @BindView(R.id.m_login)
    Button mLogin;
    @BindView(R.id.m_nick)
    TextView mNick;
    private SharedPreferences sp;


    private String imageurl="https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2013524310,284427490&fm=200&gp=0.jpg";
    private boolean islogin;
    private String nick;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = getActivity().getSharedPreferences("qquser", MODE_PRIVATE);
        islogin = sp.getBoolean("islogin", false);
        imageurl = sp.getString("image", null);
        nick = sp.getString("nick", null);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_fragment, null);
        unbinder = ButterKnife.bind(this, view);

        if (islogin) {
            myImageView.setImageURI(Uri.parse(imageurl));
            mLogin.setVisibility(View.GONE);
            mNick.setVisibility(View.VISIBLE);
            mNick.setText(nick);

        }else if(islogin ==false){

            mNick.setVisibility(View.GONE);
            mLogin.setVisibility(View.VISIBLE);

            mNick.setText("立即登录");
        }

        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.m_account, R.id.m_bookshelf, R.id.m_read, R.id.m_night, R.id.m_setup, R.id.m_accountsafe, R.id.m_opinion, R.id.m_newaccount, R.id.m_mobile, R.id.m_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.m_account:
                break;
            case R.id.m_bookshelf:
                break;
            case R.id.m_read:
                break;
            case R.id.m_night:
                int mode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
                if (mode == Configuration.UI_MODE_NIGHT_YES) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                } else if (mode == Configuration.UI_MODE_NIGHT_NO) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                getActivity().recreate();
                break;
            case R.id.m_setup:
                break;
            case R.id.m_accountsafe:
                Intent intent2=new Intent(getActivity(), AccountSafeActivity.class);
                startActivity(intent2);
                break;
            case R.id.m_opinion:
                break;
            case R.id.m_newaccount:
                break;
            case R.id.m_mobile:
                break;
            case R.id.m_login:
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
        }
    }

}
