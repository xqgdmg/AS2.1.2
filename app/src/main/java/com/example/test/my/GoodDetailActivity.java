package com.example.test.my;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.LinearLayout;
import com.astuetz.PagerSlidingTabStripExtends;
import com.cpoopc.scrollablelayoutlib.ScrollableLayout;
import com.example.test.my.fragment.GoodDetailFragment1;
import com.example.test.my.fragment.GoodDetailFragment2;
import com.example.test.my.fragment.GoodDetailFragment3;
import com.example.test.my.fragment.MyFragmentPagerAdapterNew;
import com.example.test.my.fragment.ScrollAbleFragment;
import com.nineoldandroids.view.ViewHelper;
import java.util.ArrayList;
import java.util.List;

public class GoodDetailActivity extends FragmentActivity
{
    private GoodDetailFragment1 mGoodDetail1;
    private GoodDetailFragment2 mGoodDetail2;
    private GoodDetailFragment3 mGoodDetail3;
    private ViewPager viewpager;
    private LinearLayout                llimageHeader;
    private ScrollableLayout            mScrollLayout;
    private PagerSlidingTabStripExtends pagerSlidingTabStrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_detail);//商品详情
        findView();
        setUp();
        registerListener();
        initFragmentPager(viewpager, pagerSlidingTabStrip, mScrollLayout);
    }

    private void findView() {

        //开源
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        llimageHeader = (LinearLayout) findViewById(R.id.llimageHeader);
        mScrollLayout = (ScrollableLayout) findViewById(R.id.scrollableLayout);
        pagerSlidingTabStrip = (PagerSlidingTabStripExtends) findViewById(R.id.pagerStrip);
    }

    public void initFragmentPager(ViewPager viewPager, PagerSlidingTabStripExtends pagerSlidingTabStrip, final ScrollableLayout mScrollLayout) {
        final ArrayList<ScrollAbleFragment> fragmentList = new ArrayList<>();
        mGoodDetail1 = new GoodDetailFragment1(this);
        mGoodDetail2 = new GoodDetailFragment2(this);
        mGoodDetail3 = new GoodDetailFragment3();
        fragmentList.add(mGoodDetail1);
        fragmentList.add(mGoodDetail2);
        fragmentList.add(mGoodDetail3);

        List<String> titleList = new ArrayList<>();
        titleList.add("服务保障");
        titleList.add("用户评价");
        titleList.add("进入微官网");

        viewPager.setAdapter(new MyFragmentPagerAdapterNew(getSupportFragmentManager(), fragmentList, titleList));//注意参数  // getSupportFragmentManager()
        mScrollLayout.getHelper().setCurrentScrollableContainer(fragmentList.get(0));
        pagerSlidingTabStrip.setViewPager(viewPager);
        pagerSlidingTabStrip.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int i) {
                Log.e("onPageSelected", "page:" + i);
                /** 标注当前页面 **/
                mScrollLayout.getHelper().setCurrentScrollableContainer(fragmentList.get(i));
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        viewPager.setCurrentItem(0);
    }

    private void setUp() {
        mScrollLayout.setOnScrollListener(new ScrollableLayout.OnScrollListener() {
            @Override
            public void onScroll(int currentY, int maxY) {
                ViewHelper.setTranslationY(llimageHeader, (float) (currentY * 0.5));
            }
        });
    }

    private void registerListener() {

    }
}
