package com.wt.edrink.utils;

import android.content.Context;

import cn.droidlover.xdroid.cache.SharedPref;

/**
 * Created by 美时美课 on 2017/3/29.
 */

public class UserPrefs {
    private Context context;

    public final static String KEY_USER_NAME = "user_name";
    public final static String KEY_CUP_NUM = "cup_num";

    public UserPrefs(Context context) {
        this.context = context;
    }

    public void setUserName(String username) {
        SharedPref.getInstance(context).putString(KEY_USER_NAME, username);
    }

    public String getUserName() {
        return SharedPref.getInstance(context).getString(KEY_USER_NAME, null);
    }

    public void clearUserName() {
        SharedPref.getInstance(context).putString(KEY_USER_NAME, null);
    }

    public void setCupnum(String num){
        SharedPref.getInstance(context).putString(KEY_CUP_NUM, num);
    }
    public String getCupnum(){
        return SharedPref.getInstance(context).getString(KEY_CUP_NUM,null);
    }
}
