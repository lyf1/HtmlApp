package com.example.lenovo.htmlapp.bookshelf.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.lenovo.htmlapp.R;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowBookActivity extends AutoLayoutActivity {

        @BindView(R.id.webview)
        WebView webview;
        private String url;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_show_book);
            ButterKnife.bind(this);
            Intent intent = getIntent();
            url = intent.getStringExtra("url");
            setwebView();
        }

        @SuppressLint("WrongConstant")
        private void setwebView() {
            WebSettings setting = webview.getSettings();
            setting.setJavaScriptEnabled(true);//支持js
            setting.setDefaultTextEncodingName("utf-8");
            webview.setScrollBarStyle(0);

            webview.loadUrl(url);
        }
}
