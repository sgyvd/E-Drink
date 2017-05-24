package com.wt.edrink.bean;

/**
 * Created by 美时美课 on 2017/5/24.
 */

public class CommonBean {


    /**
     * error_code : 2
     * reason : 没有直接访问的权限
     * result : -1
     */

    private int error_code;
    private String reason;
    private String result;

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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
