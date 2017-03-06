package com.example.text.calendar;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @Author zhangxin
 * @date 2017/2/27 16:32
 * @description 参加活动的适配器
 **/
public class JoinActivityAdapter extends CommonAdapter<String, JoinActivityAdapter.ViewHolder> {

    private int selectIndex = -1;
    private boolean hasJoin = false;

    public JoinActivityAdapter(Context context) {
        super(context);
    }

    public void setSelectIndex(int selectIndex) {
        this.selectIndex = selectIndex;
        notifyDataSetChanged();
    }

    public void setHasJoin(boolean hasJoin) {
        this.hasJoin = hasJoin;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder bindHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.item_join_activity;
    }

    @Override
    public void bindData(ViewHolder holder, int position, String itemData) {

//        holder.tvTime.setText(itemData.giveTime);

        if (hasJoin) {
            if (true) {
                holder.tvBookStatus.setVisibility(View.GONE);
                holder.ivChoose.setVisibility(View.VISIBLE);
            } else {
                holder.tvBookStatus.setText("已预约");
                holder.tvBookStatus.setVisibility(View.VISIBLE);
                holder.ivChoose.setVisibility(View.GONE);
            }

            if (selectIndex == position) {
                holder.ivChoose.setImageResource(R.mipmap.ic_launcher);
            } else {
                holder.ivChoose.setImageResource(R.mipmap.ic_launcher);
            }
        } else {
            holder.tvBookStatus.setText("不能预约");
            holder.tvBookStatus.setVisibility(View.VISIBLE);
            holder.ivChoose.setVisibility(View.GONE);
        }

    }

    @Override
    public void bindView(ViewHolder holder, View view, ViewGroup parent, int position) {

    }

    static class ViewHolder {
        @Bind(R.id.tvTime)
        TextView tvTime;

        @Bind(R.id.tvBookStatus)
        TextView tvBookStatus;

        @Bind(R.id.ivChoose)
        ImageView ivChoose;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
