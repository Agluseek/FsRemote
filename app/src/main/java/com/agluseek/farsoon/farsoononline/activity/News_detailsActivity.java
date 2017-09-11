package com.agluseek.farsoon.farsoononline.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.agluseek.farsoon.farsoononline.R;

/**
 * Created by Farsoon on 2017/5/4.
 */

public class News_detailsActivity extends AppCompatActivity {
    private WebView webView;
    private static LinearLayout loading;
    private static ProgressBar loadingBar;
    private static TextView loadingText;
    private static String url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.metal_detail);
        android.support.v7.widget.Toolbar bar = (android.support.v7.widget.Toolbar) findViewById(R.id.metal_detail_toolbar);
        setSupportActionBar(bar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("公司新闻");

        webView = (WebView) findViewById(R.id.metal_detail_webview);
        loading = (LinearLayout) findViewById(R.id.loading);
        loadingBar = (ProgressBar) findViewById(R.id.loading_bar);
        loadingText = (TextView) findViewById(R.id.loading_text);

        showProgress(true);

        WebSettings ws = webView.getSettings();

        ws.setJavaScriptEnabled(true);
        ws.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        ws.setUseWideViewPort(true);
        ws.setLoadWithOverviewMode(true);
        ws.setSupportZoom(true);
        // 设置出现缩放工具
        ws.setBuiltInZoomControls(true);
        //扩大比例的缩放

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                String url = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    url = request.getUrl().toString();
                }
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                showProgress(false);

            }
        });

        if (url != null) {
            webView.loadUrl(url);
        }

    }

    private void showProgress(final boolean show) {
        loading.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                webView.clearCache(true);
                webView.clearHistory();
                webView.destroy();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        webView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        webView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView.destroy();
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    public static void actionStart(Context context, String address) {
        Intent i = new Intent(context, News_detailsActivity.class);
        url = address;
        context.startActivity(i);

    }
}
