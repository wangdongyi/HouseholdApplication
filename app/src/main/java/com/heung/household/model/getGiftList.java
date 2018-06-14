package com.heung.household.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：王东一
 * 创建时间：2017/8/27.
 */

public class getGiftList implements Serializable {


    /**
     * status : SUCCESS
     * datas : [{"ISVIEW":0,"CSKC":40,"LPMC":"电视机","LPZP":"2017082203361783007.jpg","XHGG":"32寸","ID":5},{"ISVIEW":0,"CSKC":80,"LPMC":"电风扇","LPZP":"2017082203364432556.jpg","XHGG":"落地","ID":6},{"ISVIEW":0,"CSKC":50,"LPMC":"电视机2","LPZP":"2017082801042096743.jpg","XHGG":"32","ID":7}]
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
         * ISVIEW : 0
         * CSKC : 40
         * LPMC : 电视机
         * LPZP : 2017082203361783007.jpg
         * XHGG : 32寸
         * ID : 5
         */

        @SerializedName("ISVIEW")
        private int ISVIEW;
        @SerializedName("CSKC")
        private int CSKC;
        @SerializedName("LPMC")
        private String LPMC;
        @SerializedName("LPZP")
        private String LPZP;
        @SerializedName("XHGG")
        private String XHGG;
        @SerializedName("ID")
        private int ID;

        public int getISVIEW() {
            return ISVIEW;
        }

        public void setISVIEW(int iSVIEW) {
            ISVIEW = iSVIEW;
        }

        public int getCSKC() {
            return CSKC;
        }

        public void setCSKC(int cSKC) {
            CSKC = cSKC;
        }

        public String getLPMC() {
            return LPMC;
        }

        public void setLPMC(String lPMC) {
            LPMC = lPMC;
        }

        public String getLPZP() {
            return LPZP;
        }

        public void setLPZP(String lPZP) {
            LPZP = lPZP;
        }

        public String getXHGG() {
            return XHGG;
        }

        public void setXHGG(String xHGG) {
            XHGG = xHGG;
        }

        public int getID() {
            return ID;
        }

        public void setID(int iD) {
            ID = iD;
        }
    }
}
