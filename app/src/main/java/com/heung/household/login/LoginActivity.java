package com.heung.household.login;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.base.library.activity.BaseActivity;
import com.base.library.application.BaseApplication;
import com.base.library.okHtttpUtil.GenericsCallback;
import com.base.library.okHtttpUtil.OkHttpUtil;
import com.base.library.util.ActivityManage;
import com.base.library.util.CodeUtil;
import com.base.library.util.DialogUtil;
import com.base.library.util.Exit;
import com.base.library.util.MD5Util;
import com.base.library.util.WDYJsonUtil;
import com.base.library.util.WDYLog;
import com.heung.household.R;
import com.heung.household.base.MainApplication;
import com.heung.household.http.UrlUtil;
import com.heung.household.main.HtttpActivity;
import com.heung.household.main.MainActivity;
import com.heung.household.model.LoginBean;

import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * 作者：王东一
 * 创建时间：2017/8/19.
 * 登录
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private EditText inputEdit;
    private EditText passwordEdit;
    private Button loginBtn;
    private RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        hintTitle();
        hintStatus();
        setCanSwipe(false);
        setStatusBar(true);
        initView();
    }

    private void initView() {
        inputEdit = (EditText) findViewById(R.id.inputEdit);
        passwordEdit = (EditText) findViewById(R.id.passwordEdit);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        layout = (RelativeLayout) findViewById(R.id.layout);
        loginBtn.setOnClickListener(this);
        Bitmap bm = BitmapFactory.decodeResource(this.getResources(), R.mipmap.login_background);
        BitmapDrawable bd = new BitmapDrawable(this.getResources(), bm);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            layout.setBackground(bd);
        } else {
            layout.setBackgroundDrawable(bd);
        }
        if (MainApplication.getLoginBean() != null
                && MainApplication.getLoginBean().getDatas() != null
                && CodeUtil.isEmpty(MainApplication.getLoginBean().getDatas().getNAME())) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
        }
    }

    public void HttpChange(View view) {
        Intent intent = new Intent(LoginActivity.this, HtttpActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginBtn:
                submit();
                break;
        }
    }

    private void submit() {
        // validate
        String inputEditString = inputEdit.getText().toString().trim();
        if (TextUtils.isEmpty(inputEditString)) {
            MainApplication.getToastUtil().showMiddleToast("帐号不能为空");
            return;
        }

        String passwordEditString = passwordEdit.getText().toString().trim();
        if (TextUtils.isEmpty(passwordEditString)) {
            MainApplication.getToastUtil().showMiddleToast("密码不能为空");
            return;
        }
        WDYLog.i("加密", MD5Util.string2MD5(passwordEditString));
        getRequest(inputEditString, passwordEditString);
    }

    private void getRequest(String phone, String password) {
        password = MD5Util.string2MD5(password);
        final Request request = new Request.Builder()
                .get()
                .tag(this)
                .url(UrlUtil.Login + "loginName=" + phone + "&password=" + password)
                .build();
        WDYLog.i("UrlUtil.Login", UrlUtil.Login);
        OkHttpUtil.with(this, request, new GenericsCallback<LoginBean>() {

            @Override
            public void onResponse(LoginBean response) {
                String log = WDYJsonUtil.toJson(response);
                if (response != null)
                    switch (response.getStatus()) {
                        case "SUCCESS":
                            MainApplication.setLoginBean(response);
                            BaseApplication.getSharedPreferencesUtil().saveBean("LoginBean", response);
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                            overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
                            break;
                        default:
                            BaseApplication.getToastUtil().showMiddleToast(response.getMsg());
                            break;
                    }
            }

            @Override
            public boolean onBefore() {
                DialogUtil.show(LoginActivity.this);
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

    /**
     * 监听键盘按键
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //  监听键盘按键
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            pressAgainExit();
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }

    /**
     *
     */
    private Exit exit = new Exit();

    /**
     * 再按一次退出程序。
     */
    private void pressAgainExit() {
        if (exit.isExit()) {
            ActivityManage.getInstance().exit();
        } else {
            CodeUtil.showToastShort(this, "连按两次退出程序");
            exit.doExitInOneSecond();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BitmapDrawable bd = (BitmapDrawable) layout.getBackground();
        layout.setBackgroundResource(0);//别忘了把背景设为null，避免onDraw刷新背景时候出现used a recycled bitmap错误
        bd.setCallback(null);
        bd.getBitmap().recycle();
    }
}
