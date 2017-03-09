package com.example.text.ratiolayout;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;


/**
 * 创建者     chris
 * 创建时间   2016/7/5 14:03
 * 描述	      和ui相关的操作
 *
 */
public class UIUtils {
    public static final String TAG = "UIUtils";


    /**
     * px --> dip
     * @param px
     * @return
     */
    public static int px2Dip(int px) {
        //1. px / (ppi/160) = dp;
        //2. px / dip = density;
        float density = getResources().getDisplayMetrics().density;
        int dip = (int) (px / density + .5f);
        return dip;
    }

    /**
     * 得到Resource对象
     */
    public static Resources getResources() {
        return getContext().getResources();
    }

    public static Context getContext() {
        return MyApplication.getContext();
    }

}
