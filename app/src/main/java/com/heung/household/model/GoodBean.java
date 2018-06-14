package com.heung.household.model;

import java.io.Serializable;

/**
 * 作者：王东一
 * 创建时间：2017/8/20.
 */

public class GoodBean implements Serializable {
    private String name;
    private String url;
    private int AllNum;
    private int SelectedNum;
    private String id;
    private boolean canAdd;
    private boolean canSubtraction;
    private int Max;

    public int getMax() {
        return Max;
    }

    public void setMax(int max) {
        Max = max;
    }

    public boolean isCanAdd() {
        return canAdd;
    }

    public void setCanAdd(boolean canAdd) {
        this.canAdd = canAdd;
    }

    public boolean isCanSubtraction() {
        return canSubtraction;
    }

    public void setCanSubtraction(boolean canSubtraction) {
        this.canSubtraction = canSubtraction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getAllNum() {
        return AllNum;
    }

    public void setAllNum(int allNum) {
        AllNum = allNum;
    }

    public int getSelectedNum() {
        return SelectedNum;
    }

    public void setSelectedNum(int selectedNum) {
        SelectedNum = selectedNum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
