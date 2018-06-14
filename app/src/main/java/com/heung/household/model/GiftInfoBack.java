package com.heung.household.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：王东一
 * 创建时间：2017/8/27.
 */

public class GiftInfoBack implements Serializable {


    /**
     * status : SUCCESS
     * datas : [{"SOURCE_ID":12,"ISVIEW":0,"NUM":0,"OPERATOR":"","CSKC":146,"SOURCE":2,"LPMC":"6万-微波炉","LPZP":"2017082901404951058.jpg","XHGG":"美的","ID":27,"LPID":6,"CNUM":100,"OTIME":"2017-08-22 15:40:26","TYPE":2,"MEMO":"兑换"},{"SOURCE_ID":13,"ISVIEW":0,"NUM":30,"OPERATOR":"","CSKC":146,"SOURCE":2,"LPMC":"6万-微波炉","LPZP":"2017082901404951058.jpg","XHGG":"美的","ID":29,"LPID":6,"CNUM":100,"OTIME":"2017-08-22 15:51:19","TYPE":2,"MEMO":"兑换"},{"SOURCE_ID":21,"ISVIEW":0,"NUM":2,"OPERATOR":"","CSKC":146,"SOURCE":2,"LPMC":"6万-微波炉","LPZP":"2017082901404951058.jpg","XHGG":"美的","ID":36,"LPID":6,"CNUM":80,"OTIME":"2017-08-29 11:08:42","TYPE":2,"MEMO":"兑换"},{"SOURCE_ID":21,"ISVIEW":0,"NUM":2,"OPERATOR":"","CSKC":5,"SOURCE":2,"LPMC":"3万-智能电饭煲","LPZP":"2017082901410844136.jpg","XHGG":"美的","ID":37,"LPID":7,"CNUM":51,"OTIME":"2017-08-29 11:08:42","TYPE":2,"MEMO":"兑换"},{"SOURCE_ID":22,"ISVIEW":0,"NUM":2,"OPERATOR":"","CSKC":146,"SOURCE":2,"LPMC":"6万-微波炉","LPZP":"2017082901404951058.jpg","XHGG":"美的","ID":39,"LPID":6,"CNUM":80,"OTIME":"2017-08-29 11:12:56","TYPE":2,"MEMO":"兑换"},{"SOURCE_ID":23,"ISVIEW":0,"NUM":1,"OPERATOR":"","CSKC":64,"SOURCE":2,"LPMC":"10万-净水器","LPZP":"2017082901402298275.jpg","XHGG":"YAMAHA","ID":42,"LPID":8,"CNUM":10,"OTIME":"2017-08-29 14:12:12","TYPE":2,"MEMO":"兑换"},{"SOURCE_ID":23,"ISVIEW":0,"NUM":10,"OPERATOR":"","CSKC":146,"SOURCE":2,"LPMC":"6万-微波炉","LPZP":"2017082901404951058.jpg","XHGG":"美的","ID":43,"LPID":6,"CNUM":10,"OTIME":"2017-08-29 14:12:12","TYPE":2,"MEMO":"兑换"},{"SOURCE_ID":23,"ISVIEW":0,"NUM":10,"OPERATOR":"","CSKC":5,"SOURCE":2,"LPMC":"3万-智能电饭煲","LPZP":"2017082901410844136.jpg","XHGG":"美的","ID":44,"LPID":7,"CNUM":10,"OTIME":"2017-08-29 14:12:12","TYPE":2,"MEMO":"兑换"},{"SOURCE_ID":23,"ISVIEW":0,"NUM":46,"OPERATOR":"","CSKC":32,"SOURCE":2,"LPMC":"2万-电磁炉","LPZP":"2017082901453318685.jpg","XHGG":"美的","ID":45,"LPID":9,"CNUM":100,"OTIME":"2017-08-29 14:12:12","TYPE":2,"MEMO":"兑换"},{"SOURCE_ID":24,"ISVIEW":0,"NUM":2,"OPERATOR":"","CSKC":64,"SOURCE":2,"LPMC":"10万-净水器","LPZP":"2017082901402298275.jpg","XHGG":"YAMAHA","ID":47,"LPID":8,"CNUM":10,"OTIME":"2017-08-29 14:47:18","TYPE":2,"MEMO":"兑换"},{"SOURCE_ID":24,"ISVIEW":0,"NUM":2,"OPERATOR":"","CSKC":146,"SOURCE":2,"LPMC":"6万-微波炉","LPZP":"2017082901404951058.jpg","XHGG":"美的","ID":48,"LPID":6,"CNUM":66,"OTIME":"2017-08-29 14:47:18","TYPE":2,"MEMO":"兑换"},{"SOURCE_ID":24,"ISVIEW":0,"NUM":4,"OPERATOR":"","CSKC":5,"SOURCE":2,"LPMC":"3万-智能电饭煲","LPZP":"2017082901410844136.jpg","XHGG":"美的","ID":49,"LPID":7,"CNUM":10,"OTIME":"2017-08-29 14:47:18","TYPE":2,"MEMO":"兑换"},{"SOURCE_ID":24,"ISVIEW":0,"NUM":2,"OPERATOR":"","CSKC":32,"SOURCE":2,"LPMC":"2万-电磁炉","LPZP":"2017082901453318685.jpg","XHGG":"美的","ID":50,"LPID":9,"CNUM":100,"OTIME":"2017-08-29 14:47:18","TYPE":2,"MEMO":"兑换"},{"SOURCE_ID":25,"ISVIEW":0,"NUM":2,"OPERATOR":"","CSKC":64,"SOURCE":2,"LPMC":"10万-净水器","LPZP":"2017082901402298275.jpg","XHGG":"YAMAHA","ID":51,"LPID":8,"CNUM":10,"OTIME":"2017-08-29 14:51:28","TYPE":2,"MEMO":"兑换"},{"SOURCE_ID":25,"ISVIEW":0,"NUM":2,"OPERATOR":"","CSKC":146,"SOURCE":2,"LPMC":"6万-微波炉","LPZP":"2017082901404951058.jpg","XHGG":"美的","ID":52,"LPID":6,"CNUM":66,"OTIME":"2017-08-29 14:51:28","TYPE":2,"MEMO":"兑换"},{"SOURCE_ID":25,"ISVIEW":0,"NUM":2,"OPERATOR":"","CSKC":5,"SOURCE":2,"LPMC":"3万-智能电饭煲","LPZP":"2017082901410844136.jpg","XHGG":"美的","ID":53,"LPID":7,"CNUM":6,"OTIME":"2017-08-29 14:51:28","TYPE":2,"MEMO":"兑换"},{"SOURCE_ID":25,"ISVIEW":0,"NUM":2,"OPERATOR":"","CSKC":32,"SOURCE":2,"LPMC":"2万-电磁炉","LPZP":"2017082901453318685.jpg","XHGG":"美的","ID":54,"LPID":9,"CNUM":100,"OTIME":"2017-08-29 14:51:28","TYPE":2,"MEMO":"兑换"},{"SOURCE_ID":26,"ISVIEW":0,"NUM":2,"OPERATOR":"","CSKC":64,"SOURCE":2,"LPMC":"10万-净水器","LPZP":"2017082901402298275.jpg","XHGG":"YAMAHA","ID":55,"LPID":8,"CNUM":10,"OTIME":"2017-08-29 14:53:54","TYPE":2,"MEMO":"兑换"},{"SOURCE_ID":26,"ISVIEW":0,"NUM":2,"OPERATOR":"","CSKC":146,"SOURCE":2,"LPMC":"6万-微波炉","LPZP":"2017082901404951058.jpg","XHGG":"美的","ID":56,"LPID":6,"CNUM":64,"OTIME":"2017-08-29 14:53:54","TYPE":2,"MEMO":"兑换"},{"SOURCE_ID":26,"ISVIEW":0,"NUM":2,"OPERATOR":"","CSKC":5,"SOURCE":2,"LPMC":"3万-智能电饭煲","LPZP":"2017082901410844136.jpg","XHGG":"美的","ID":57,"LPID":7,"CNUM":4,"OTIME":"2017-08-29 14:53:54","TYPE":2,"MEMO":"兑换"},{"SOURCE_ID":26,"ISVIEW":0,"NUM":2,"OPERATOR":"","CSKC":32,"SOURCE":2,"LPMC":"2万-电磁炉","LPZP":"2017082901453318685.jpg","XHGG":"美的","ID":58,"LPID":9,"CNUM":100,"OTIME":"2017-08-29 14:53:54","TYPE":2,"MEMO":"兑换"},{"SOURCE_ID":27,"ISVIEW":0,"NUM":2,"OPERATOR":"","CSKC":64,"SOURCE":2,"LPMC":"10万-净水器","LPZP":"2017082901402298275.jpg","XHGG":"YAMAHA","ID":59,"LPID":8,"CNUM":10,"OTIME":"2017-08-29 14:59:25","TYPE":2,"MEMO":"兑换"},{"SOURCE_ID":27,"ISVIEW":0,"NUM":2,"OPERATOR":"","CSKC":146,"SOURCE":2,"LPMC":"6万-微波炉","LPZP":"2017082901404951058.jpg","XHGG":"美的","ID":60,"LPID":6,"CNUM":92,"OTIME":"2017-08-29 14:59:25","TYPE":2,"MEMO":"兑换"},{"SOURCE_ID":27,"ISVIEW":0,"NUM":2,"OPERATOR":"","CSKC":5,"SOURCE":2,"LPMC":"3万-智能电饭煲","LPZP":"2017082901410844136.jpg","XHGG":"美的","ID":61,"LPID":7,"CNUM":2,"OTIME":"2017-08-29 14:59:25","TYPE":2,"MEMO":"兑换"},{"SOURCE_ID":27,"ISVIEW":0,"NUM":2,"OPERATOR":"","CSKC":32,"SOURCE":2,"LPMC":"2万-电磁炉","LPZP":"2017082901453318685.jpg","XHGG":"美的","ID":62,"LPID":9,"CNUM":100,"OTIME":"2017-08-29 14:59:25","TYPE":2,"MEMO":"兑换"},{"SOURCE_ID":28,"ISVIEW":0,"NUM":4,"OPERATOR":"","CSKC":64,"SOURCE":2,"LPMC":"10万-净水器","LPZP":"2017082901402298275.jpg","XHGG":"YAMAHA","ID":64,"LPID":8,"CNUM":10,"OTIME":"2017-08-29 15:05:30","TYPE":2,"MEMO":"兑换"},{"SOURCE_ID":28,"ISVIEW":0,"NUM":4,"OPERATOR":"","CSKC":146,"SOURCE":2,"LPMC":"6万-微波炉","LPZP":"2017082901404951058.jpg","XHGG":"美的","ID":65,"LPID":6,"CNUM":90,"OTIME":"2017-08-29 15:05:30","TYPE":2,"MEMO":"兑换"},{"SOURCE_ID":28,"ISVIEW":0,"NUM":2,"OPERATOR":"","CSKC":5,"SOURCE":2,"LPMC":"3万-智能电饭煲","LPZP":"2017082901410844136.jpg","XHGG":"美的","ID":66,"LPID":7,"CNUM":0,"OTIME":"2017-08-29 15:05:30","TYPE":2,"MEMO":"兑换"},{"SOURCE_ID":28,"ISVIEW":0,"NUM":10,"OPERATOR":"","CSKC":32,"SOURCE":2,"LPMC":"2万-电磁炉","LPZP":"2017082901453318685.jpg","XHGG":"美的","ID":67,"LPID":9,"CNUM":100,"OTIME":"2017-08-29 15:05:30","TYPE":2,"MEMO":"兑换"},{"SOURCE_ID":29,"ISVIEW":0,"NUM":2,"OPERATOR":"","CSKC":5,"SOURCE":2,"LPMC":"3万-智能电饭煲","LPZP":"2017082901410844136.jpg","XHGG":"美的","ID":78,"LPID":7,"CNUM":11,"OTIME":"2017-08-29 15:12:27","TYPE":2,"MEMO":"兑换"},{"SOURCE_ID":30,"ISVIEW":0,"NUM":3,"OPERATOR":"","CSKC":146,"SOURCE":2,"LPMC":"6万-微波炉","LPZP":"2017082901404951058.jpg","XHGG":"美的","ID":79,"LPID":6,"CNUM":86,"OTIME":"2017-08-29 17:04:22","TYPE":2,"MEMO":"兑换"},{"SOURCE_ID":30,"ISVIEW":0,"NUM":3,"OPERATOR":"","CSKC":5,"SOURCE":2,"LPMC":"3万-智能电饭煲","LPZP":"2017082901410844136.jpg","XHGG":"美的","ID":80,"LPID":7,"CNUM":9,"OTIME":"2017-08-29 17:04:22","TYPE":2,"MEMO":"兑换"},{"SOURCE_ID":31,"ISVIEW":0,"NUM":2,"OPERATOR":"","CSKC":64,"SOURCE":2,"LPMC":"10万-净水器","LPZP":"2017082901402298275.jpg","XHGG":"YAMAHA","ID":81,"LPID":8,"CNUM":10,"OTIME":"2017-08-29 17:25:12","TYPE":2,"MEMO":"兑换"},{"SOURCE_ID":31,"ISVIEW":0,"NUM":2,"OPERATOR":"","CSKC":146,"SOURCE":2,"LPMC":"6万-微波炉","LPZP":"2017082901404951058.jpg","XHGG":"美的","ID":82,"LPID":6,"CNUM":83,"OTIME":"2017-08-29 17:25:12","TYPE":2,"MEMO":"兑换"},{"SOURCE_ID":32,"ISVIEW":0,"NUM":2,"OPERATOR":"","CSKC":64,"SOURCE":2,"LPMC":"10万-净水器","LPZP":"2017082901402298275.jpg","XHGG":"YAMAHA","ID":83,"LPID":8,"CNUM":8,"OTIME":"2017-08-29 17:56:42","TYPE":2,"MEMO":"兑换"},{"SOURCE_ID":32,"ISVIEW":0,"NUM":10,"OPERATOR":"","CSKC":32,"SOURCE":2,"LPMC":"2万-电磁炉","LPZP":"2017082901453318685.jpg","XHGG":"美的","ID":84,"LPID":9,"CNUM":100,"OTIME":"2017-08-29 17:56:42","TYPE":2,"MEMO":"兑换"},{"SOURCE_ID":33,"ISVIEW":0,"NUM":2,"OPERATOR":"","CSKC":64,"SOURCE":2,"LPMC":"10万-净水器","LPZP":"2017082901402298275.jpg","XHGG":"YAMAHA","ID":86,"LPID":8,"CNUM":6,"OTIME":"2017-08-29 17:57:07","TYPE":2,"MEMO":"兑换"},{"SOURCE_ID":33,"ISVIEW":0,"NUM":6,"OPERATOR":"","CSKC":32,"SOURCE":2,"LPMC":"2万-电磁炉","LPZP":"2017082901453318685.jpg","XHGG":"美的","ID":87,"LPID":9,"CNUM":90,"OTIME":"2017-08-29 17:57:07","TYPE":2,"MEMO":"兑换"},{"SOURCE_ID":34,"ISVIEW":0,"NUM":12,"OPERATOR":"","CSKC":32,"SOURCE":2,"LPMC":"2万-电磁炉","LPZP":"2017082901453318685.jpg","XHGG":"美的","ID":89,"LPID":9,"CNUM":85,"OTIME":"2017-08-29 18:01:21","TYPE":2,"MEMO":"兑换"},{"SOURCE_ID":35,"ISVIEW":0,"NUM":10,"OPERATOR":"","CSKC":32,"SOURCE":2,"LPMC":"2万-电磁炉","LPZP":"2017082901453318685.jpg","XHGG":"美的","ID":96,"LPID":9,"CNUM":20,"OTIME":"2017-08-29 18:13:34","TYPE":2,"MEMO":"兑换"},{"SOURCE_ID":36,"ISVIEW":0,"NUM":3,"OPERATOR":"","CSKC":64,"SOURCE":2,"LPMC":"10万-净水器","LPZP":"2017082901402298275.jpg","XHGG":"YAMAHA","ID":97,"LPID":8,"CNUM":42,"OTIME":"2017-08-29 18:19:36","TYPE":2,"MEMO":"兑换"},{"SOURCE_ID":36,"ISVIEW":0,"NUM":3,"OPERATOR":"","CSKC":32,"SOURCE":2,"LPMC":"2万-电磁炉","LPZP":"2017082901453318685.jpg","XHGG":"美的","ID":98,"LPID":9,"CNUM":32,"OTIME":"2017-08-29 18:19:36","TYPE":2,"MEMO":"兑换"},{"SOURCE_ID":37,"ISVIEW":0,"NUM":5,"OPERATOR":"","CSKC":146,"SOURCE":2,"LPMC":"6万-微波炉","LPZP":"2017082901404951058.jpg","XHGG":"美的","ID":106,"LPID":6,"CNUM":165,"OTIME":"2017-08-30 09:34:11","TYPE":2,"MEMO":"兑换"},{"SOURCE_ID":38,"ISVIEW":0,"NUM":1,"OPERATOR":"","CSKC":64,"SOURCE":2,"LPMC":"10万-净水器","LPZP":"2017082901402298275.jpg","XHGG":"YAMAHA","ID":107,"LPID":8,"CNUM":39,"OTIME":"2017-08-30 09:35:46","TYPE":2,"MEMO":"兑换"},{"SOURCE_ID":39,"ISVIEW":0,"NUM":8,"OPERATOR":"","CSKC":32,"SOURCE":2,"LPMC":"2万-电磁炉","LPZP":"2017082901453318685.jpg","XHGG":"美的","ID":108,"LPID":9,"CNUM":29,"OTIME":"2017-08-30 09:41:27","TYPE":2,"MEMO":"兑换"},{"SOURCE_ID":40,"ISVIEW":0,"NUM":8,"OPERATOR":"","CSKC":32,"SOURCE":2,"LPMC":"2万-电磁炉","LPZP":"2017082901453318685.jpg","XHGG":"美的","ID":109,"LPID":9,"CNUM":30,"OTIME":"2017-08-30 09:43:31","TYPE":2,"MEMO":"兑换"},{"SOURCE_ID":41,"ISVIEW":0,"NUM":8,"OPERATOR":"","CSKC":146,"SOURCE":2,"LPMC":"6万-微波炉","LPZP":"2017082901404951058.jpg","XHGG":"美的","ID":112,"LPID":6,"CNUM":165,"OTIME":"2017-08-30 10:23:45","TYPE":2,"MEMO":"兑换"},{"SOURCE_ID":42,"ISVIEW":0,"NUM":1,"OPERATOR":"","CSKC":64,"SOURCE":2,"LPMC":"10万-净水器","LPZP":"2017082901402298275.jpg","XHGG":"YAMAHA","ID":113,"LPID":8,"CNUM":41,"OTIME":"2017-08-30 10:26:13","TYPE":2,"MEMO":"兑换"},{"SOURCE_ID":43,"ISVIEW":0,"NUM":2,"OPERATOR":"","CSKC":5,"SOURCE":2,"LPMC":"3万-智能电饭煲","LPZP":"2017082901410844136.jpg","XHGG":"美的","ID":114,"LPID":7,"CNUM":5,"OTIME":"2017-08-30 10:50:56","TYPE":2,"MEMO":"兑换"},{"SOURCE_ID":44,"ISVIEW":0,"NUM":7,"OPERATOR":"","CSKC":146,"SOURCE":2,"LPMC":"6万-微波炉","LPZP":"2017082901404951058.jpg","XHGG":"美的","ID":115,"LPID":6,"CNUM":167,"OTIME":"2017-08-30 10:52:27","TYPE":2,"MEMO":"兑换"},{"SOURCE_ID":45,"ISVIEW":0,"NUM":3,"OPERATOR":"","CSKC":5,"SOURCE":2,"LPMC":"3万-智能电饭煲","LPZP":"2017082901410844136.jpg","XHGG":"美的","ID":116,"LPID":7,"CNUM":5,"OTIME":"2017-08-30 10:55:28","TYPE":2,"MEMO":"兑换"},{"SOURCE_ID":46,"ISVIEW":0,"NUM":0,"OPERATOR":"","CSKC":64,"SOURCE":2,"LPMC":"10万-净水器","LPZP":"2017082901402298275.jpg","XHGG":"YAMAHA","ID":117,"LPID":8,"CNUM":45,"OTIME":"2017-08-30 10:58:12","TYPE":2,"MEMO":"兑换"},{"SOURCE_ID":46,"ISVIEW":0,"NUM":0,"OPERATOR":"","CSKC":146,"SOURCE":2,"LPMC":"6万-微波炉","LPZP":"2017082901404951058.jpg","XHGG":"美的","ID":118,"LPID":6,"CNUM":167,"OTIME":"2017-08-30 10:58:12","TYPE":2,"MEMO":"兑换"},{"SOURCE_ID":46,"ISVIEW":0,"NUM":0,"OPERATOR":"","CSKC":32,"SOURCE":2,"LPMC":"2万-电磁炉","LPZP":"2017082901453318685.jpg","XHGG":"美的","ID":119,"LPID":9,"CNUM":32,"OTIME":"2017-08-30 10:58:12","TYPE":2,"MEMO":"兑换"},{"SOURCE_ID":47,"ISVIEW":0,"NUM":4,"OPERATOR":"","CSKC":64,"SOURCE":2,"LPMC":"10万-净水器","LPZP":"2017082901402298275.jpg","XHGG":"YAMAHA","ID":128,"LPID":8,"CNUM":68,"OTIME":"2017-08-30 11:20:45","TYPE":2,"MEMO":"兑换"},{"SOURCE_ID":47,"ISVIEW":0,"NUM":4,"OPERATOR":"","CSKC":146,"SOURCE":2,"LPMC":"6万-微波炉","LPZP":"2017082901404951058.jpg","XHGG":"美的","ID":129,"LPID":6,"CNUM":150,"OTIME":"2017-08-30 11:20:45","TYPE":2,"MEMO":"兑换"},{"SOURCE_ID":47,"ISVIEW":0,"NUM":3,"OPERATOR":"","CSKC":32,"SOURCE":2,"LPMC":"2万-电磁炉","LPZP":"2017082901453318685.jpg","XHGG":"美的","ID":130,"LPID":9,"CNUM":35,"OTIME":"2017-08-30 11:20:45","TYPE":2,"MEMO":"兑换"}]
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
         * SOURCE_ID : 12
         * ISVIEW : 0
         * NUM : 0
         * OPERATOR :
         * CSKC : 146
         * SOURCE : 2
         * LPMC : 6万-微波炉
         * LPZP : 2017082901404951058.jpg
         * XHGG : 美的
         * ID : 27
         * LPID : 6
         * CNUM : 100
         * OTIME : 2017-08-22 15:40:26
         * TYPE : 2
         * MEMO : 兑换
         */

