package com.example.test.my.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.cpoopc.scrollablelayoutlib.ScrollableHelper;
import com.example.test.my.GoodDetailActivity;
import com.example.test.my.R;

/**
 * 小ViewPager的适配fragment
 * 这里用listView显示数据
 */
public class GoodDetailFragment2 extends ScrollAbleFragment
        implements ScrollableHelper.ScrollableContainer {

    private Context context;
    private GoodDetailActivity act;
    private TextView tvNoComment;
    private ListView lvComment;

    public GoodDetailFragment2() {
    }

    @SuppressLint("ValidFragment")
    public GoodDetailFragment2(Context context) {
        this.context = context;
        this.act = (GoodDetailActivity) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gooddetail_list2, container, false);
        findView(view);
        setUp();
        return view;
    }


    private void findView(View view) {

        lvComment = (ListView) view.findViewById(R.id.lvComment);
    }

    public void setUp() {//数据都是别人提供的，只提供方法给别人设置
        MyAdapter adapter = new MyAdapter();
        lvComment.setAdapter(adapter);
    }

    @Override
    public View getScrollableView() {
        return lvComment;
    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 50;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = View.inflate(getActivity().getApplicationContext(),R.layout.item,null);
            return v;
        }
    }
}
