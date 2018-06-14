package com.heung.household.change;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.library.activity.BaseActivity;
import com.base.library.application.BaseApplication;
import com.base.library.eventBus.MessageEvent;
import com.base.library.listen.NoDoubleClickListener;
import com.base.library.okHtttpUtil.GenericsCallback;
import com.base.library.okHtttpUtil.OkHttpUtil;
import com.base.library.util.CodeUtil;
import com.base.library.util.DialogUtil;
import com.base.library.util.WDYLog;
import com.bumptech.glide.Glide;
import com.heung.household.R;
import com.heung.household.base.MainApplication;
import com.heung.household.entry.EntryAdapter;
import com.heung.household.entry.GoodAdapter;
import com.heung.household.http.UrlUtil;
import com.heung.household.listen.MoneyListen;
import com.heung.household.model.EntryBean;
import com.heung.household.model.EntryInputBean;
import com.heung.household.model.GiftInfoBack;
import com.heung.household.model.GoodBean;
import com.heung.household.model.NoDataBack;
import com.heung.household.model.QueryBean;
import com.heung.household.model.SaveChangeActivity;
import com.heung.household.model.SearchInfoBean;
import com.heung.household.model.ToMainBackBean;
import com.heung.household.model.getGiftList;
import com.heung.household.see.SeeActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;

import static com.heung.household.http.UrlUtil.PhotoUrl;

/**
 * 作者：王东一
 * 创建时间：2017/8/20.
 * 修改订单
 */

