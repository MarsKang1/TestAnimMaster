package com.testanimmaster.common;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.testanimmaster.R;

import java.util.List;

/**
 * Created by Administrator on 2017/5/19.
 */
public class StringItemAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private Context context;
    private List<String> dates;
    private RecycleViewCallback callback;

    public StringItemAdapter(Context context, List<String> dates) {
        this.context = context;
        this.dates = dates;
    }

    public void setItemClickCallback(RecycleViewCallback callback) {
        this.callback = callback;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv, parent, false);
        return new BaseViewHolder(context, viewGroup);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.setText(R.id.tv_content, dates.get(position)); //绑定数据
        holder.getView(R.id.tv_content).setOnClickListener(new MyClickListener(position));//设置点击事件
    }

    @Override
    public int getItemCount() {
        return dates.size();
    }

    private class MyClickListener implements View.OnClickListener {
        int position;

        public MyClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            if (callback != null) {
                callback.onItemClick(v, position);
            }
        }
    }
}
