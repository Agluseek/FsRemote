package com.agluseek.farsoon.farsoononline.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.agluseek.farsoon.farsoononline.R;

import io.vov.vitamio.utils.Log;

/**
 * @author Farsoon Wu'Ang
 *  FS271 M 金属机详情
 */

public class Metal_detailActivity extends AppCompatActivity {
    private WebView webView;
    private static LinearLayout loading;
    private static ProgressBar loadingBar;
    private static TextView loadingText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.metal_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.metal_detail_toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("FS-271M");

    //  webView = new WebView(getApplicationContext());
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

//      ws.setDomStorageEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request)
            {
                String url = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    url = request.getUrl().toString();
                }

                view.loadUrl(url);
                return true;
//                if (url.startsWith("tel:")) {
//                    initiateCall(url);
//                    return true;
//                }
//                if (url.startsWith("mailto:")) {
//                    sendEmail(url.substring(7));
//                    return true;
//                }
//                return false;
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
            //            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return true;
//            }
        });
        //http://mp.weixin.qq.com/s/xHadtsSJFfsS4awVT0sa-g
        //http://www.farsoon.com/?page_id=995
        webView.loadUrl("https://mp.weixin.qq.com/s/xHadtsSJFfsS4awVT0sa-g");
        //http://mp.weixin.qq.com/s/xHadtsSJFfsS4awVT0sa-g
//        ws.setBuiltInZoomControls(true);// 隐藏缩放按钮
//        ws.setUseWideViewPort(true);// 可任意比例缩放
//        ws.setLoadWithOverviewMode(true);// setUseWideViewPort方法设置webview推荐使用的窗口。setLoadWithOverviewMode方法是设置webview加载的页面的模式。
//        ws.setSavePassword(true);
//        ws.setSaveFormData(true);// 保存表单数据
//        ws.setDomStorageEnabled(true);
//        ws.setSupportMultipleWindows(true);//新加
//        webView.setWebViewClient(new WebViewClient());

    }


    private void showProgress(final boolean show) {
        loading.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //不加的话多次点击后程序崩溃
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
        Log.i("-------->", "onDestroy");
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(webView.canGoBack() && keyCode == KeyEvent.KEYCODE_BACK){
            webView.goBack();   //goBack()表示返回webView的上一页面
            return true;
        }

        return false;
    }

}
