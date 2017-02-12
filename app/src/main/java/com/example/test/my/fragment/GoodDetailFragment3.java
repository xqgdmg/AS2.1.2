package com.example.test.my.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cpoopc.scrollablelayoutlib.ScrollableHelper;
import com.example.test.my.GoodDetailActivity;
import com.example.test.my.R;

/**
 * 小ViewPager的适配fragment
 * 这里用listView显示数据
 */
public class GoodDetailFragment3 extends ScrollAbleFragment
        implements ScrollableHelper.ScrollableContainer {

    private WebView            mWebView;

    public GoodDetailFragment3() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gooddetail_list3, container, false);
        findView(view);
        setUp();
        registerListener();
        return view;
    }

    private void findView(View view) {
        mWebView = (WebView) view.findViewById(R.id.webView);
    }

    @SuppressLint("SetJavaScriptEnabled")
    public void setUp() {
        if (mWebView == null){
            return;
        }

        mWebView.loadUrl("http://www.baidu.com");//测试网址

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        //启用支持javascript
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
    }


    private void registerListener() {

    }

    @Override
    public View getScrollableView() {
        return mWebView;
    }

}
