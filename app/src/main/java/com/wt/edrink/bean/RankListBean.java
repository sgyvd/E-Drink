package com.wt.edrink.bean;

import java.util.List;

/**
 * Created by 美时美课 on 2017/5/25.
 */

public class RankListBean {


    /**
     * error_code : 10026
     * reason : 获取排行榜完成
     * result : [{"userName":"-3.****+15","healthyStatus":"0.00"}]
     */

    private int error_code;
    private String reason;
    private List<RankBean> result;

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

    public List<RankBean> getResult() {
        return result;
    }

    public void setResult(List<RankBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * userName : -3.****+15
         * healthyStatus : 0.00
         */

        private String userName;
        private String healthyStatus;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getHealthyStatus() {
            return healthyStatus;
        }

        public void setHealthyStatus(String healthyStatus) {
            this.healthyStatus = healthyStatus;
        }
    }
}
