package com.heung.household.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：王东一
 * 创建时间：2017/8/27.
 */

public class SearchBack implements Serializable {


    /**
     * status : SUCCESS
     * datas : [{"MONEY":60,"SFZH":"6666","LXFS":"666","ID":13},{"MONEY":60,"SFZH":"6666","LXFS":"666","ID":13},{"MONEY":60,"SFZH":"6666","LXFS":"666","ID":13},{"MONEY":60,"SFZH":"6666","LXFS":"666","ID":13},{"MONEY":60,"SFZH":"6666","LXFS":"666","ID":13},{"MONEY":60,"SFZH":"6666","LXFS":"666","ID":13},{"MONEY":213123,"SFZH":"123213","LXFS":"123123123","ID":14,"INDATE":"2017-08-27"},{"MONEY":213123,"SFZH":"123213","LXFS":"123123123","ID":14,"INDATE":"2017-08-27"},{"MONEY":213123,"SFZH":"123213","LXFS":"123123123","ID":15,"INDATE":"2017-08-27"},{"MONEY":213123,"SFZH":"123213","LXFS":"123123123","ID":15,"INDATE":"2017-08-27"},{"MONEY":123,"SFZH":"1111111","LXFS":"12345678900","ID":16,"INDATE":"2017-08-27"}]
     * msg : 查询完成
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
         * MONEY : 60
         * SFZH : 6666
         * LXFS : 666
         * ID : 13
         * INDATE : 2017-08-27
         */

        @SerializedName("MONEY")
        private int MONEY;
        @SerializedName("SFZH")
        private String SFZH;
        @SerializedName("LXFS")
        private String LXFS;
        @SerializedName("ID")
        private int ID;
        @SerializedName("NUMBER")
        private String NUMBER;
        @SerializedName("INDATE")
        private String INDATE;

        public String getNUMBER() {
            return NUMBER;
        }

        public void setNUMBER(String NUMBER) {
            this.NUMBER = NUMBER;
        }

        public int getMONEY() {
            return MONEY;
        }

        public void setMONEY(int mONEY) {
            MONEY = mONEY;
        }

        public String getSFZH() {
            return SFZH;
        }

        public void setSFZH(String sFZH) {
            SFZH = sFZH;
        }

        public String getLXFS() {
            return LXFS;
        }

        public void setLXFS(String lXFS) {
            LXFS = lXFS;
        }

        public int getID() {
            return ID;
        }

        public void setID(int iD) {
            ID = iD;
        }

        public String getINDATE() {
            return INDATE;
        }

        public void setINDATE(String iNDATE) {
            INDATE = iNDATE;
        }
    }
}
