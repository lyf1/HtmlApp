package com.example.lenovo.htmlapp;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.lenovo.htmlapp.bookhoom.view.ClassFragment;
import com.example.lenovo.htmlapp.bookmy.MyFragment;
import com.example.lenovo.htmlapp.bookshelf.view.bookfragment;
import com.example.lenovo.htmlapp.net.StatusBarUtil;
import com.hjm.bottomtabbar.BottomTabBar;
import com.zhy.autolayout.AutoLayoutActivity;


import butterknife.ButterKnife;


public class MainActivity extends AutoLayoutActivity {





    private BottomTabBar mb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        StatusBarUtil.setTransparent(MainActivity.this);
        StatusBarUtil.setColor(this,Color.parseColor("#2a79ce"));
        mb=(BottomTabBar)findViewById(R.id.m_bottom_tab_bar);
        mb.init(getSupportFragmentManager())
                .setImgSize(60, 60)
                .setFontSize(15)
                .setTabPadding(4, 4, 4)
                .setChangeColor(Color.BLUE, Color.DKGRAY)
                .addTabItem("书架",R.mipmap.a88, bookfragment.class)
                .addTabItem("分类",R.mipmap.a5j , ClassFragment.class)
                .addTabItem("我的",R.mipmap.ae_, MyFragment.class)
                .isShowDivider(false)
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name) {

                    }
                });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
