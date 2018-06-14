package com.heung.household.change;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.heung.household.R;
import com.heung.household.model.GoodBean;

import java.util.ArrayList;

/**
 * 作者：王东一
 * 创建时间：2017/8/30.
 */

public class ChangeGoodAdapter extends  RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<GoodBean> list = new ArrayList<>();

    public ChangeGoodAdapter(Context mContext, ArrayList<GoodBean> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        ViewHolder viewHolder = null;
        view = LayoutInflater.from(mContext).inflate(R.layout.adapter_good_item, parent, false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @SuppressLint({"RecyclerView", "SetTextI18n"})
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.goodName.setText(list.get(position).getName());
        viewHolder.goodNum.setText(list.get(position).getAllNum() + "个");
        viewHolder.selectedNum.setText(list.get(position).getSelectedNum() + "");
        Glide.with(mContext)
                .load(list.get(position).getUrl())
                .centerCrop()
                .crossFade()
                .into(viewHolder.goodImage);
        if (0 == list.get(position).getMax()) {
            //没有库存
            viewHolder.imageSubtraction.setBackgroundResource(R.drawable.oval_grey);
            viewHolder.imageAdd.setBackgroundResource(R.drawable.oval_grey);
            list.get(position).setCanSubtraction(false);
            list.get(position).setCanAdd(false);
        } else if (list.get(position).getMax() == list.get(position).getSelectedNum()) {
            //最大
            viewHolder.imageSubtraction.setBackgroundResource(R.drawable.oval_blue);
            viewHolder.imageAdd.setBackgroundResource(R.drawable.oval_grey);
            list.get(position).setCanSubtraction(true);
            list.get(position).setCanAdd(false);
        } else if (list.get(position).getSelectedNum() == 0) {
            //最小
            viewHolder.imageSubtraction.setBackgroundResource(R.drawable.oval_grey);
            viewHolder.imageAdd.setBackgroundResource(R.drawable.oval_blue);
            list.get(position).setCanSubtraction(false);
            list.get(position).setCanAdd(true);
        } else if (list.get(position).getSelectedNum() < list.get(position).getMax()) {
            //可选
            viewHolder.imageSubtraction.setBackgroundResource(R.drawable.oval_blue);
            viewHolder.imageAdd.setBackgroundResource(R.drawable.oval_blue);
            list.get(position).setCanSubtraction(true);
            list.get(position).setCanAdd(true);
        } else {
            //异常
            viewHolder.imageSubtraction.setBackgroundResource(R.drawable.oval_grey);
            viewHolder.imageAdd.setBackgroundResource(R.drawable.oval_grey);
            list.get(position).setCanSubtraction(false);
            list.get(position).setCanAdd(false);
        }
        viewHolder.imageSubtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (list.get(position).isCanSubtraction()) {
                    int selected = list.get(position).getSelectedNum() - 1;
                    list.get(position).setSelectedNum(selected);
                    notifyDataSetChanged();
                }
            }
        });
        viewHolder.imageAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (list.get(position).isCanAdd()) {
                    int selected = list.get(position).getSelectedNum() + 1;
                    list.get(position).setSelectedNum(selected);
                    notifyDataSetChanged();
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    private static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView goodName;
        public TextView goodNum;
        public ImageView goodImage;
        public ImageView imageSubtraction;
        public TextView selectedNum;
        public ImageView imageAdd;


        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.goodName = (TextView) rootView.findViewById(R.id.goodName);
            this.goodNum = (TextView) rootView.findViewById(R.id.goodNum);
            this.goodImage = (ImageView) rootView.findViewById(R.id.goodImage);
            this.imageSubtraction = (ImageView) rootView.findViewById(R.id.imageSubtraction);
            this.selectedNum = (TextView) rootView.findViewById(R.id.selectedNum);
            this.imageAdd = (ImageView) rootView.findViewById(R.id.imageAdd);
        }
    }


}