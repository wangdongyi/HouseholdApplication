package com.heung.household.entry;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.library.application.BaseApplication;
import com.base.library.eventBus.MessageEvent;
import com.base.library.fragment.BaseFragment;
import com.base.library.listen.NoDoubleClickListener;
import com.base.library.okHtttpUtil.GenericsCallback;
import com.base.library.okHtttpUtil.OkHttpUtil;
import com.base.library.util.CodeUtil;
import com.base.library.util.DialogUtil;
import com.base.library.util.WDYLog;
import com.heung.household.R;
import com.heung.household.base.MainApplication;
import com.heung.household.http.UrlUtil;
import com.heung.household.listen.MoneyListen;
import com.heung.household.model.EntryBean;
import com.heung.household.model.EntryInputBean;
import com.heung.household.model.GoodBean;
import com.heung.household.model.SaveEntry;
import com.heung.household.model.SaveQuery;
import com.heung.household.model.getGiftList;
import com.heung.household.sure.SureActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import okhttp3.Request;

import static com.heung.household.http.UrlUtil.PhotoUrl;

/**
 * 作者：王东一
 * 创建时间：2017/8/20.
 * 录入
 */

public class EntryFragment extends BaseFragment {
    private boolean isBuild = false;
    private ArrayList<EntryBean> list = new ArrayList<>();
    private ArrayList<GoodBean> goodList = new ArrayList<>();
    private EntryAdapter entryAdapter;
    private GoodAdapter goodAdapter;
    private ImageView imageAdd;
    private RecyclerView entryRecycle;
    private TextView allMoney;
    private TextView phoneHint;
    private EditText phoneEdit;
    private TextView cardHint;
    private EditText cardEdit;
    private RecyclerView goodRecycle;
    private TextView updateBtn;
    private TextView cleanBtn;
    private ImageView refresh;
    private SaveEntry saveEntry = new SaveEntry();
    private Bundle savedIn;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setContentView(inflater, R.layout.fragment_entry);
        savedIn = savedInstanceState;
        if (!isViewShown && !isBuild) {
            lazyLoad();
        }
        initView(main);
        return main;
    }

    public void onDateBack(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            saveEntry = (SaveEntry) savedInstanceState.getSerializable("saveEntry");
        }
    }

    public void onDateSave(Bundle outState) {
        saveEntry.setList(list);
        saveEntry.setGoodList(goodList);
        saveEntry.setMoney(allMoney.getText().toString());
        saveEntry.setPhone(phoneEdit.getText().toString());
        saveEntry.setCard(cardEdit.getText().toString());
        outState.putSerializable("saveEntry", saveEntry);
    }

    @Override
    protected void init() {
        EventBus.getDefault().register(this);
        initView(main);
        imageAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //增加录入项
                if (list.size() <= 30) {
                    EntryBean entryBean = new EntryBean();
                    list.add(entryBean);
                    entryAdapter.notifyDataSetChanged();
                } else {
                    MainApplication.getToastUtil().showMiddleToast("录入项最多30条");
                }
            }
        });
        list = saveEntry.getList();
        goodList = saveEntry.getGoodList();
        allMoney.setText(saveEntry.getMoney());
        phoneEdit.setText(saveEntry.getPhone());
        cardEdit.setText(saveEntry.getCard());
        if (list.size() == 0)
            for (int i = 0; i < 1; i++) {
                EntryBean e = new EntryBean();
                list.add(e);
            }
        entryAdapter = new EntryAdapter(getActivity(), list);
        entryAdapter.setMoneyListen(new MoneyListen() {
            @Override
            public void upData() {
                double allMoneyNum = 0;
                for (int i = 0; i < list.size(); i++) {
                    allMoneyNum = list.get(i).getMoney() + allMoneyNum;
                }
                allMoney.setText(allMoneyNum + "");
            }
        });

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mLinearLayoutManager.setAutoMeasureEnabled(true);
        entryRecycle.setLayoutManager(mLinearLayoutManager);
        entryRecycle.setHasFixedSize(true);
        entryRecycle.setNestedScrollingEnabled(false);
        entryRecycle.setAdapter(entryAdapter);
        goodAdapter = new GoodAdapter(getActivity(), goodList);
        LinearLayoutManager mGridLayoutManager = new GridLayoutManager(getActivity(), 3);
        mGridLayoutManager.setAutoMeasureEnabled(true);
        goodRecycle.setLayoutManager(mGridLayoutManager);
        goodRecycle.setHasFixedSize(true);
        goodRecycle.setNestedScrollingEnabled(false);
        goodRecycle.setAdapter(goodAdapter);
        updateBtn.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                //提交按钮
                submit();
            }
        });
        cleanBtn.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                //清空按钮
                list.clear();
                list.add(new EntryBean());
                entryAdapter.notifyDataSetChanged();
                allMoney.setText("");
                getGiftList();
                phoneEdit.setText("");
                cardEdit.setText("");
            }
        });
        refresh.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                getGiftList();
            }
        });
        if (goodList.size() == 0) {
            getGiftList();
        }

    }

    @Override
    protected void lazyLoad() {
        isBuild = true;
        init();
    }

    private void initView(View main) {
        entryRecycle = (RecyclerView) main.findViewById(R.id.entryRecycle);
        goodRecycle = (RecyclerView) main.findViewById(R.id.goodRecycle);
        phoneHint = (TextView) main.findViewById(R.id.phoneHint);
        phoneEdit = (EditText) main.findViewById(R.id.phoneEdit);
        cardHint = (TextView) main.findViewById(R.id.cardHint);
        cardEdit = (EditText) main.findViewById(R.id.cardEdit);
        updateBtn = (TextView) main.findViewById(R.id.updateBtn);
        cleanBtn = (TextView) main.findViewById(R.id.cleanBtn);
        imageAdd = (ImageView) main.findViewById(R.id.imageAdd);
        entryRecycle = (RecyclerView) main.findViewById(R.id.entryRecycle);
        allMoney = (TextView) main.findViewById(R.id.allMoney);
        phoneHint = (TextView) main.findViewById(R.id.phoneHint);
        phoneEdit = (EditText) main.findViewById(R.id.phoneEdit);
        cardHint = (TextView) main.findViewById(R.id.cardHint);
        cardEdit = (EditText) main.findViewById(R.id.cardEdit);
        goodRecycle = (RecyclerView) main.findViewById(R.id.goodRecycle);
        updateBtn = (TextView) main.findViewById(R.id.updateBtn);
        cleanBtn = (TextView) main.findViewById(R.id.cleanBtn);
        refresh = (ImageView) main.findViewById(R.id.refresh);
    }

    private void submit() {
        // validate
        String phoneEditString = phoneEdit.getText().toString().trim();
        if (TextUtils.isEmpty(phoneEditString)) {
            phoneEditString = "";
        }

        String cardEditString = cardEdit.getText().toString().trim();
        if (TextUtils.isEmpty(cardEditString)) {
            cardEditString = "";
        }
        for (int i = 0; i < list.size(); i++) {
            if (TextUtils.isEmpty(list.get(i).getNumFirst()) || TextUtils.isEmpty(list.get(i).getNumSecond()) || list.get(i).getMoney() == 0) {
                MainApplication.getToastUtil().showMiddleToast("录入不能为空");
                return;
            }
        }
        ArrayList<GoodBean> selectedGoodList = new ArrayList<>();
        for (int i = 0; i < goodList.size(); i++) {
            if (goodList.get(i).getSelectedNum() > 0) {
                selectedGoodList.add(goodList.get(i));
            }
        }
        if (selectedGoodList.size() == 0) {
            MainApplication.getToastUtil().showMiddleToast("录入礼品不能为空");
            return;
        }
        EntryInputBean entryBean = new EntryInputBean();
        entryBean.setPhone(phoneEditString);
        entryBean.setCard(cardEditString);
        entryBean.setEntryBeanArrayList(list);
        entryBean.setGoodBeanArrayList(selectedGoodList);
        boolean isHave = false;
        for (int i = 0; i < entryBean.getEntryBeanArrayList().size(); i++) {
            for (int j = 0; j < entryBean.getEntryBeanArrayList().size(); j++) {
                if (i != j) {
                    String numi = entryBean.getEntryBeanArrayList().get(i).getNumFirst() + "-" + entryBean.getEntryBeanArrayList().get(i).getNumSecond();
                    String numj = entryBean.getEntryBeanArrayList().get(j).getNumFirst() + "-" + entryBean.getEntryBeanArrayList().get(j).getNumSecond();
                    if (numi.equals(numj)) {
                        isHave = true;
                    }
                }
            }
        }
        if (isHave) {
            MainApplication.getToastUtil().showMiddleToast("录入单号不能重复");
            return;
        }
        Intent intent = new Intent(getActivity(), SureActivity.class);
        intent.putExtra("EntryInputBean", entryBean);
        startActivity(intent);
    }

    private void getGiftList() {
        final Request request = new Request.Builder()
                .get()
                .tag(this)
                .url(UrlUtil.getGiftList)
                .build();
        OkHttpUtil.with(this, request, new GenericsCallback<getGiftList>() {

            @Override
            public void onResponse(getGiftList response) {
                switch (response.getStatus()) {
                    case "SUCCESS":
                        goodList.clear();
                        for (int i = 0; i < response.getDatas().size(); i++) {
                            GoodBean good = new GoodBean();
                            good.setId(response.getDatas().get(i).getID() + "");
                            good.setName(response.getDatas().get(i).getLPMC());
                            good.setSelectedNum(0);
                            good.setAllNum(CodeUtil.IntegerEmpty(response.getDatas().get(i).getCSKC()));
                            good.setUrl(PhotoUrl + response.getDatas().get(i).getLPZP());
                            goodList.add(good);
                        }
                        goodAdapter.notifyDataSetChanged();
                        break;
                    default:
                        BaseApplication.getToastUtil().showMiddleToast(response.getMsg());
                        break;
                }
            }

            @Override
            public boolean onBefore() {
                DialogUtil.show(getActivity());
                return true;
            }

            @Override
            public void onRequest(String response) {
                WDYLog.d("回掉", "回掉:" + response);

            }

            @Override
            public void onError(String msg) {
                WDYLog.d("错误", "错误:" + msg);
            }

            @Override
            public void onCancel() {
                Log.i("onSuccess", "取消:");
            }

            @Override
            public void onFinish() {
                Log.i("onSuccess", "结束:");
                DialogUtil.dismiss();
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoonEvent(MessageEvent messageEvent) {
        switch (messageEvent.getMessage()) {
            case "更新":
                list.clear();
                list.add(new EntryBean());
                entryAdapter.notifyDataSetChanged();
                allMoney.setText("");
                getGiftList();
                phoneEdit.setText("");
                cardEdit.setText("");
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
