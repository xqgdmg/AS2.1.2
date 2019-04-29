package com.example.text.ratiolayout.ui;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.text.ratiolayout.R;
import com.example.text.ratiolayout.view.RatioLayout;

public class RatioLayoutActivity extends Activity {

    private RatioLayout ratioLayout;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratio_layout);

        initView();

        // 当图片的宽高比不是固定的，或者是不知道图片的宽高比
        testRatioLayout();
    }

    private void initView() {
        iv = (ImageView) findViewById(R.id.iv);
        ratioLayout = (RatioLayout) findViewById(R.id.ratio);
    }

    private void testRatioLayout() {
        // 1 计算本地图片的宽高比
        mothod1(iv, ratioLayout);

//        // 2 计算图片的宽高比 glide
//        mothod2(iv, ratioLayout);
    }

//    private void mothod2(ImageView iv, RatioLayout ratioLayout) {
//
//    }

    private void mothod1(ImageView iv, RatioLayout ratioLayout) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true; // 相册图片铺满屏幕，直接 oom，只写这个是黑色的图片
        // 通过这个bitmap获取图片的宽和高。。。占用了 167M 的运行内存（p8迷你的可用运行内存 175 M 左右）
         // R.drawable.big 是一张电脑壁纸
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.t4,options); // 这里的 options 带有图片大小
        if (bitmap == null){
            Log.e("TAG","bitmap为空");
        }

         // 1 计算宽高比
//        float realWidth = 200;  // test：直接写死宽度，自动计算高度
        float realWidth = options.outWidth;
        float realHeight = options.outHeight;
        Log.e("TAG","realWidth=" + realWidth);
        Log.e("TAG","realHeight=" + realHeight);

        float ratio = realWidth/realHeight;
//        float ratio = bitmap.getWidth()/bitmap.getHeight();
        Log.e("TAG","ratio=" + ratio);

        // 2 计算缩放比，解决 oom 的问题 （造成了图片模糊）
        int scale = (int) ((realHeight > realWidth ? realHeight : realWidth) / 1000); // 1000 ，数字越大越清晰
        Log.e("TAG","scale=" + scale);
        if (scale <= 0){
            scale = 1;
        }
        options.inSampleSize = scale;
        options.inJustDecodeBounds = false;
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.t4,options);
        iv.setImageBitmap(bitmap2); // 设置图片到控件
        ratioLayout.setPicRatio(ratio); // 可以点击设置
//        ratioLayout.setPicRatio(2); // 手动减小一半，图片会被压缩
    }
}
