package com.wt.edrink.bean;

/**
 * Created by 美时美课 on 2017/5/26.
 */

public class InfoBean {
    /**
     * device_id : 1
     * nickname : 123
     * birthday : 1995-12-12
     * height : 170
     * weight : 50
     * sex : 保密
     * HealthyStatus : 123
     */

    private String device_id;
    private String nickname;
    private String birthday;
    private String height;
    private String weight;
    private String sex;
    private String HealthyStatus;

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHealthyStatus() {
        return HealthyStatus;
    }

    public void setHealthyStatus(String HealthyStatus) {
        this.HealthyStatus = HealthyStatus;
    }
}
