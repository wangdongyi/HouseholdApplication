package com.heung.household.main;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.base.library.activity.BaseActivity;
import com.base.library.application.BaseApplication;
import com.base.library.listen.OnPermissionListen;
import com.base.library.permission.PermissionsManager;
import com.base.library.util.ActivityManage;
import com.heung.household.R;
import com.heung.household.base.MainApplication;
import com.heung.household.http.UrlUtil;

/**
 * 作者：王东一
 * 创建时间：2017/8/28.
 */

public class HtttpActivity extends BaseActivity implements View.OnClickListener {
    private EditText http;
    private Button btn;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        initView();
        setTitleName("修改地址");

    }

    private void initView() {
        http = (EditText) findViewById(R.id.http);
        btn = (Button) findViewById(R.id.btn);
        text = (TextView) findViewById(R.id.text);
        text.setText(UrlUtil.getAppUrl());
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                submit();
                break;
        }
    }

    private void submit() {
        // validate
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            PermissionsManager.with(this, new OnPermissionListen() {
                @Override
                public void callBack(boolean isHave) {
                    if (isHave) {
                        String httpString = http.getText().toString().trim();
                        MainApplication.getSharedPreferencesUtil().saveData("url", httpString);
                        MainApplication.getToastUtil().showMiddleToast("修改成功请重新启动");
                        text.setText(UrlUtil.getAppUrl());
                    } else {
                        BaseApplication.getToastUtil().showMiddleToast("您没有开启权限");
                    }
                }
            }, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE);
        }

    }
}
