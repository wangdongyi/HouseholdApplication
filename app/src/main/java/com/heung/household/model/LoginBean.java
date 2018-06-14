package com.heung.household.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 作者：王东一
 * 创建时间：2017/8/26.
 */

public class LoginBean implements Serializable {


    /**
     * status : SUCCESS
     * datas : {"USERNAME":"b","ID":3,"USERPASSWORD":"b","NAME":"b"}
     * msg : 登录成功
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
         * USERNAME : b
         * ID : 3
         * USERPASSWORD : b
         * NAME : b
         */

        @SerializedName("USERNAME")
        private String USERNAME;
        @SerializedName("ID")
        private int ID;
        @SerializedName("USERPASSWORD")
        private String USERPASSWORD;
        @SerializedName("NAME")
        private String NAME;

        public String getUSERNAME() {
            return USERNAME;
        }

        public void setUSERNAME(String uSERNAME) {
            USERNAME = uSERNAME;
        }

        public int getID() {
            return ID;
        }

        public void setID(int iD) {
            ID = iD;
        }

        public String getUSERPASSWORD() {
            return USERPASSWORD;
        }

        public void setUSERPASSWORD(String uSERPASSWORD) {
            USERPASSWORD = uSERPASSWORD;
        }

        public String getNAME() {
            return NAME;
        }

        public void setNAME(String nAME) {
            NAME = nAME;
        }
    }
}
