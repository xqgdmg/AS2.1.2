package com.example.test.myrichtextdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        /*####以下是显示表情######*/

        TextView tv = (TextView) findViewById(R.id.tv);

        String content = "[呲牙]DotaDotaDota,幻影刺客，猴子，影魔";

//        CharacterStyle 记住这个看继承关系

        SpannableString spanableString = new SpannableString(content);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources() , R.drawable.smiley_13);

        spanableString.setSpan(new ImageSpan(bitmap),
                0 ,
                4 ,
                Spanned.SPAN_INCLUSIVE_EXCLUSIVE);


        tv.setText(spanableString);

/*

        #####以下是点击 + 替换颜色处理 ######

        String content = "[呲牙]DotaDotaDota,幻影刺客，猴子，影魔";

        SpannableString spanableString = new SpannableString(content);

        //允许点击
        spanableString.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(MainActivity.this, "点击了文字", Toast.LENGTH_SHORT).show();
            }


            @Override
            public void updateDrawState(TextPaint ds) {
               // super.updateDrawState(ds);

                ds.setColor(Color.BLUE);
                ds.setUnderlineText(false);
            }
        }, 0, 8, SpannableString.SPAN_INCLUSIVE_INCLUSIVE);


        tv.setMovementMethod(new LinkMovementMethod());

        tv.setText(spanableString);*/


       /*
       #########以下是修改颜色############
       TextView tv = (TextView) findViewById(R.id.tv);

        String content = "[呲牙]DotaDotaDota,幻影刺客，猴子，影魔";

        //修改文本中的文字颜色 + 是否可以点击 + 替换图片
        SpannableString spanableString = new SpannableString(content);

        spanableString.setSpan(
                //替换颜色
                new ForegroundColorSpan(Color.BLUE),
                0 ,  //范围起始索引
                8 ,  //范围结束索引
                SpannableString.SPAN_INCLUSIVE_INCLUSIVE); //是否包含前后


        tv.setText(spanableString);*/







        /*##### 以下是html过滤标签*/
      /*  TextView tv = (TextView) findViewById(R.id.tv);

        //String content = "<font color='red'>安卓开发大牛是美女</font>";


        String content2 = "<a href='http://www.itheima.com'>安卓开发大牛是美女</a>";

//        过滤掉html的标签，呈现出来具体的内容
        Spanned spanned = Html.fromHtml(content2);

      //  LinkMovementMethod.getInstance()
        //允许点击超链接
        tv.setMovementMethod(new LinkMovementMethod());

        tv.setText(spanned);*/
    }

}
