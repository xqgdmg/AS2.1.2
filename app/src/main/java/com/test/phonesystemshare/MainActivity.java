package com.test.phonesystemshare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                AndroidShare as = new AndroidShare(
                        MainActivity.this,
                        "哈哈---超方便的分享！！！来自allen",
                        "http://img6.cache.netease.com/cnews/news2012/img/logo_news.png");
                as.show();
            }
        });
    }
}
