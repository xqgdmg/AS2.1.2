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

import com.example.text.ratiolayout.oom.OomActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         // 跳转到 OomActivity
        gotoOomActivity();

        ImageView iv = (ImageView) findViewById(R.id.iv);
        RatioLayout ratioLayout = (RatioLayout) findViewById(R.id.ratio);

         // 当图片的宽高比不是固定的，或者是不知道图片的宽高比

         // 1 计算本地图片的宽高比
        mothod1(iv, ratioLayout);

         // 2 计算图片的宽高比 glide
        mothod2(iv, ratioLayout);

    }

    private void gotoOomActivity() {
        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, OomActivity.class));
            }
        });
    }

    private void mothod2(ImageView iv, RatioLayout ratioLayout) {

    }

    private void mothod1(ImageView iv, RatioLayout ratioLayout) {
        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inJustDecodeBounds = true;
        // 通过这个bitmap获取图片的宽和高
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.t4,options);
        if (bitmap == null){
            Log.e("TAG","bitmap为空");
        }

        float realWidth = options.outWidth;
        float realHeight = options.outHeight;
        Log.e("TAG","realWidth=" + realWidth);
        Log.e("TAG","realHeight=" + realHeight);
//        Log.e("TAG","bitmap.getWidth()=" + bitmap.getWidth());
//        Log.e("TAG","bitmap.getHeight()=" + bitmap.getHeight());

        // 计算宽高比
        float ratio = realWidth/realHeight;
//        float ratio = bitmap.getWidth()/bitmap.getHeight();
        Log.e("TAG","ratio=" + ratio);

        /*// 计算缩放比
        int scale = (int) ((realHeight > realWidth ? realHeight : realWidth) / 100);
        if (scale <= 0)
        {
            scale = 1;
        }
        options.inSampleSize = scale;
        options.inJustDecodeBounds = false;
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.mipmap.t1,options);*/

        iv.setImageBitmap(bitmap); // 设置图片到控件
        ratioLayout.setPicRatio(ratio); // 可以点击设置
    }
}
