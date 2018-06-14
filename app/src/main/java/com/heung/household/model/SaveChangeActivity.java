package com.heung.household.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 作者：王东一
 * 创建时间：2017/8/30.
 */

public class SaveChangeActivity implements Serializable {
    private QueryBean queryBean;
    private ArrayList<EntryBean> list;
    private ArrayList<GoodBean> goodList;
    private String MainId;
    private String phone;
    private String card;
    private String allMoney;
    private String url;

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

    public QueryBean getQueryBean() {
        return queryBean;
    }

    public void setQueryBean(QueryBean queryBean) {
        this.queryBean = queryBean;
    }

    public ArrayList<EntryBean> getList() {
        return list;
    }

    public void setList(ArrayList<EntryBean> list) {
        this.list = list;
    }

    public ArrayList<GoodBean> getGoodList() {
        return goodList;
    }

    public void setGoodList(ArrayList<GoodBean> goodList) {
        this.goodList = goodList;
    }

    public String getMainId() {
        return MainId;
    }

    public void setMainId(String mainId) {
        MainId = mainId;
    }

    public String getAllMoney() {
        return allMoney;
    }

    public void setAllMoney(String allMoney) {
        this.allMoney = allMoney;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
