package com.heung.household.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 作者：王东一
 * 创建时间：2017/8/28.
 */

public class ToMainBackBean implements Serializable {


    /**
     * status : SUCCESS
     * datas : {"MONEY":2323,"SFZH":"1222222222","LXFS":"18600000000","ID":17,"INDATE":"2017-08-27","QM":"ssss"}
     * msg : 查询成功
     */

    @SerializedName("status")
    private String status;
    @SerializedName("datas")
    private DatasBean datas;
    @SerializedName("msg")
    private String msg;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DatasBean getDatas() {
        return datas;
    }

    public void setDatas(DatasBean datas) {
        this.datas = datas;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DatasBean {
        /**
         * MONEY : 2323
         * SFZH : 1222222222
         * LXFS : 18600000000
         * ID : 17
         * INDATE : 2017-08-27
         * QM : ssss
         */

        @SerializedName("MONEY")
        private int MONEY;
        @SerializedName("SFZH")
        private String SFZH;
        @SerializedName("LXFS")
        private String LXFS;
        @SerializedName("ID")
        private int ID;
        @SerializedName("INDATE")
        private String INDATE;
        @SerializedName("QM")
        private String QM;

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

        public String getQM() {
            return QM;
        }

        public void setQM(String qM) {
            QM = qM;
        }
    }
}
