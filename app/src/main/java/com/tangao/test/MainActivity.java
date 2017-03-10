package com.tangao.test;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity {

    //QQ登录申请的appid
    public static final String QQ_APP_ID = "1105547524";

    //布局控件
    private Button btnLogIn;
    private ImageView headerLogo;
    private TextView tvNickName;
    private TextView openId;

    //显示获取到的头像和昵称
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {//获取昵称
                tvNickName.setText((CharSequence) msg.obj);
            } else if (msg.what == 1) {//获取头像
                headerLogo.setImageBitmap((Bitmap) msg.obj);
            }
        }
    };

    //需要腾讯提供的一个Tencent类
    private Tencent mTencent;
    //还需要一个IUiListener 的实现类（LogInListener implements IUiListener）
    private LogInListener mListener;

    //用来判断当前是否已经授权登录，若为false，点击登录button时进入授权，否则注销
    private boolean isLogIned = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //首先需要用APP ID 获取到一个Tencent实例
        mTencent = Tencent.createInstance(QQ_APP_ID, this.getApplicationContext());
        //初始化一个IUiListener对象，在IUiListener接口的回调方法中获取到有关授权的某些信息
        // （千万别忘了覆写onActivityResult方法，否则接收不到回调）
        mListener = new LogInListener();
        //初始化各控件
        initView();
    }

    private void initView() {

        btnLogIn = (Button) findViewById(R.id.mainQQ_btn_login);
//        headerLogo = (CustomImageView) findViewById(R.id.mainQQ_iv_user_logo);
        headerLogo = (ImageView) findViewById(R.id.mainQQ_iv_user_logo);
        tvNickName = (TextView) findViewById(R.id.mainQQ_tv_user_nickname);
        openId = (TextView) findViewById(R.id.mainQQ_tv_user_openid);

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isLogIned) {
                    isLogIned = true;
                    //调用QQ登录，用IUiListener对象作参数
                    if (!mTencent.isSessionValid()) {
                        mTencent.login(MainActivity.this, "all", mListener);
                    }
                } else {
                    //登出
                    mTencent.logout(MainActivity.this);
                    isLogIned = false;
                    Toast.makeText(MainActivity.this, "登录已注销！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     *
     */
    private class LogInListener implements IUiListener {

        @Override
        public void onComplete(Object o) {
            Toast.makeText(MainActivity.this, "授权成功！", Toast.LENGTH_LONG).show();
            System.out.println("o.toString() ------------------------->        " + o.toString());


            JSONObject jsonObject = (JSONObject) o;

            //设置openid和token，否则获取不到下面的信息
            initOpenidAndToken(jsonObject);
            //获取QQ用户的各信息
            getUserInfo();
        }

        @Override
        public void onError(UiError uiError) {

            Toast.makeText(MainActivity.this, "授权出错！", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel() {
            Toast.makeText(MainActivity.this, "授权取消！", Toast.LENGTH_LONG).show();
        }
    }

    private void initOpenidAndToken(JSONObject jsonObject) {
        try {
            String openid = jsonObject.getString("openid");
            String token = jsonObject.getString("access_token");
            String expires = jsonObject.getString("expires_in");

            mTencent.setAccessToken(token, expires);
            mTencent.setOpenId(openid);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getUserInfo() {

        //sdk给我们提供了一个类UserInfo，这个类中封装了QQ用户的一些信息，我么可以通过这个类拿到这些信息
        QQToken mQQToken = mTencent.getQQToken();
        UserInfo userInfo = new UserInfo(MainActivity.this, mQQToken);
        userInfo.getUserInfo(new IUiListener() {
                                 @Override
                                 public void onComplete(final Object o) {
                                     JSONObject userInfoJson = (JSONObject) o;
                                     //                {
//                        "ret": 0,
//                        "msg": "",
//                        "is_lost": 0,
//                        "nickname": "High!",
//                        "gender": "男",
//                        "province": "天津",
//                        "city": "河西",
//                        "figureurl": "http://qzapp.qlogo.cn/qzapp/1105547524/19E8D43EB75ED256CAC70C02953F188A/30",
//                        "figureurl_1": "http://qzapp.qlogo.cn/qzapp/1105547524/19E8D43EB75ED256CAC70C02953F188A/50",
//                        "figureurl_2": "http://qzapp.qlogo.cn/qzapp/1105547524/19E8D43EB75ED256CAC70C02953F188A/100",
//                        "figureurl_qq_1": "http://q.qlogo.cn/qqapp/1105547524/19E8D43EB75ED256CAC70C02953F188A/40",
//                        "figureurl_qq_2": "http://q.qlogo.cn/qqapp/1105547524/19E8D43EB75ED256CAC70C02953F188A/100",
//                        "is_yellow_vip": "0",
//                        "vip": "0",
//                        "yellow_vip_level": "0",
//                        "level": "0",
//                        "is_yellow_year_vip": "0"
//                }
                                     Message msgNick = new Message();
                                     msgNick.what = 0;//昵称
                                     try {
                                         msgNick.obj = userInfoJson.getString("nickname");//直接传递一个昵称的内容过去
                                     } catch (JSONException e) {
                                         e.printStackTrace();
                                     }
                                     mHandler.sendMessage(msgNick);
                                     //子线程 获取并传递头像图片，由Handler更新
                                     new Thread(new Runnable() {
                                         @Override
                                         public void run() {
                                             Bitmap bitmapHead = null;
                                             if (((JSONObject) o).has("figureurl")) {
                                                 try {
                                                     String headUrl = ((JSONObject) o).getString("figureurl_qq_2");
                                                     bitmapHead = Util.getbitmap(headUrl);
                                                 } catch (JSONException e) {
                                                     e.printStackTrace();
                                                 }
                                                 Message msgHead = new Message();
                                                 msgHead.what = 1;
                                                 msgHead.obj = bitmapHead;
                                                 mHandler.sendMessage(msgHead);
                                             }
                                         }
                                     }).start();

                                 }

                                 @Override
                                 public void onError(UiError uiError) {
                                     Log.e("GET_QQ_INFO_ERROR", "获取qq用户信息错误");
                                     Toast.makeText(MainActivity.this, "获取qq用户信息错误", Toast.LENGTH_SHORT).show();
                                 }

                                 @Override
                                 public void onCancel() {
                                     Log.e("GET_QQ_INFO_CANCEL", "获取qq用户信息取消");
                                     Toast.makeText(MainActivity.this, "获取qq用户信息取消", Toast.LENGTH_SHORT).show();
                                 }
                             }
        );
    }

    //确保能接收到回调
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Tencent.onActivityResultData(requestCode, resultCode, data, mListener);
    }
}
