package com.example.text.calendar.demo2;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.text.calendar.InterlinkCalendarAdapter;
import com.example.text.calendar.JoinActivityAdapter;
import com.example.text.calendar.NoScrollGridView;
import com.example.text.calendar.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Chris on 2017/3/6.
 */
public class CalendarActivity2 extends Activity {
    @Bind(R.id.ivLeft)
    ImageView ivLeft;
    @Bind(R.id.tvMonth)
    TextView tvMonth;
    @Bind(R.id.ivRight)
    ImageView ivRight;
    @Bind(R.id.listView)
    ListView listView;
    @Bind(R.id.btnNext)
    Button btnNext;
    @Bind(R.id.gridView)
    NoScrollGridView gridView;
    private InterlinkCalendarAdapter calendarAdapter;
    private String currentDate = "";

    private static int jumpMonth = 0; // 每次滑动，增加或减去一个月,默认为0（即显示当前月）
    private static int jumpYear = 0; // 滑动跨越一年，则增加或者减去一年,默认为0(即当前年)
    private int year_c = 0;
    private int month_c = 0;
    private int day_c = 0;
    private String giveDate;
    private JoinActivityAdapter activityAdapter;
    private int gvFlag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar2);
        ButterKnife.bind(this);

        initCalendar();

        initActivityAdapter();

        simulate();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        revertData();
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 还原数据
     */
    private void revertData() {
        jumpMonth = 0;
        jumpYear = 0;
        year_c = 0;
        month_c = 0;
        day_c = 0;
        currentDate = "";
        gvFlag = 0;
    }

    private void simulate() {
        List<String> data = new ArrayList<String>();
        data.add("a");
        data.add("a");
        data.add("a");
        data.add("a");
        activityAdapter.setData(data);
    }

    private void initCalendar() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
        currentDate = sdf.format(date); // 当期日期
        year_c = Integer.parseInt(currentDate.split("-")[0]);
        month_c = Integer.parseInt(currentDate.split("-")[1]);
        day_c = Integer.parseInt(currentDate.split("-")[2]);
        calendarAdapter = new InterlinkCalendarAdapter(this, jumpMonth, jumpYear, year_c,
                month_c, day_c);
        gridView.setAdapter(calendarAdapter);
        gridView.setOnItemClickListener(itemClickListener);
        giveDate = currentDate;
    }

    private void initActivityAdapter() {
        activityAdapter = new JoinActivityAdapter(this);
        listView.setAdapter(activityAdapter);
        listView.setOnItemClickListener(clickListener);

    }

    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // 点击任何一个item，得到这个item的日期(排除点击的是周日到周六(点击不响应))
            int startPosition = calendarAdapter.getStartPositon();
            int endPosition = calendarAdapter.getEndPosition();
            String scheduleDay = calendarAdapter.getDateByClickItem(position)
                    .split("\\.")[0];
            giveDate = calendarAdapter.getShowYear() + "-" + calendarAdapter.getShowMonth() + "-" + scheduleDay;
            if (startPosition <= position + 7 && position <= endPosition - 7) {
                calendarAdapter.setSelectIndex(position);

                activityAdapter.setHasJoin(false);
            }
        }
    };

    private AdapterView.OnItemClickListener clickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            Toast.makeText(CalendarActivity2.this,"activityListView onItemClick",Toast.LENGTH_LONG).show();
        }
    };

    @OnClick({R.id.ivLeft, R.id.ivRight, R.id.btnNext})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                TurnPrevMonth();
                break;
            case R.id.ivRight:
                TurnNextMonth();
                break;
            case R.id.btnNext:
                break;
        }
    }


    /**
     * 跳转到上个月
     */
    private void TurnPrevMonth() {

        gvFlag = 0;
        jumpMonth--;
        calendarAdapter = new InterlinkCalendarAdapter(this, jumpMonth, jumpYear, year_c,
                month_c, day_c);
        gridView.setAdapter(calendarAdapter);
        gvFlag++;
        showDateValue();
    }

    /**
     * 跳转到下个月
     */
    private void TurnNextMonth() {
        gvFlag = 0;
        jumpMonth++;

        calendarAdapter = new InterlinkCalendarAdapter(this, jumpMonth, jumpYear, year_c,
                month_c, day_c);
        gridView.setAdapter(calendarAdapter);
        gvFlag++;
        showDateValue();
    }

    /**
     * 显示日期月份
     */
    private void showDateValue() {

        setTextViewData(tvMonth, getStringResources(MerchantEnums.Month.getMonth
                (str2Int(calendarAdapter.getShowMonth())).getMonthResId()));
    }

    public String getStringResources(int strResId) {
        return getResources().getString(strResId);
    }

    public void setTextViewData(TextView textView, String str) {
        if (!TextUtils.isEmpty(str)) {
            textView.setText(str);
        }
    }

    public static int str2Int(String data) {
        int num = 0;
        if (!TextUtils.isEmpty(data)) {
            try {
                num = Integer.valueOf(data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return num;
    }
}
