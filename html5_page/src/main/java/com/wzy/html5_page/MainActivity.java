package com.wzy.html5_page;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    private WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        wv = (WebView) findViewById(R.id.wv);
        wv = new WebView(this);
        wv.setWebChromeClient(new WebChromeClient());
//设置WebView属性，能够执行Javascript脚本
        wv.getSettings().setJavaScriptEnabled(true);
//加载需要显示的网页
//        wv.loadUrl("file:///android_asset/h5/agent.html");
        wv.loadUrl("file:///android_asset/Html5CameraDemo/HTML5Camera.htm");
//        wv.loadUrl("file:///android_asset/xmltest/ddisplocathtml.html");
//设置Web视图
        setContentView(wv);

        wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                view.loadUrl(url);   //在当前的webview中跳转到新的url

                return true;
            }
        });
    }
    @Override
//设置回退
//覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if ((keyCode == KeyEvent.KEYCODE_BACK) && wv.canGoBack()) {

            wv.goBack(); //goBack()表示返回WebView的上一页面

            return true;

        }

        return false;
    }
}
