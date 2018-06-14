package com.heung.household.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 作者：王东一
 * 创建时间：2017/8/27.
 */

public class EntryInputBean implements Serializable {
    private ArrayList<EntryBean> entryBeanArrayList;
    private String phone;
    private String card;
    private ArrayList<GoodBean> goodBeanArrayList;

    public ArrayList<EntryBean> getEntryBeanArrayList() {
        return entryBeanArrayList;
    }

    public void setEntryBeanArrayList(ArrayList<EntryBean> entryBeanArrayList) {
        this.entryBeanArrayList = entryBeanArrayList;
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

    public ArrayList<GoodBean> getGoodBeanArrayList() {
        return goodBeanArrayList;
    }

    public void setGoodBeanArrayList(ArrayList<GoodBean> goodBeanArrayList) {
        this.goodBeanArrayList = goodBeanArrayList;
    }
}
