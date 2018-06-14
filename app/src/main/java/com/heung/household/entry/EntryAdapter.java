package com.heung.household.entry;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.base.library.listen.NoDoubleClickListener;
import com.base.library.util.CodeUtil;
import com.heung.household.R;
import com.heung.household.base.MainApplication;
import com.heung.household.listen.MoneyListen;
import com.heung.household.model.EntryBean;

import java.util.ArrayList;

/**
 * 作者：王东一
 * 创建时间：2017/8/20.
 */

public class EntryAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<EntryBean> list = new ArrayList<>();
    private MoneyListen moneyListen;

    public MoneyListen getMoneyListen() {
        return moneyListen;
    }

    public void setMoneyListen(MoneyListen moneyListen) {
        this.moneyListen = moneyListen;
    }

    public EntryAdapter(Context mContext, ArrayList<EntryBean> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        ViewHolder viewHolder = null;
        view = LayoutInflater.from(mContext).inflate(R.layout.adapter_entry_item, parent, false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @SuppressLint({"RecyclerView", "SetTextI18n"})
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.entryMoney.setTag(position);
        viewHolder.entryOne.setTag(position);
        viewHolder.entryTwo.setTag(position);
        viewHolder.entryMoney.setText((list.get(position).getMoney() == 0 ? "" : list.get(position).getMoney()) + "");
        viewHolder.entryOne.setText(list.get(position).getNumFirst());
        viewHolder.entryTwo.setText(list.get(position).getNumSecond());
        final int p = position;
        viewHolder.subtractionBtn.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (list.size() == 1) {
                    MainApplication.getToastUtil().showMiddleToast("录入不能为空");
                } else {
                    list.remove(p);
                    notifyDataSetChanged();
                }
            }
        });
        viewHolder.entryOne.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (p == (int) viewHolder.entryOne.getTag())
                    list.get(p).setNumFirst(viewHolder.entryOne.getText().toString());
            }
        });
        viewHolder.entryTwo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (p == (int) viewHolder.entryTwo.getTag())
                    list.get(p).setNumSecond(viewHolder.entryTwo.getText().toString());
            }
        });
        viewHolder.entryMoney.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = s.toString();
                if (text.contains(".")) {
                    int index = text.indexOf(".");
                    if (index + 3 < text.length()) {
                        text = text.substring(0, index + 3);
                        viewHolder.entryMoney.setText(text);
                        viewHolder.entryMoney.setSelection(text.length());
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //

            }

            @Override
            public void afterTextChanged(Editable s) {
                //
                if ((int) viewHolder.entryMoney.getTag() == p) {
                    if (CodeUtil.isEmpty(viewHolder.entryMoney.getText().toString()))
                        list.get(p).setMoney(0);
                    else
                        list.get(p).setMoney(Double.parseDouble(viewHolder.entryMoney.getText().toString()));
                    if (getMoneyListen() != null) {
                        getMoneyListen().upData();
                    }
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
        public EditText entryOne;
        public ImageView subtraction;
        public EditText entryTwo;
        public EditText entryMoney;
        public ImageView subtractionBtn;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.entryOne = (EditText) rootView.findViewById(R.id.entry_one);
            this.subtraction = (ImageView) rootView.findViewById(R.id.subtraction);
            this.entryTwo = (EditText) rootView.findViewById(R.id.entry_two);
            this.entryMoney = (EditText) rootView.findViewById(R.id.entry_money);
            this.subtractionBtn = (ImageView) rootView.findViewById(R.id.subtractionBtn);
        }
    }


}