        @SerializedName("SOURCE_ID")
        private int SOURCEID;
        @SerializedName("ISVIEW")
        private int ISVIEW;
        @SerializedName("NUM")
        private int NUM;
        @SerializedName("OPERATOR")
        private String OPERATOR;
        @SerializedName("CSKC")
        private int CSKC;
        @SerializedName("SOURCE")
        private int SOURCE;
        @SerializedName("LPMC")
        private String LPMC;
        @SerializedName("LPZP")
        private String LPZP;
        @SerializedName("XHGG")
        private String XHGG;
        @SerializedName("ID")
        private int ID;
        @SerializedName("LPID")
        private int LPID;
        @SerializedName("CNUM")
        private int CNUM;
        @SerializedName("OTIME")
        private String OTIME;
        @SerializedName("TYPE")
        private int TYPE;
        @SerializedName("MEMO")
        private String MEMO;

        public int getSOURCEID() {
            return SOURCEID;
        }

        public void setSOURCEID(int sOURCEID) {
            SOURCEID = sOURCEID;
        }

        public int getISVIEW() {
            return ISVIEW;
        }

        public void setISVIEW(int iSVIEW) {
            ISVIEW = iSVIEW;
        }

        public int getNUM() {
            return NUM;
        }

        public void setNUM(int nUM) {
            NUM = nUM;
        }

        public String getOPERATOR() {
            return OPERATOR;
        }

        public void setOPERATOR(String oPERATOR) {
            OPERATOR = oPERATOR;
        }

        public int getCSKC() {
            return CSKC;
        }

        public void setCSKC(int cSKC) {
            CSKC = cSKC;
        }

        public int getSOURCE() {
            return SOURCE;
        }

        public void setSOURCE(int sOURCE) {
            SOURCE = sOURCE;
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

        public int getLPID() {
            return LPID;
        }

        public void setLPID(int lPID) {
            LPID = lPID;
        }

        public int getCNUM() {
            return CNUM;
        }

        public void setCNUM(int cNUM) {
            CNUM = cNUM;
        }

        public String getOTIME() {
            return OTIME;
        }

        public void setOTIME(String oTIME) {
            OTIME = oTIME;
        }

        public int getTYPE() {
            return TYPE;
        }

        public void setTYPE(int tYPE) {
            TYPE = tYPE;
        }

        public String getMEMO() {
            return MEMO;
        }

        public void setMEMO(String mEMO) {
            MEMO = mEMO;
        }
    }
}
