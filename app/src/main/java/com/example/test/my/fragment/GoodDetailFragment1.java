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
import com.cpoopc.scrollablelayoutlib.ScrollableHelper;
import com.example.test.my.R;

/**
 * 小ViewPager的适配fragment
 * 这里用listView显示数据
 */
public class GoodDetailFragment1 extends ScrollAbleFragment
        implements ScrollableHelper.ScrollableContainer {

    private WebView            mWebView;
    private Context            context;

    public GoodDetailFragment1() {
    }

    @SuppressLint("ValidFragment")
    public GoodDetailFragment1(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gooddetail_list1, container, false);
        findView(view);
        setUp();
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

        // webView设置
        mWebView.loadUrl("http://www.baidu.com");

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

    @Override
    public View getScrollableView() {//尼玛 只能是能滚动的view
        return mWebView;
    }



}
