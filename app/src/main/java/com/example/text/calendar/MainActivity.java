package com.example.text.calendar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.btn)
    Button btn;
    private String year;
    private String month;
    private String day;
    private int selectIndex;
    private String startTime;
    private String endTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK)
                    year = data.getStringExtra("year");
                month = data.getStringExtra("month");
                day = data.getStringExtra("day");
                selectIndex = data.getIntExtra("selectIndex", 0);
                startTime = year + "-" + month + "-" + day;
                endTime = year + "-" + month + "-" + day;
                Log.e("chris", "startTime=" + startTime + ",endTime=" + endTime + ",selectIndex=" + selectIndex);
                break;
        }
    }

    @OnClick(R.id.btn)
    public void onClick() {
        startActivityForResult(new Intent(this, CalendarActivity.class), 1);
    }
}
