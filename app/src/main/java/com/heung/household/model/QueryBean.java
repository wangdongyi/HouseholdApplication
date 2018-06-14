package com.heung.household.model;

import java.io.Serializable;

/**
 * 作者：王东一
 * 创建时间：2017/8/20.
 */

public class QueryBean implements Serializable {
    private String id;
    private String numOne;
    private String numTwo;
    private String time;
    private String money;
    private String phone;
    private String card;

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumOne() {
        return numOne;
    }

    public void setNumOne(String numOne) {
        this.numOne = numOne;
    }

    public String getNumTwo() {
        return numTwo;
    }

    public void setNumTwo(String numTwo) {
        this.numTwo = numTwo;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
}
