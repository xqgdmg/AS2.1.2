package com.example.text.ratiolayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.text.ratiolayout.ui.OomActivity;
import com.example.text.ratiolayout.ui.RatioLayoutActivity;
import com.example.text.ratiolayout.view.RatioLayout;

public class MainActivity extends AppCompatActivity {

    private Button btnOom;
    private ImageView iv;
    private RatioLayout ratioLayout;
    private Button btnRatio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        // 跳转到 OomActivity
        initListener();



    }



    private void initView() {
        btnOom = (Button) findViewById(R.id.btnOom);
        btnRatio = (Button) findViewById(R.id.btnRatio);
    }

    private void initListener() {

        btnOom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, OomActivity.class));
            }
        });

        btnRatio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RatioLayoutActivity.class));
            }
        });
    }


}
