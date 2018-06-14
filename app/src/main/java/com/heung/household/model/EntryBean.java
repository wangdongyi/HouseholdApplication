package com.heung.household.model;

import java.io.Serializable;

/**
 * 作者：王东一
 * 创建时间：2017/8/20.
 */

public class EntryBean implements Serializable {
    private double money;
    private String numFirst;
    private String numSecond;

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getNumFirst() {
        return numFirst;
    }

    public void setNumFirst(String numFirst) {
        this.numFirst = numFirst;
    }

    public String getNumSecond() {
        return numSecond;
    }

    public void setNumSecond(String numSecond) {
        this.numSecond = numSecond;
    }
}
