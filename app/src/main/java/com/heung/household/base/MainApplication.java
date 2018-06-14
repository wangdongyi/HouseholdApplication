package com.heung.household.base;

import com.base.library.application.BaseApplication;
import com.base.library.bean.ThemBean;
import com.base.library.util.SharedPreferencesUtil;
import com.heung.household.R;
import com.heung.household.model.LoginBean;


/**
 * 作者：王东一
 * 创建时间：2017/8/1.
 */

public class MainApplication extends BaseApplication {
    private static LoginBean loginBean;

    public static LoginBean getLoginBean() {
        return loginBean;
    }

    public static void setLoginBean(LoginBean loginBean) {
        MainApplication.loginBean = loginBean;
    }

    public static String getUrl() {
//        return (String) getSharedPreferencesUtil().getData("url","192.168.1.6");//47.92.101.197
        return (String) getSharedPreferencesUtil().getData("url","47.92.101.197");//47.92.101.197
    }


    @Override
    public void onCreate() {
        super.onCreate();
        setLoginBean(getSharedPreferencesUtil().getBean("LoginBean", LoginBean.class));
        ThemBean themBean = new ThemBean();
        themBean.setStatusBarDark(true);
        themBean.setBackMipmap(R.drawable.ic_back);
        setThemBean(themBean);

    }
}
