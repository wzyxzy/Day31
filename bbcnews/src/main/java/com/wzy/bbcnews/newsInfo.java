package com.wzy.bbcnews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class newsInfo extends AppCompatActivity {

    private WebView webview;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_info);
        initData();
        initView();
    }

    private void initData() {
        Intent intent=getIntent();
        url=intent.getStringExtra("infoUrl");
    }

    private void initView() {
        webview = (WebView) findViewById(R.id.webview);
        webview = new WebView(this);
//设置WebView属性，能够执行Javascript脚本
        webview.getSettings().setJavaScriptEnabled(true);
//加载需要显示的网页
//        wv.loadUrl("file:///android_asset/h5/agent.html");
        webview.loadUrl(url);
//设置Web视图

        setContentView(webview);

        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                view.loadUrl(url);   //在当前的webview中跳转到新的url

                return true;
            }
        });
    }

//    @Override
////设置回退
////覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//
//        if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {
//
//            webview.goBack(); //goBack()表示返回WebView的上一页面
//
//            return true;
//
//        }
//
//        return true;
//    }

}
