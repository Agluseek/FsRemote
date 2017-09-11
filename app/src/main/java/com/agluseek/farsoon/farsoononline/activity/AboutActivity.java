package com.agluseek.farsoon.farsoononline.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.agluseek.farsoon.farsoononline.R;

/**
 * @author Farsoon Wu'Ang
 *  关于界面
 */

public class AboutActivity extends AppCompatActivity {
    private WebView about_webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Toolbar bar = (Toolbar) findViewById(R.id.about_toolbar);

        if (bar != null) {
            setSupportActionBar(bar);
        }

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("联系我们");
        }

        about_webView = (WebView) findViewById(R.id.about_webView);
        WebSettings settings = about_webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        about_webView.setWebViewClient(new WebViewClient(){

        });
        about_webView.loadUrl("http://mp.weixin.qq.com/s/GzUE82Pqbo8pReiwWHGhzw");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                about_webView.clearCache(true);
                about_webView.clearHistory();
                about_webView.destroy();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onResume() {
        super.onResume();
        about_webView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        about_webView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        about_webView.destroy();
    }

    @Override
    public void onBackPressed() {

        if (about_webView.canGoBack()) {
            about_webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    public static void actionStart(Context context) {
        Intent i = new Intent(context, AboutActivity.class);
        context.startActivity(i);
    }
}
