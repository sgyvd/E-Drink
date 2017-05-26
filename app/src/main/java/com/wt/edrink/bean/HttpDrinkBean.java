package com.wt.edrink.bean;

/**
 * Created by 美时美课 on 2017/5/26.
 */

public class HttpDrinkBean {

    /**
     * error_code : 10019
     * reason : 获取饮水时间成功
     * result : {"device_id":"smart_cup-0001","firstTime":"08:30:00","secondTime":"09:30:00","thirdTime":"10:30:00","forthTime":"11:00:00","fifthTime":"13:00:00","sixthTime":"14:00:00","seventhTime":"15:30:00","eighthTime":"17:30:00","firstWaterValue":"225","secondWaterValue":"225","thirdWaterValue":"225","forthWaterValue":"225","fifthWaterValue":"225","sixthWaterValue":"225","seventhWaterValue":"225","eighthWaterValue":"225"}
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
         * device_id : smart_cup-0001
         * firstTime : 08:30:00
         * secondTime : 09:30:00
         * thirdTime : 10:30:00
         * forthTime : 11:00:00
         * fifthTime : 13:00:00
         * sixthTime : 14:00:00
         * seventhTime : 15:30:00
         * eighthTime : 17:30:00
         * firstWaterValue : 225
         * secondWaterValue : 225
         * thirdWaterValue : 225
         * forthWaterValue : 225
         * fifthWaterValue : 225
         * sixthWaterValue : 225
         * seventhWaterValue : 225
         * eighthWaterValue : 225
         */

        private String device_id;
        private String firstTime;
        private String secondTime;
        private String thirdTime;
        private String forthTime;
        private String fifthTime;
        private String sixthTime;
        private String seventhTime;
        private String eighthTime;
        private String firstWaterValue;
        private String secondWaterValue;
        private String thirdWaterValue;
        private String forthWaterValue;
        private String fifthWaterValue;
        private String sixthWaterValue;
        private String seventhWaterValue;
        private String eighthWaterValue;

        public String getDevice_id() {
            return device_id;
        }

        public void setDevice_id(String device_id) {
            this.device_id = device_id;
        }

        public String getFirstTime() {
            return firstTime;
        }

        public void setFirstTime(String firstTime) {
            this.firstTime = firstTime;
        }

        public String getSecondTime() {
            return secondTime;
        }

        public void setSecondTime(String secondTime) {
            this.secondTime = secondTime;
        }

        public String getThirdTime() {
            return thirdTime;
        }

        public void setThirdTime(String thirdTime) {
            this.thirdTime = thirdTime;
        }

        public String getForthTime() {
            return forthTime;
        }

        public void setForthTime(String forthTime) {
            this.forthTime = forthTime;
        }

        public String getFifthTime() {
            return fifthTime;
        }

        public void setFifthTime(String fifthTime) {
            this.fifthTime = fifthTime;
        }

        public String getSixthTime() {
            return sixthTime;
        }

        public void setSixthTime(String sixthTime) {
            this.sixthTime = sixthTime;
        }

        public String getSeventhTime() {
            return seventhTime;
        }

        public void setSeventhTime(String seventhTime) {
            this.seventhTime = seventhTime;
        }

        public String getEighthTime() {
            return eighthTime;
        }

        public void setEighthTime(String eighthTime) {
            this.eighthTime = eighthTime;
        }

        public String getFirstWaterValue() {
            return firstWaterValue;
        }

        public void setFirstWaterValue(String firstWaterValue) {
            this.firstWaterValue = firstWaterValue;
        }

        public String getSecondWaterValue() {
            return secondWaterValue;
        }

        public void setSecondWaterValue(String secondWaterValue) {
            this.secondWaterValue = secondWaterValue;
        }

        public String getThirdWaterValue() {
            return thirdWaterValue;
        }

        public void setThirdWaterValue(String thirdWaterValue) {
            this.thirdWaterValue = thirdWaterValue;
        }

        public String getForthWaterValue() {
            return forthWaterValue;
        }

        public void setForthWaterValue(String forthWaterValue) {
            this.forthWaterValue = forthWaterValue;
        }

        public String getFifthWaterValue() {
            return fifthWaterValue;
        }

        public void setFifthWaterValue(String fifthWaterValue) {
            this.fifthWaterValue = fifthWaterValue;
        }

        public String getSixthWaterValue() {
            return sixthWaterValue;
        }

        public void setSixthWaterValue(String sixthWaterValue) {
            this.sixthWaterValue = sixthWaterValue;
        }

        public String getSeventhWaterValue() {
            return seventhWaterValue;
        }

        public void setSeventhWaterValue(String seventhWaterValue) {
            this.seventhWaterValue = seventhWaterValue;
        }

        public String getEighthWaterValue() {
            return eighthWaterValue;
        }

        public void setEighthWaterValue(String eighthWaterValue) {
            this.eighthWaterValue = eighthWaterValue;
        }
    }
}
