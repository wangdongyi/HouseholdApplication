package com.heung.household.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 作者：王东一
 * 创建时间：2017/8/30.
 */

public class SaveEntry implements Serializable {
    private ArrayList<EntryBean> list = new ArrayList<>();
    private ArrayList<GoodBean> goodList = new ArrayList<>();
    private String money;
    private String phone;
    private String card;

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

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

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
}
