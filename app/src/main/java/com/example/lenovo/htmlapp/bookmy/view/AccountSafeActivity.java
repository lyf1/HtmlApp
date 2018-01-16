package com.example.lenovo.htmlapp.bookmy.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.lenovo.htmlapp.R;
import com.zhy.autolayout.AutoLinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AccountSafeActivity extends AppCompatActivity {

    @BindView(R.id.as_cancel)
    AutoLinearLayout mCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_safe);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.as_cancel)
    public void onViewClicked() {
        SharedPreferences sp = getSharedPreferences("qquser", MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit().clear();
        edit.putBoolean("islogin",false);
        edit.commit();
        AccountSafeActivity.this.finish();
    }
}
