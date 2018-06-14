package com.heung.household.query;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.library.listen.NoDoubleClickListener;
import com.base.library.listen.OnRecyclerClickListen;
import com.heung.household.R;
import com.heung.household.change.ChangeActivity;
import com.heung.household.model.QueryBean;
import com.heung.household.see.SeeActivity;

import java.util.ArrayList;

/**
 * 作者：王东一
 * 创建时间：2017/8/20.
 */

public class QueryAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<QueryBean> list = new ArrayList<>();
    private OnRecyclerClickListen onRecyclerClickListen;

    public OnRecyclerClickListen getOnRecyclerClickListen() {
        return onRecyclerClickListen;
    }

    public void setOnRecyclerClickListen(OnRecyclerClickListen onRecyclerClickListen) {
        this.onRecyclerClickListen = onRecyclerClickListen;
    }

    public QueryAdapter(Context mContext, ArrayList<QueryBean> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        ViewHolder viewHolder = null;
        view = LayoutInflater.from(mContext).inflate(R.layout.adapter_query_item, parent, false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @SuppressLint({"RecyclerView", "SetTextI18n"})
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.time.setText(list.get(position).getTime().substring(5));
        viewHolder.num.setText(list.get(position).getNumOne());
        viewHolder.phone.setText(list.get(position).getPhone());
        viewHolder.money.setText(list.get(position).getMoney());
        viewHolder.look.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                Intent intent = new Intent(mContext, SeeActivity.class);
                intent.putExtra("QueryBean", list.get(position));
                mContext.startActivity(intent);
            }
        });
        viewHolder.change.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                Intent intent = new Intent(mContext, ChangeActivity.class);
                intent.putExtra("QueryBean", list.get(position));
                mContext.startActivity(intent);
            }
        });
        viewHolder.delete.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (getOnRecyclerClickListen() != null) {
                    getOnRecyclerClickListen().onClick(position);
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
        public TextView time;
        public TextView num;
        public TextView phone;
        public TextView money;
        public TextView look;
        public TextView change;
        public TextView delete;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.time = (TextView) rootView.findViewById(R.id.time);
            this.num = (TextView) rootView.findViewById(R.id.num);
            this.phone = (TextView) rootView.findViewById(R.id.phone);
            this.money = (TextView) rootView.findViewById(R.id.money);
            this.look = (TextView) rootView.findViewById(R.id.look);
            this.change = (TextView) rootView.findViewById(R.id.change);
            this.delete = (TextView) rootView.findViewById(R.id.delete);
        }

    }
}
