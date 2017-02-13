package com.my.my;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class SpinnerView extends RelativeLayout {

	private EditText mEdit;
	private ImageView mArrow;
	private PopupWindow mPopupWindow;

	// ListView的数据集合
	private ArrayList<String> mDataList;

	/**
	 * setContentView系统会解析xml,调用此构造函数创建对象
	 * 
	 * @param context
	 * @param attrs
	 */
	public SpinnerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	/**
	 * 代码使用
	 * @param context
	 */
	public SpinnerView(Context context) {
		super(context);
		init();
	}

	/**
	 * 初始化自定义控件的布局
	 */
	private void init() {
		// 用inflate方法解析一个布局添加到自定义控件里面
		LayoutInflater.from(getContext()).inflate(R.layout.view_spinner, this);
		mEdit = (EditText) findViewById(R.id.edit);
		mArrow = (ImageView) findViewById(R.id.arrow);
		mockList();
		initEvent();
	}
	
	 /**
     * 模拟listView里面的数据
     */
    private void mockList() {
    	mDataList = new ArrayList<String>();
    	for (int i = 10000; i < 10030; i ++) {
    		mDataList.add(String.valueOf(i));
    	}
	}


	/**
     * 初始化事件
     */
    private void initEvent() {
    	mArrow.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showListWindow();
			}
		});
	}


    /**
     * 弹出下拉选择框的popupwindow
     */
	protected void showListWindow() {
		//mPopupWindow只创建一次
		if (mPopupWindow == null) {
			int width = mEdit.getWidth();
			int height = 320;
			mPopupWindow = new PopupWindow(width, height);
//			TextView contentView = new TextView(this);
//			contentView.setBackgroundColor(Color.BLUE);
			
			ListView contentView = new ListView(getContext());
			contentView.setAdapter(mBaseAdapter);
			
			//给ListView设置边框
			contentView.setBackgroundResource(R.drawable.listview_background);
			
			
			contentView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					//点击时设置编辑框的文本
					String data = mDataList.get(position);
					mEdit.setText(data);
					//设置光标的位置
					mEdit.setSelection(data.length());
					
					//消失popupwindow
					mPopupWindow.dismiss();
				}
			});
			
			//listview -> setAdapter - > 创建数据集合
			mPopupWindow.setContentView(contentView);
			
			//转移焦点
			mPopupWindow.setFocusable(true);
			
			//让popupwindow消失
			mPopupWindow.setOutsideTouchable(true);
			//设置背景后，才会有触摸事件处理，才会消失
			mPopupWindow.setBackgroundDrawable(new ColorDrawable());
			
		}
		
		//弹出popupwindow
		mPopupWindow.showAsDropDown(mEdit);
	}
	
	private BaseAdapter mBaseAdapter = new BaseAdapter() {
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder vh = null;
			if (convertView == null) {
//				View.inflate(context, resource, root)
				convertView = LayoutInflater.from(getContext())
						.inflate(R.layout.view_list_item, null);
				vh = new ViewHolder(convertView);
				convertView.setTag(vh);
			} else {
				vh = (ViewHolder) convertView.getTag();
			}
			
			//刷新TextView
			final String data = mDataList.get(position);
			vh.mId.setText(data);
			//3. 点击删除按钮，删除列表选项
			vh.mDelete.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					//移除点击位置的数据
					mDataList.remove(data);
					//刷新列表
					notifyDataSetChanged();
				}
			});
			
			return convertView;
		}
		
		@Override
		public long getItemId(int position) {
			return 0;
		}
		
		@Override
		public Object getItem(int position) {
			return null;
		}
		
		@Override
		public int getCount() {
			return mDataList.size();
		}
	};
	
	private class ViewHolder {
		TextView mId;
		ImageView mDelete;
		
		public ViewHolder(View root) {
			mId = (TextView) root.findViewById(R.id.user_id);
			mDelete = (ImageView) root.findViewById(R.id.delete);
		}
	}


}
