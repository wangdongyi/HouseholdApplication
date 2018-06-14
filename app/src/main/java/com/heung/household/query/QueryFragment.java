package com.heung.household.query;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.base.library.application.BaseApplication;
import com.base.library.eventBus.MessageEvent;
import com.base.library.fragment.BaseFragment;
import com.base.library.listen.NoDoubleClickListener;
import com.base.library.listen.OnRecyclerClickListen;
import com.base.library.okHtttpUtil.GenericsCallback;
import com.base.library.okHtttpUtil.OkHttpUtil;
import com.base.library.util.CodeUtil;
import com.base.library.util.DialogUtil;
import com.base.library.util.WDYJsonUtil;
import com.base.library.util.WDYLog;
import com.heung.household.R;
import com.heung.household.base.MainApplication;
import com.heung.household.http.UrlUtil;
import com.heung.household.main.MainActivity;
import com.heung.household.model.EntryBean;
import com.heung.household.model.LoginBean;
import com.heung.household.model.NoDataBack;
import com.heung.household.model.QueryBean;
import com.heung.household.model.SaveQuery;
import com.heung.household.model.SearchBack;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import okhttp3.Request;

/**
 * 作者：王东一
 * 创建时间：2017/8/20.
 */

public class QueryFragment extends BaseFragment {
    Bundle savedState;
    private boolean isBuild = false;
    private TextView searchBtn;
    private RecyclerView searchRecycle;
    private QueryAdapter queryAdapter;
    private ArrayList<QueryBean> list = new ArrayList<>();
    private EditText editQuery;
    private String input;
    private SaveQuery saveQuery = new SaveQuery();

    public void onDateBack(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            saveQuery = (SaveQuery) savedInstanceState.getSerializable("saveQuery");
        }
    }

    public void onDateSave(Bundle outState) {
        saveQuery.setList(list);
        saveQuery.setInput(editQuery.getText().toString());
        outState.putSerializable("saveQuery", saveQuery);
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setContentView(inflater, R.layout.fragment_query);
        if (!isViewShown && !isBuild) {
            lazyLoad();
        }
        initView(main);
        return main;
    }


    @Override
    protected void init() {
        EventBus.getDefault().register(this);
        initView(main);
        list = saveQuery.getList();
        input = saveQuery.getInput();
        queryAdapter = new QueryAdapter(getActivity(), list);
        queryAdapter.setOnRecyclerClickListen(new OnRecyclerClickListen() {
            @Override
            public void onClick(final int i) {
                DialogUtil.showNoTitleSave(getActivity(), "是否删除该订单", "删除", "取消", new NoDoubleClickListener() {
                    @Override
                    protected void onNoDoubleClick(View view) {
                        DialogUtil.dismiss();
                        DialogUtil.showNoTitleSave(getActivity(), "是否删除该订单", "删除", "取消", new NoDoubleClickListener() {
                            @Override
                            protected void onNoDoubleClick(View view) {
                                DialogUtil.dismiss();
                                deleted(list.get(i).getId(), i);
                            }
                        }, new NoDoubleClickListener() {
                            @Override
                            protected void onNoDoubleClick(View view) {
                                DialogUtil.dismiss();
                            }
                        });
                    }
                }, new NoDoubleClickListener() {
                    @Override
                    protected void onNoDoubleClick(View view) {
                        DialogUtil.dismiss();
                    }
                });
            }
        });
        searchRecycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        searchRecycle.setAdapter(queryAdapter);
        searchBtn.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                if (CodeUtil.isEmpty(editQuery.getText().toString())) {
                    MainApplication.getToastUtil().showMiddleToast("搜索内容不能为空");
                } else {
                    search(editQuery.getText().toString());
                }
            }
        });
        editQuery.setText(input);
        editQuery.setSelection(editQuery.getText().toString().length());
    }

    @Override
    protected void lazyLoad() {
        isBuild = true;
        init();
    }

    private void initView(View main) {
        searchBtn = (TextView) main.findViewById(R.id.searchBtn);
        searchRecycle = (RecyclerView) main.findViewById(R.id.searchRecycle);
        editQuery = (EditText) main.findViewById(R.id.edit_query);
    }

    private void search(String input) {
        final Request request = new Request.Builder()
                .get()
                .tag(this)
                .url(UrlUtil.queryOrderList + "input=" + input + MainActivity.getRybld())
                .build();
        WDYLog.i("输出日志：",UrlUtil.queryOrderList + "input=" + input + MainActivity.getRybld());
        OkHttpUtil.with(this, request, new GenericsCallback<SearchBack>() {

            @Override
            public void onResponse(SearchBack response) {
                BaseApplication.getToastUtil().showMiddleToast(response.getMsg());
                switch (response.getStatus()) {
                    case "SUCCESS":
                        list.clear();
                        for (int i = 0; i < response.getDatas().size(); i++) {
                            QueryBean q = new QueryBean();
                            q.setId(response.getDatas().get(i).getID() + "");
                            q.setNumOne(response.getDatas().get(i).getNUMBER() + "");
                            q.setMoney(response.getDatas().get(i).getMONEY() + "");
                            q.setPhone(response.getDatas().get(i).getLXFS());
                            q.setTime(response.getDatas().get(i).getINDATE());
                            q.setCard(response.getDatas().get(i).getSFZH());
                            list.add(q);
                        }
                        queryAdapter.notifyDataSetChanged();
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

    private void deleted(String id, final int position) {
        final Request request = new Request.Builder()
                .get()
                .tag(this)
                .url(UrlUtil.deleteTheOrder + "id=" + id)
                .build();
        WDYLog.i("删除",UrlUtil.deleteTheOrder + "id=" + id);
        OkHttpUtil.with(this, request, new GenericsCallback<NoDataBack>() {

            @Override
            public void onResponse(NoDataBack response) {
                if (response != null)
                    switch (response.getStatus()) {
                        case "SUCCESS":
                            list.remove(position);
                            queryAdapter.notifyDataSetChanged();
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
            case "刷新":
                if (CodeUtil.isEmpty(editQuery.getText().toString())) {
                    MainApplication.getToastUtil().showMiddleToast("搜索内容不能为空");
                } else {
                    search(editQuery.getText().toString());
                }
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
