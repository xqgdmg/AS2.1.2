package com.example.text.calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * @author: ZhangXin
 * @time: 2016/11/15 14:19
 * @description: 日历
 */
public class CalendarActivity extends Activity implements GestureDetector.OnGestureListener {

    @Bind(R.id.iv_preMonth)
    ImageView ivPreMonth;

    @Bind(R.id.tv_year)
    TextView tvYear;

    @Bind(R.id.view_slash_line)
    TextView viewSlashLine;

    @Bind(R.id.tv_month)
    TextView tvMonth;

    @Bind(R.id.iv_nextMonth)
    ImageView ivNextMonth;

    @Bind(R.id.btn_today)
    Button btnToday;

    @Bind(R.id.gridView)
    NoScrollGridView gridView;

    private CalendarAdapter calendarAdapter;

    private static int jumpMonth = 0; // 每次滑动，增加或减去一个月,默认为0（即显示当前月）
    private static int jumpYear = 0; // 滑动跨越一年，则增加或者减去一年,默认为0(即当前年)
    private int year_c = 0;
    private int month_c = 0;
    private int day_c = 0;
    private String currentDate = "";
    public int gvFlag = 0;
    public int selectIndex = 0;

    private GestureDetector gestureDetector = null;
    private List<Map<String, String>> groupDataList = new ArrayList<Map<String, String>>();
    private List<List<Map<String, String>>> childDataList = new ArrayList<List<Map<String, String>>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFinishOnTouchOutside(true);
        setContentView(R.layout.activity_calendar);
        ButterKnife.bind(this);
        initView();
        setDateValue();
        gestureDetector = new GestureDetector(this);
    }

    private void initView() {
        boolean isChooseDate = getIntent().getBooleanExtra("isChooseDate", false);
        if (isChooseDate) {
            year_c = getIntent().getIntExtra("year", 0);
            month_c = getIntent().getIntExtra("month", 0);
            day_c = getIntent().getIntExtra("day", 0);
            selectIndex = getIntent().getIntExtra("selectIndex", 0);
            calendarAdapter = new CalendarAdapter(this, year_c, month_c, day_c);
            calendarAdapter.setSelectIndex(selectIndex);
        } else {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
            currentDate = sdf.format(date); // 当期日期
            year_c = Integer.parseInt(currentDate.split("-")[0]);
            month_c = Integer.parseInt(currentDate.split("-")[1]);
            day_c = Integer.parseInt(currentDate.split("-")[2]);
            calendarAdapter = new CalendarAdapter(this, jumpMonth, jumpYear, year_c,
                    month_c, day_c);
        }
        gridView.setAdapter(calendarAdapter);

        gridView.setOnItemClickListener(itemClickListener);
        gridView.setOnTouchListener(touchListener);
    }

    private void setDateValue() {
        tvYear.setText(calendarAdapter.getShowYear());
        tvMonth.setText(calendarAdapter.getShowMonth());
    }

    private View.OnTouchListener touchListener = new View.OnTouchListener() {
        // 将gridView中的触摸事件回传给gestureDetector
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return CalendarActivity.this.gestureDetector.onTouchEvent(event);
        }
    };

    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // 点击任何一个item，得到这个item的日期(排除点击的是周日到周六(点击不响应))
            int startPosition = calendarAdapter.getStartPositon();
            int endPosition = calendarAdapter.getEndPosition();
            String scheduleDay = calendarAdapter.getDateByClickItem(position)
                    .split("\\.")[0];
            if (startPosition <= position + 7 && position <= endPosition - 7) {
                calendarAdapter.setSelectIndex(position);
                Intent intent = new Intent();
                intent.putExtra("selectIndex", position);
                intent.putExtra("year", calendarAdapter.getShowYear());
                intent.putExtra("month", calendarAdapter.getShowMonth());
                intent.putExtra("day", scheduleDay);
                setResult(RESULT_OK, intent);
                revertData();
                finish();
            }
        }
    };


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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            revertData();
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick({R.id.iv_preMonth, R.id.iv_nextMonth, R.id.btn_today})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_preMonth:
                TurnPrevMonth();
                break;
            case R.id.iv_nextMonth:
                TurnNextMonth();
                break;
            case R.id.btn_today:
                TurnToToday();
                break;
        }
    }

    /**
     * @param v1
     * @param v2
     * @return
     */
    public double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    /**
     * 跳转到今天
     */
    private void TurnToToday() {
        gvFlag = 0;
        jumpMonth = 0;
        jumpYear = 0;
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
        currentDate = sdf.format(date); // 当期日期
        year_c = Integer.parseInt(currentDate.split("-")[0]);
        month_c = Integer.parseInt(currentDate.split("-")[1]);
        day_c = Integer.parseInt(currentDate.split("-")[2]);
        calendarAdapter = new CalendarAdapter(this, jumpMonth, jumpYear, year_c,
                month_c, day_c);
        gridView.setAdapter(calendarAdapter);
        setDateValue();
        gvFlag++;
    }

    /**
     * 跳转到上个月
     */
    private void TurnPrevMonth() {

        gvFlag = 0;
        jumpMonth--;
        calendarAdapter = new CalendarAdapter(this, jumpMonth, jumpYear, year_c,
                month_c, day_c);
        gridView.setAdapter(calendarAdapter);
        gvFlag++;
        setDateValue();
    }

    /**
     * 跳转到下个月
     */
    private void TurnNextMonth() {
        gvFlag = 0;
        jumpMonth++;

        calendarAdapter = new CalendarAdapter(this, jumpMonth, jumpYear, year_c,
                month_c, day_c);
        gridView.setAdapter(calendarAdapter);
        gvFlag++;
        setDateValue();
    }



    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (e1.getX() - e2.getX() > 120) { //向左滑动
            TurnNextMonth();
            return true;
        } else if (e1.getX() - e2.getX() < -120) { //向右滑动
            TurnPrevMonth();
            return true;
        }
        return false;
    }
}
