package com.wt.edrink.bean;

import java.util.List;

/**
 * Created by 美时美课 on 2017/5/25.
 */

public class RankBean {

    private int error_code;
    private String reason;
    private List<RankListBean> result;

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

    public List<RankListBean> getResult() {
        return result;
    }

    public void setResult(List<RankListBean> result) {
        this.result = result;
    }


}
