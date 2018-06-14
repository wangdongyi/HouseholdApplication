package com.heung.household.sure;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.base.library.activity.BaseActivity;
import com.base.library.application.BaseApplication;
import com.base.library.eventBus.MessageEvent;
import com.base.library.listen.NoDoubleClickListener;
import com.base.library.okHtttpUtil.GenericsCallback;
import com.base.library.okHtttpUtil.OkHttpUtil;
import com.base.library.photopicker.utils.PhotoUtils;
import com.base.library.util.DialogUtil;
import com.base.library.util.MD5Util;
import com.base.library.util.WDYJsonUtil;
import com.base.library.util.WDYLog;
import com.base.library.view.papyrus.PapyrusView;
import com.heung.household.R;
import com.heung.household.base.MainApplication;
import com.heung.household.http.UrlUtil;
import com.heung.household.login.LoginActivity;
import com.heung.household.main.MainActivity;
import com.heung.household.model.EntryInputBean;
import com.heung.household.model.GiftBean;
import com.heung.household.model.GoodBean;
import com.heung.household.model.LoginBean;
import com.heung.household.model.NoDataBack;
import com.heung.household.model.SaveSure;
import com.heung.household.model.SureOrderBean;
import com.heung.household.util.PhotoUtil;
import com.heung.household.view.SignatureView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * 作者：王东一
 * 创建时间：2017/8/20.
 */

