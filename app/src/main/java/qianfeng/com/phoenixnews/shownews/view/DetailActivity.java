package qianfeng.com.phoenixnews.shownews.view;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.Serializable;

import qianfeng.com.phoenixnews.R;
import qianfeng.com.phoenixnews.bean.LVItemBean;

public class DetailActivity extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initView();
        loadData();
    }

    private void loadData() {
        LVItemBean lvItemBean = (LVItemBean) getIntent().getSerializableExtra("lvItemBean");
        String commentsUrl = lvItemBean.getCommentsUrl();
        Log.d("super-filter", "loadData: "+commentsUrl);
        mWebView.loadUrl(commentsUrl);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
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
            }
        });
    }

    private void initView() {
        mWebView = (WebView) ((WebView) findViewById(R.id.webview));
    }
}
