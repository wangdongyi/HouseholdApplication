package com.heung.household.sure;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.heung.household.R;
import com.heung.household.entry.EntryAdapter;
import com.heung.household.model.EntryBean;
import com.heung.household.model.SureOrderBean;

import java.util.ArrayList;

/**
 * 作者：王东一
 * 创建时间：2017/8/20.
 */

public class SureOrderAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<SureOrderBean> list = new ArrayList<>();

    public SureOrderAdapter(Context mContext, ArrayList<SureOrderBean> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        ViewHolder viewHolder = null;
        view = LayoutInflater.from(mContext).inflate(R.layout.adapter_sure_order_item, parent, false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @SuppressLint({"RecyclerView", "SetTextI18n"})
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.textOne.setText(list.get(position).getNumFirst());
        viewHolder.textTwo.setText(list.get(position).getNumSecond());
        viewHolder.money.setText(list.get(position).getMoney()+"");
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
    

    private static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView textOne;
        public TextView textTwo;
        public TextView money;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.textOne = (TextView) rootView.findViewById(R.id.textOne);
            this.textTwo = (TextView) rootView.findViewById(R.id.textTwo);
            this.money = (TextView) rootView.findViewById(R.id.money);
        }

    }

    
}