public class SureActivity extends BaseActivity {
    private TextView hint;
    private RecyclerView Recycle;
    private View line;
    private TextView money;
    private TextView phone;
    private TextView card;
    private RecyclerView giftRecycle;
    private TextView updateBtn;
    private TextView backBtn;
    private SureOrderAdapter sureOrderAdapter;
    private ArrayList<SureOrderBean> list = new ArrayList<>();
    private ArrayList<GoodBean> giftList = new ArrayList<>();
    private SaveSure saveSure;
    private TextView hintSignature;
    private SignatureView signatureView;
    private ImageView delete;
    private EntryInputBean entryInputBean;
    private double allMoney = 0;
    private Bitmap tBitmap;
    private boolean isSign = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sure);
        initView();
        setTitleName("确认订单");
        setCanSwipe(false);
        setStatusBar(true);
        saveSure=new SaveSure();
        entryInputBean = (EntryInputBean) getIntent().getSerializableExtra("EntryInputBean");
        if (savedInstanceState != null) {
            entryInputBean = (EntryInputBean) savedInstanceState.getSerializable("entryInputBean");
            saveSure= (SaveSure) savedInstanceState.getSerializable("saveSure");
            signatureView.setPathList(saveSure.getPathList());
            signatureView.setPathNextList(saveSure.getPathNextList());
            signatureView.setOnDownPosition(saveSure.getOnDownPosition());
        }
        init();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveSure.setPathList(signatureView.getPathList());
        saveSure.setPathNextList(signatureView.getPathNextList());
        saveSure.setOnDownPosition(signatureView.getOnDownPosition());
        outState.putSerializable("entryInputBean", entryInputBean);
        outState.putSerializable("saveSure", saveSure);
    }

    private void init() {
        phone.setText(entryInputBean.getPhone());
        card.setText(entryInputBean.getCard());
        sureOrderAdapter = new SureOrderAdapter(this, list);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setAutoMeasureEnabled(true);
        Recycle.setLayoutManager(mLinearLayoutManager);
        Recycle.setHasFixedSize(true);
        Recycle.setNestedScrollingEnabled(false);
        Recycle.setAdapter(sureOrderAdapter);
        for (int i = 0; i < entryInputBean.getEntryBeanArrayList().size(); i++) {
            SureOrderBean s = new SureOrderBean();
            allMoney = allMoney + entryInputBean.getEntryBeanArrayList().get(i).getMoney();
            s.setMoney(entryInputBean.getEntryBeanArrayList().get(i).getMoney());
            s.setNumFirst(entryInputBean.getEntryBeanArrayList().get(i).getNumFirst());
            s.setNumSecond(entryInputBean.getEntryBeanArrayList().get(i).getNumSecond());
            list.add(s);
        }
        sureOrderAdapter.notifyDataSetChanged();
        money.setText(allMoney + "");
        GiftAdapter giftAdapter = new GiftAdapter(this, giftList);
        LinearLayoutManager giftManager = new LinearLayoutManager(this);
        giftManager.setAutoMeasureEnabled(true);
        giftRecycle.setLayoutManager(giftManager);
        giftRecycle.setHasFixedSize(true);
        giftRecycle.setNestedScrollingEnabled(false);
        giftRecycle.setAdapter(giftAdapter);
        for (int i = 0; i < entryInputBean.getGoodBeanArrayList().size(); i++) {
            if (entryInputBean.getGoodBeanArrayList().get(i).getSelectedNum() > 0)
                giftList.add(entryInputBean.getGoodBeanArrayList().get(i));
        }
        giftAdapter.notifyDataSetChanged();

        delete.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                signatureView.clean();
            }
        });
        signatureView.setOnListen(new PapyrusView.onListen() {
            @Override
            public void canClean(boolean b) {
                isSign = b;
                if (b) {
                    delete.setVisibility(View.VISIBLE);
                    hintSignature.setVisibility(View.GONE);
                } else {
                    delete.setVisibility(View.GONE);
                    hintSignature.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void canBack(boolean b) {

            }

            @Override
            public void canNext(boolean b) {

            }
        });
        updateBtn.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                signatureView.setDrawingCacheEnabled(true);
                tBitmap = signatureView.getDrawingCache();
                // 拷贝图片，否则在setDrawingCacheEnabled(false)以后该图片会被释放掉
                tBitmap = tBitmap.createBitmap(tBitmap);
                signatureView.setDrawingCacheEnabled(false);
                if (!isSign) {
                    MainApplication.getToastUtil().showMiddleToast("请签名");
                } else {
                    up();
                }

            }
        });
        backBtn.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                onBackPressed();
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
        updateBtn = (TextView) findViewById(R.id.updateBtn);
        backBtn = (TextView) findViewById(R.id.backBtn);
        hintSignature = (TextView) findViewById(R.id.hintSignature);
        signatureView = (SignatureView) findViewById(R.id.SignatureView);
        delete = (ImageView) findViewById(R.id.delete);
    }

    private void up() {
        String DH = "";
        String JE = "";
        String LPID = "";
        String SL = "";
        String photo = "";
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
        if (tBitmap != null)
            photo = PhotoUtil.bitmapToString(tBitmap);
        RequestBody requestBody = new FormBody.Builder()
                .add("DH", DH)
                .add("JE", JE)
                .add("SFZH", entryInputBean.getCard())
                .add("LXFS", entryInputBean.getPhone())
                .add("moneyAll", allMoney + "")
                .add("photo", photo)
                .add("LPID", LPID)
                .add("SL", SL)
                .add("rybId", MainApplication.getLoginBean().getDatas().getID() + "")
                .build();
        WDYLog.i("录入数据：", "DH" + DH + "\nJE" + JE + "\nSFZH" + entryInputBean.getCard() + "\nLXFS" + entryInputBean.getPhone() + "\nmoneyAll" + allMoney + "\nLPID" + LPID + "\nSL" + SL + "\nrybId" + MainApplication.getLoginBean().getDatas().getID() + "");
        final Request request = new Request.Builder()
                .post(requestBody)
                .tag(this)
                .url(UrlUtil.upData)
                .build();
        OkHttpUtil.with(this, request, new GenericsCallback<NoDataBack>() {

            @Override
            public void onResponse(NoDataBack response) {
                if (response != null) {
                    BaseApplication.getToastUtil().showMiddleToast(response.getMsg() + "");
                    EventBus.getDefault().post(new MessageEvent("更新"));
                }
            }

            @Override
            public boolean onBefore() {
                DialogUtil.show(SureActivity.this);
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
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (tBitmap != null) {
            tBitmap.recycle();
            tBitmap = null;
            System.gc();
        }

    }
}
