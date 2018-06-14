package com.heung.household.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：王东一
 * 创建时间：2017/8/27.
 */

public class SearchInfoBean implements Serializable {


    /**
     * status : SUCCESS
     * datas : [{"DH":"1-1","ID":34,"JE":10,"DHID":13},{"DH":"1-2","ID":33,"JE":20,"DHID":13},{"DH":"1-3","ID":32,"JE":30,"DHID":13},{"DH":"1-3","ID":25,"JE":30,"DHID":13},{"DH":"1-2","ID":24,"JE":20,"DHID":13},{"DH":"1-1","ID":23,"JE":10,"DHID":13}]
     * msg : 查询成功
     */

    @SerializedName("status")
    private String status;
    @SerializedName("msg")
    private String msg;
    @SerializedName("datas")
    private List<DatasBean> datas;

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

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * DH : 1-1
         * ID : 34
         * JE : 10
         * DHID : 13
         */

        @SerializedName("DH")
        private String DH;
        @SerializedName("ID")
        private int ID;
        @SerializedName("JE")
        private int JE;
        @SerializedName("DHID")
        private int DHID;

        public String getDH() {
            return DH;
        }

        public void setDH(String dH) {
            DH = dH;
        }

        public int getID() {
            return ID;
        }

        public void setID(int iD) {
            ID = iD;
        }

        public int getJE() {
            return JE;
        }

        public void setJE(int jE) {
            JE = jE;
        }

        public int getDHID() {
            return DHID;
        }

        public void setDHID(int dHID) {
            DHID = dHID;
        }
    }
}
