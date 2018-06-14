package com.heung.household.http;

import com.heung.household.base.MainApplication;

import static com.heung.household.base.MainApplication.getUrl;

/**
 * 作者：王东一
 * 创建时间：2017/8/26.
 */

public class UrlUtil {
    public static String PhotoUrl = "http://" + getUrl() + ":8080/XYADMINM/uploadFiles/uploadFile/";

    public static String getAppUrl() {
        String AppUrl = "http://" + getUrl() + ":8080/XYADMINM/app/";
        return AppUrl;
    }

    public static String Login = getAppUrl() + "Login.do?";
    public static String toMainView = getAppUrl() + "toMainView.do?";
    public static String getGiftList = getAppUrl() + "getGiftList.do";
    public static String upData = getAppUrl() + "upData.do";
    public static String queryOrderList = getAppUrl() + "queryOrderList.do?";
    public static String deleteTheOrder = getAppUrl() + "deleteTheOrder.do?";
    public static String changeOrders = getAppUrl() + "changeOrders.do";
    public static String toChildView = getAppUrl() + "toChildView.do?";
    public static String getGift = getAppUrl() + "getGift.do?";
}
