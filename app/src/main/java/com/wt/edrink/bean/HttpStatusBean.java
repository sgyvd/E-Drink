package com.wt.edrink.bean;

/**
 * Created by 美时美课 on 2017/5/26.
 */

public class HttpStatusBean {

    /**
     * error_code : 10049
     * reason : 获取所有提醒状态成功
     * result : {"showRank":"1","mobileNotice":"0","cupNotice":"0"}
     */

    private int error_code;
    private String reason;
    private ResultBean result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * showRank : 1
         * mobileNotice : 0
         * cupNotice : 0
         */

        private String showRank;
        private String mobileNotice;
        private String cupNotice;

        public String getShowRank() {
            return showRank;
        }

        public void setShowRank(String showRank) {
            this.showRank = showRank;
        }

        public String getMobileNotice() {
            return mobileNotice;
        }

        public void setMobileNotice(String mobileNotice) {
            this.mobileNotice = mobileNotice;
        }

        public String getCupNotice() {
            return cupNotice;
        }

        public void setCupNotice(String cupNotice) {
            this.cupNotice = cupNotice;
        }
    }
}
