package com.wt.edrink.bean;

import java.util.List;

/**
 * Created by 美时美课 on 2017/5/25.
 */

public class DeviceListBean {

    /**
     * error_code : 10012
     * reason : 设备列表获取成功
     * result : [{"device_id":"smart_cup-0001","device_pwd":"786547","status":"1","userid":"1","userName":"15706844661","birthday":null,"height":null,"weight":null,"sex":"保密","nickname":null,"HealthyStatus":null,"historyValue":"0","todayTotal":"0","Total":"0","noticeWay":"0","noticeFile":null,"showRank":"0","mobileNotice":"0","cupNotice":"0"}]
     */

    private int error_code;
    private String reason;
    private List<DeviceBean> result;

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

    public List<DeviceBean> getResult() {
        return result;
    }

    public void setResult(List<DeviceBean> result) {
        this.result = result;
    }


}
