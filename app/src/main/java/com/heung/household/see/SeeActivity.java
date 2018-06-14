package com.heung.household.see;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.library.activity.BaseActivity;
import com.base.library.application.BaseApplication;
import com.base.library.eventBus.MessageEvent;
import com.base.library.okHtttpUtil.GenericsCallback;
import com.base.library.okHtttpUtil.OkHttpUtil;
import com.base.library.util.CodeUtil;
import com.base.library.util.WDYLog;
import com.bumptech.glide.Glide;
import com.heung.household.R;
import com.heung.household.change.ChangeActivity;
import com.heung.household.http.UrlUtil;
import com.heung.household.model.EntryBean;
import com.heung.household.model.GiftInfoBack;
import com.heung.household.model.GoodBean;
import com.heung.household.model.QueryBean;
import com.heung.household.model.SaveChangeActivity;
import com.heung.household.model.SaveSeeActivity;
import com.heung.household.model.SearchInfoBean;
import com.heung.household.model.SureOrderBean;
import com.heung.household.model.ToMainBackBean;
import com.heung.household.model.getGiftList;
import com.heung.household.sure.GiftAdapter;
import com.heung.household.sure.SureOrderAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import okhttp3.Request;

import static com.heung.household.http.UrlUtil.PhotoUrl;

/**
 * 作者：王东一
 * 创建时间：2017/8/20.
 */

public class SeeActivity extends BaseActivity {
    private TextView hint;
    private RecyclerView Recycle;
    private View line;
    private TextView money;
    private TextView phone;
    private TextView card;
    private RecyclerView giftRecycle;
    private ImageView imageView;
    private TextView changeBtn;
    private QueryBean queryBean;
    private SureOrderAdapter sureOrderAdapter;
    private GiftAdapter giftAdapter;
    private SaveSeeActivity save;
    private ArrayList<SureOrderBean> list = new ArrayList<>();
    private ArrayList<GoodBean> giftList = new ArrayList<>();
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see);
        initView();
        if (savedInstanceState != null) {
            save = (SaveSeeActivity) savedInstanceState.getSerializable("save");
            list = save.getList();
            queryBean = save.getQueryBean();
            giftList = save.getGiftList();
            money.setText(save.getAllMoney());
            phone.setText(save.getPhone());
            card.setText(save.getCard());
            url = save.getUrl();
            Glide.with(SeeActivity.this)
                    .load(url)
                    .centerCrop()
                    .crossFade()
                    .into(imageView);
        } else {
            getMain(queryBean.getId());
        }
        init();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        save.setList(list);
        save.setAllMoney(money.getText().toString());
        save.setPhone(phone.getText().toString());
        save.setCard(card.getText().toString());
        save.setGiftList(giftList);
        save.setQueryBean(queryBean);
        save.setUrl(url);
        outState.putSerializable("save", save);
    }

    private void init() {
        sureOrderAdapter = new SureOrderAdapter(this, list);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setAutoMeasureEnabled(true);
        Recycle.setLayoutManager(mLinearLayoutManager);
        Recycle.setHasFixedSize(true);
        Recycle.setNestedScrollingEnabled(false);
        Recycle.setAdapter(sureOrderAdapter);
        giftAdapter = new GiftAdapter(this, giftList);
        LinearLayoutManager giftManager = new LinearLayoutManager(this);
        giftManager.setAutoMeasureEnabled(true);
        giftRecycle.setLayoutManager(giftManager);
        giftRecycle.setHasFixedSize(true);
        giftRecycle.setNestedScrollingEnabled(false);
        giftRecycle.setAdapter(giftAdapter);
        changeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SeeActivity.this, ChangeActivity.class);
                intent.putExtra("QueryBean", queryBean);
                startActivity(intent);
            }
        });

    }

    private void initView() {
        hint = (TextView) findViewById(R.id.hint);
        Recycle = (RecyclerView) findViewById(R.id.Recycle);
        line = (View) findViewById(R.id.line);
        money = (TextView) findViewById(R.id.money);
        phone = (TextView) findViewById(R.id.phone);
        card = (TextView) findViewById(R.id.card);
        giftRecycle = (RecyclerView) findViewById(R.id.giftRecycle);
        imageView = (ImageView) findViewById(R.id.imageView);
        changeBtn = (TextView) findViewById(R.id.changeBtn);
        setTitleName("查看订单");
        setCanSwipe(false);
        setStatusBar(true);
        EventBus.getDefault().register(this);
        queryBean = (QueryBean) getIntent().getSerializableExtra("QueryBean");
        phone.setText(queryBean.getPhone());
        card.setText(queryBean.getCard());
        money.setText(queryBean.getMoney());
        save = new SaveSeeActivity();
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
                        Glide.with(SeeActivity.this)
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
                            SureOrderBean sureOrderBean = new SureOrderBean();
                            if (!CodeUtil.isEmpty(response.getDatas().get(i).getDH())) {
                                String[] num = response.getDatas().get(i).getDH().split("-");
                                if (num != null && num.length == 2) {
                                    sureOrderBean.setNumFirst(num[0]);
                                    sureOrderBean.setNumSecond(num[1]);
                                }
                            }
                            sureOrderBean.setMoney(response.getDatas().get(i).getJE());
                            list.add(sureOrderBean);
                            sureOrderAdapter.notifyDataSetChanged();
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
                .url(UrlUtil.getGift + "id=" + id)
                .build();
        OkHttpUtil.with(this, request, new GenericsCallback<GiftInfoBack>() {

            @Override
            public void onResponse(GiftInfoBack response) {
                BaseApplication.getToastUtil().showMiddleToast(response.getMsg());
                switch (response.getStatus()) {
                    case "SUCCESS":
                        giftList.clear();
                        for (int i = 0; i < response.getDatas().size(); i++) {
                            GoodBean goodBean = new GoodBean();
                            goodBean.setId(response.getDatas().get(i).getID() + "");
                            goodBean.setSelectedNum(response.getDatas().get(i).getNUM());
                            goodBean.setAllNum(response.getDatas().get(i).getCNUM());
                            goodBean.setName(response.getDatas().get(i).getLPMC());
                            goodBean.setUrl(PhotoUrl + response.getDatas().get(i).getLPZP());
                            giftList.add(goodBean);
                        }
                        giftAdapter.notifyDataSetChanged();
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoonEvent(MessageEvent messageEvent) {
        switch (messageEvent.getMessage()) {
            case "刷新":
                getMain(queryBean.getId());
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
