package com.example.text.calendar;

import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author: ZhangXin
 * @time: 2016/12/13 15:03
 * @description:公共的adapter
 */
public abstract class CommonAdapter<E, T> extends BaseAdapter {

    protected List<E> mDatas;
    protected Context mContext;
    private LayoutInflater mInflater;

    public CommonAdapter(Context context) {
        super();
        this.mContext = context;
        this.mDatas = new ArrayList<>();
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        if (mDatas == null) {
            return 0;
        } else
            return mDatas.size();
    }

    public E getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        T holder;
        E item = this.getItem(position);
        if (convertView == null) {
            convertView = mInflater.inflate(getLayoutResId(), parent, false);
            holder = bindHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (T) convertView.getTag();
        }
        bindData(holder, position, item);
        bindView(holder, convertView, parent, position);
        return convertView;
    }

    /**
     * 绑定
     *
     * @return
     */
    public abstract T bindHolder(View view);

    /**
     * 布局
     *
     * @return
     */
    public abstract int getLayoutResId();

    /**
     * 绑定数据
     *
     * @param holder
     * @param position
     * @param itemData
     */
    public abstract void bindData(T holder, int position, E itemData);

    /**
     * 事件回调
     *
     * @param holder
     * @param view
     * @param parent
     * @param position
     */
    public abstract void bindView(T holder, View view, ViewGroup parent, int position);

    public List<E> getData() {
        return this.mDatas;
    }

    public void addNewData(List<E> data) {
        if (data != null) {
            this.mDatas.addAll(0, data);
            this.notifyDataSetChanged();
        }

    }

    /**
     * 添加更多
     *
     * @param data
     */
    public void addMoreData(List<E> data) {
        if (data != null) {
            this.mDatas.addAll(this.mDatas.size(), data);
            this.notifyDataSetChanged();
        }

    }

    /**
     * 数据赋值
     *
     * @param data
     */
    public void setData(List<E> data) {
        if (data != null) {
            this.mDatas = data;
        } else {
            this.mDatas.clear();
        }
        this.notifyDataSetChanged();
    }

    public void clear() {
        this.mDatas.clear();
        this.notifyDataSetChanged();
    }

    public void removeItem(int position) {
        this.mDatas.remove(position);
        this.notifyDataSetChanged();
    }

    public void removeItem(E model) {
        this.mDatas.remove(model);
        this.notifyDataSetChanged();
    }

    public void addItem(int position, E model) {
        this.mDatas.add(position, model);
        this.notifyDataSetChanged();
    }

    public void addFirstItem(E model) {
        this.addItem(0, model);
    }

    public void addLastItem(E model) {
        this.addItem(this.mDatas.size(), model);
    }

    public void setItem(int location, E newModel) {
        this.mDatas.set(location, newModel);
        this.notifyDataSetChanged();
    }

    public void setItem(E oldModel, E newModel) {
        this.setItem(this.mDatas.indexOf(oldModel), newModel);
    }

    public void moveItem(int fromPosition, int toPosition) {
        Collections.swap(this.mDatas, fromPosition, toPosition);
        this.notifyDataSetChanged();
    }

    /**
     * 获取str资源
     *
     * @param strResId
     * @return
     */
    public String getStringResources(int strResId) {
        return mContext.getResources().getString(strResId);
    }

    /**
     * 获取颜色
     *
     * @param colorResId
     * @return
     */
    public int getColorResources(int colorResId) {
        return mContext.getResources().getColor(colorResId);
    }

    public void setTextViewData(TextView textView, String str) {
        if (!TextUtils.isEmpty(str)) {
            textView.setText(str);
        }
    }

    public void setTextViewHtmlData(TextView textView, String htmlStr) {
        if (!TextUtils.isEmpty(htmlStr)) {
            textView.setText(Html.fromHtml(htmlStr));
        }
    }

    public String getUnitPai(int colorResId) {
        return getUnitPai(colorResId, "π");
    }


    public String getUnitPai(int colorResId, String currency) {
        String currencyUnit = "π";
        if (!TextUtils.isEmpty(currency)) {
            if (currency.equalsIgnoreCase("PAI")) {
                currencyUnit = "π";
            } else {
                currencyUnit = currency;
            }

        }
        return "<font color=" + getColorResources(colorResId) + "><small>" + currencyUnit + "</small></font>";
    }
}
