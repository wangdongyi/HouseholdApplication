package com.heung.household.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 作者：王东一
 * 创建时间：2017/8/30.
 */

public class SaveSeeActivity implements Serializable {
    private ArrayList<SureOrderBean> list = new ArrayList<>();
    private ArrayList<GoodBean> giftList = new ArrayList<>();
    private String MainId;
    private String url;
    private QueryBean queryBean;
    private String phone;
    private String card;
    private String allMoney;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getAllMoney() {
        return allMoney;
    }

    public void setAllMoney(String allMoney) {
        this.allMoney = allMoney;
    }

    public QueryBean getQueryBean() {
        return queryBean;
    }

    public void setQueryBean(QueryBean queryBean) {
        this.queryBean = queryBean;
    }

    public ArrayList<SureOrderBean> getList() {
        return list;
    }

    public void setList(ArrayList<SureOrderBean> list) {
        this.list = list;
    }

    public ArrayList<GoodBean> getGiftList() {
        return giftList;
    }

    public void setGiftList(ArrayList<GoodBean> giftList) {
        this.giftList = giftList;
    }

    public String getMainId() {
        return MainId;
    }

    public void setMainId(String mainId) {
        MainId = mainId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