public class ChangeActivity extends BaseActivity {
    private RecyclerView entryRecycle;
    private TextView phoneHint;
    private EditText phoneEdit;
    private TextView cardHint;
    private EditText cardEdit;
    private RecyclerView goodRecycle;
    private TextView updateBtn;
    private ArrayList<EntryBean> list = new ArrayList<>();
    private ArrayList<GoodBean> goodList = new ArrayList<>();
    private EntryAdapter changeAdapter;
    private ChangeGoodAdapter goodAdapter;
    private ImageView imageAdd;
    private TextView allMoney;
    private ImageView refresh;
    private QueryBean queryBean;
    private EntryInputBean entryInputBean;
    private SaveChangeActivity saveChangeActivity;
    private String MainId;
    private String url;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    getInfo(MainId);
                    getGift(MainId);
                    break;
            }
            super.handleMessage(msg);
        }
    };
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        initView();
        setTitleName("修改订单");
        setCanSwipe(false);
        setStatusBar(true);
        queryBean = (QueryBean) getIntent().getSerializableExtra("QueryBean");
        saveChangeActivity = new SaveChangeActivity();
        phoneEdit.setText(queryBean.getPhone());
        cardEdit.setText(queryBean.getCard());
        allMoney.setText(queryBean.getMoney());
        if (savedInstanceState != null) {
            saveChangeActivity = (SaveChangeActivity) savedInstanceState.getSerializable("saveChangeActivity");
            list = saveChangeActivity.getList();
            queryBean = saveChangeActivity.getQueryBean();
            goodList = saveChangeActivity.getGoodList();
            allMoney.setText(saveChangeActivity.getAllMoney());
            phoneEdit.setText(saveChangeActivity.getPhone());
            cardEdit.setText(saveChangeActivity.getCard());
            url=saveChangeActivity.getUrl();
            Glide.with(ChangeActivity.this)
                    .load(url)
                    .centerCrop()
                    .crossFade()
                    .into(imageView);
        } else {
            getDate();
        }
        init();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveChangeActivity.setList(list);
        saveChangeActivity.setAllMoney(allMoney.getText().toString());
        saveChangeActivity.setPhone(phoneEdit.getText().toString());
        saveChangeActivity.setCard(cardEdit.getText().toString());
        saveChangeActivity.setGoodList(goodList);
        saveChangeActivity.setQueryBean(queryBean);
        saveChangeActivity.setUrl(url);
        outState.putSerializable("saveChangeActivity", saveChangeActivity);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    private void init() {
        entryInputBean = new EntryInputBean();

        imageAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //增加录入项
                if (list.size() <= 30) {
                    EntryBean e = new EntryBean();
                    list.add(e);
                    changeAdapter.notifyDataSetChanged();
                } else {
                    MainApplication.getToastUtil().showMiddleToast("录入项最多30条");
                }
            }
        });
        changeAdapter = new EntryAdapter(this, list);
        changeAdapter.setMoneyListen(new MoneyListen() {
            @Override
            public void upData() {
                double allMoneyNum = 0;
                for (int i = 0; i < list.size(); i++) {
                    allMoneyNum = list.get(i).getMoney() + allMoneyNum;
                }
                allMoney.setText(allMoneyNum + "");
            }
        });
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setAutoMeasureEnabled(true);
        entryRecycle.setLayoutManager(mLinearLayoutManager);
        entryRecycle.setHasFixedSize(true);
        entryRecycle.setNestedScrollingEnabled(false);
        entryRecycle.setAdapter(changeAdapter);

        goodAdapter = new ChangeGoodAdapter(this, goodList);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(this, 3);
        mGridLayoutManager.setAutoMeasureEnabled(true);
        goodRecycle.setLayoutManager(mGridLayoutManager);
        goodRecycle.setHasFixedSize(true);
        goodRecycle.setNestedScrollingEnabled(false);
        goodRecycle.setAdapter(goodAdapter);
        updateBtn.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                submit();
            }
        });
        refresh.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                getGift(MainId);
            }
        });

    }

    private void getDate() {
        getMain(queryBean.getId());
    }

    private void initView() {
        entryRecycle = (RecyclerView) findViewById(R.id.entryRecycle);
        phoneHint = (TextView) findViewById(R.id.phoneHint);
        phoneEdit = (EditText) findViewById(R.id.phoneEdit);
        cardHint = (TextView) findViewById(R.id.cardHint);
        cardEdit = (EditText) findViewById(R.id.cardEdit);
        goodRecycle = (RecyclerView) findViewById(R.id.goodRecycle);
        updateBtn = (TextView) findViewById(R.id.updateBtn);
        imageAdd = (ImageView) findViewById(R.id.imageAdd);
        allMoney = (TextView) findViewById(R.id.allMoney);
        refresh = (ImageView) findViewById(R.id.refresh);
        imageView = (ImageView) findViewById(R.id.imageView);
    }

    private void submit() {
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
        up();
    }

    private void getMain(String id) {
        final Request request = new Request.Builder()
                .get()
                .tag(this)
                .url(UrlUtil.toMainView + "id=" + id)
                .build();
        OkHttpUtil.with(this, request, new GenericsCallback<ToMainBackBean>() {

            @Override
            public void onResponse(ToMainBackBean response) {
                BaseApplication.getToastUtil().showMiddleToast(response.getMsg());
                switch (response.getStatus()) {
                    case "SUCCESS":
                        MainId = response.getDatas().getID() + "";
                        handler.sendEmptyMessage(1);
                        url = UrlUtil.PhotoUrl + response.getDatas().getQM();
                        Glide.with(ChangeActivity.this)
                                .load(url)
                                .centerCrop()
                                .crossFade()
                                .into(imageView);
                        break;
                    default:
                        BaseApplication.getToastUtil().showMiddleToast(response.getMsg());
                        break;
                }
            }

            @Override
            public boolean onBefore() {
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
            }
        });
    }

    private void getInfo(String id) {
        final Request request = new Request.Builder()
                .get()
                .tag(this)
                .url(UrlUtil.toChildView + "id=" + id)
                .build();
        OkHttpUtil.with(this, request, new GenericsCallback<SearchInfoBean>() {

            @Override
            public void onResponse(SearchInfoBean response) {
                BaseApplication.getToastUtil().showMiddleToast(response.getMsg());
                switch (response.getStatus()) {
                    case "SUCCESS":
                        for (int i = 0; i < response.getDatas().size(); i++) {
                            EntryBean sureOrderBean = new EntryBean();
                            if (!CodeUtil.isEmpty(response.getDatas().get(i).getDH())) {
                                String[] num = response.getDatas().get(i).getDH().split("-");
                                if (num != null && num.length == 2) {
                                    sureOrderBean.setNumFirst(num[0]);
                                    sureOrderBean.setNumSecond(num[1]);
                                }
                            }
                            sureOrderBean.setMoney(response.getDatas().get(i).getJE());
                            list.add(sureOrderBean);
                            changeAdapter.notifyDataSetChanged();
                        }
                        break;
                    default:
                        BaseApplication.getToastUtil().showMiddleToast(response.getMsg());
                        break;
                }
            }

            @Override
            public boolean onBefore() {
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
            }
        });
    }

    private void getGift(String id) {
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
                            good.setMax(CodeUtil.IntegerEmpty(response.getDatas().get(i).getCSKC()));
                            good.setUrl(PhotoUrl + response.getDatas().get(i).getLPZP());
                            goodList.add(good);
                        }
                        goodAdapter.notifyDataSetChanged();
                        getGiftInfo(MainId);
                        break;
                    default:
                        BaseApplication.getToastUtil().showMiddleToast(response.getMsg());
                        break;
                }
            }

            @Override
            public boolean onBefore() {
                DialogUtil.show(ChangeActivity.this);
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

    private void getGiftInfo(String id) {
        WDYLog.i("UrlUtil.getGift", id);
        final Request request = new Request.Builder()
                .get()
                .tag(this)
                .url(UrlUtil.getGift + "id=" + id)
                .build();
        OkHttpUtil.with(this, request, new GenericsCallback<GiftInfoBack>() {

            @Override
            public void onResponse(GiftInfoBack response) {
                BaseApplication.getToastUtil().showMiddleToast(response.getMsg());
                switch (response.getStatus()) {
                    case "SUCCESS":
                        for (int i = 0; i < goodList.size(); i++) {
                            for (int j = 0; j < response.getDatas().size(); j++) {
                                if (goodList.get(i).getId().equals(response.getDatas().get(j).getLPID() + "")) {
                                    goodList.get(i).setSelectedNum(response.getDatas().get(j).getNUM());
                                    int num = goodList.get(i).getAllNum() + response.getDatas().get(j).getNUM();
                                    goodList.get(i).setMax(num);
                                }
                            }
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
            }
        });
    }

    private void up() {
        entryInputBean.setCard(cardEdit.getText().toString());
        entryInputBean.setPhone(phoneEdit.getText().toString());
        entryInputBean.setEntryBeanArrayList(list);
        entryInputBean.setGoodBeanArrayList(goodList);
        String DH = "";
        String JE = "";
        String LPID = "";
        String SL = "";
        for (int i = 0; i < entryInputBean.getEntryBeanArrayList().size(); i++) {
            if (i == 0) {
                DH = entryInputBean.getEntryBeanArrayList().get(i).getNumFirst() + "-" + entryInputBean.getEntryBeanArrayList().get(i).getNumSecond();
                JE = entryInputBean.getEntryBeanArrayList().get(i).getMoney() + "";
            } else {
                DH = DH + "," + entryInputBean.getEntryBeanArrayList().get(i).getNumFirst() + "-" + entryInputBean.getEntryBeanArrayList().get(i).getNumSecond();
                JE = JE + "," + entryInputBean.getEntryBeanArrayList().get(i).getMoney();
            }
        }
        for (int i = 0; i < entryInputBean.getGoodBeanArrayList().size(); i++) {
            if (i == 0) {
                LPID = entryInputBean.getGoodBeanArrayList().get(i).getId();
                SL = entryInputBean.getGoodBeanArrayList().get(i).getSelectedNum() + "";
            } else {
                LPID = LPID + "," + entryInputBean.getGoodBeanArrayList().get(i).getId();
                SL = SL + "," + entryInputBean.getGoodBeanArrayList().get(i).getSelectedNum();
            }
        }
        boolean isHave = false;
        for (int i = 0; i < entryInputBean.getEntryBeanArrayList().size(); i++) {
            for (int j = 0; j < entryInputBean.getEntryBeanArrayList().size(); j++) {
                if (i != j) {
                    String numi = entryInputBean.getEntryBeanArrayList().get(i).getNumFirst() + "-" + entryInputBean.getEntryBeanArrayList().get(i).getNumSecond();
                    String numj = entryInputBean.getEntryBeanArrayList().get(j).getNumFirst() + "-" + entryInputBean.getEntryBeanArrayList().get(j).getNumSecond();
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
        RequestBody requestBody = new FormBody.Builder()
                .add("id", MainId)
                .add("DH", DH)
                .add("JE", JE)
                .add("SFZH", entryInputBean.getCard())
                .add("LXFS", entryInputBean.getPhone())
                .add("moneyAll", allMoney.getText().toString() + "")
                .add("LPID", LPID)
                .add("SL", SL)
                .add("rybId", MainApplication.getLoginBean().getDatas().getID() + "")
                .build();
        WDYLog.i("录入数据：","id"+MainId+"DH" + DH + "\nJE" + JE + "\nSFZH" + entryInputBean.getCard() + "\nLXFS" + entryInputBean.getPhone() + "\nmoneyAll" + allMoney.getText().toString() + "\nLPID" + LPID + "\nSL" + SL + "\nrybId" + MainApplication.getLoginBean().getDatas().getID() + "");

        final Request request = new Request.Builder()
                .post(requestBody)
                .tag(this)
                .url(UrlUtil.changeOrders)
                .build();
        OkHttpUtil.with(this, request, new GenericsCallback<NoDataBack>() {

            @Override
            public void onResponse(NoDataBack response) {
                if (response != null)
                    BaseApplication.getToastUtil().showMiddleToast(response.getMsg() + "");

            }

            @Override
            public boolean onBefore() {
                DialogUtil.show(ChangeActivity.this);
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
                EventBus.getDefault().post(new MessageEvent("刷新"));
                onBackPressed();
            }
        });
    }
}
