package com.heung.household.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 作者：王东一
 * 创建时间：2017/8/30.
 */

public class SaveQuery implements Serializable{
    private ArrayList<QueryBean> list = new ArrayList<>();
    private String input;

    public ArrayList<QueryBean> getList() {
        return list;
    }

    public void setList(ArrayList<QueryBean> list) {
        this.list = list;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
}
