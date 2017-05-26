package com.wt.edrink.bean;

/**
 * Created by 美时美课 on 2017/5/26.
 */

public class HttpInfoBean {


    /**
     * error_code : 10018
     * reason : 获取用户信息完成
     * result : {"device_id":"1","nickname":"123","birthday":"1995-12-12","height":"170","weight":"50","sex":"保密","HealthyStatus":"123"}
     */

    private int error_code;
    private String reason;
    private InfoBean result;

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

    public InfoBean getResult() {
        return result;
    }

    public void setResult(InfoBean result) {
        this.result = result;
    }

}
