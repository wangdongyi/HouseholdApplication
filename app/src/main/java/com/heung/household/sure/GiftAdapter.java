package com.heung.household.sure;

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
import com.heung.household.model.GiftBean;
import com.heung.household.model.GoodBean;

import java.util.ArrayList;

/**
 * 作者：王东一
 * 创建时间：2017/8/20.
 */

public class GiftAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<GoodBean> list = new ArrayList<>();

    public GiftAdapter(Context mContext, ArrayList<GoodBean> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        ViewHolder viewHolder = null;
        view = LayoutInflater.from(mContext).inflate(R.layout.adapter_gift_item, parent, false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @SuppressLint({"RecyclerView", "SetTextI18n"})
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.goodName.setText(list.get(position).getName());
        viewHolder.goodNum.setText(list.get(position).getSelectedNum()+"个");
        Glide.with(mContext)
                .load(list.get(position).getUrl())
                .centerCrop()
                .crossFade()
                .into(viewHolder.giftImage);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }




    private static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView goodName;
        public ImageView giftImage;
        public TextView goodNum;


        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.goodName = (TextView) rootView.findViewById(R.id.goodName);
            this.giftImage = (ImageView) rootView.findViewById(R.id.giftImage);
            this.goodNum = (TextView) rootView.findViewById(R.id.goodNum);
        }

    }


}
