package com.heung.household.model;

import com.base.library.view.papyrus.PathPointBean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 作者：王东一
 * 创建时间：2017/8/30.
 */

public class SaveSure implements Serializable {
    private ArrayList<PathPointBean> pathList = new ArrayList<>();
    private ArrayList<PathPointBean> pathNextList = new ArrayList<>();
    private int onDownPosition;

    public int getOnDownPosition() {
        return onDownPosition;
    }

    public void setOnDownPosition(int onDownPosition) {
        this.onDownPosition = onDownPosition;
    }

    public ArrayList<PathPointBean> getPathList() {
        return pathList;
    }

    public void setPathList(ArrayList<PathPointBean> pathList) {
        this.pathList = pathList;
    }

    public ArrayList<PathPointBean> getPathNextList() {
        return pathNextList;
    }

    public void setPathNextList(ArrayList<PathPointBean> pathNextList) {
        this.pathNextList = pathNextList;
    }
}
