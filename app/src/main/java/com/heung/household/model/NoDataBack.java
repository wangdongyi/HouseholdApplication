package com.heung.household.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 作者：王东一
 * 创建时间：2017/8/27.
 */

public class NoDataBack implements Serializable {

    /**
     * status : SUCCESS
     * msg : 操作成功
     */

    @SerializedName("status")
    private String status;
    @SerializedName("msg")
    private String msg;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
