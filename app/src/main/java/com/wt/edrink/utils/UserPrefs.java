package com.wt.edrink.utils;

import android.content.Context;

import cn.droidlover.xdroid.cache.SharedPref;

/**
 * Created by 美时美课 on 2017/3/29.
 */

public class UserPrefs {
    private Context context;

    public final static String KEY_AUTH = "auth";
    public final static String KEY_USERNAME = "user_name";
    public final static String KEY_CUP_NUM = "cup_num";

    public UserPrefs(Context context) {
        this.context = context;
    }

    public void setAuthKeyAndUserName(String authkey, String username) {
        SharedPref.getInstance(context).putString(KEY_AUTH, authkey);
        String str = username.substring(0, 3) + "****" + username.substring(7);
        SharedPref.getInstance(context).putString(KEY_USERNAME, str);
    }

    public String getAuthKey() {
        return SharedPref.getInstance(context).getString(KEY_AUTH, null);
    }

    public String getUserName() {
        return SharedPref.getInstance(context).getString(KEY_USERNAME, null);
    }

    public void clearAuthKeyAndUserName() {
        SharedPref.getInstance(context).putString(KEY_AUTH, null);
        SharedPref.getInstance(context).putString(KEY_USERNAME, null);
    }

    public void setDeviceId(String num) {
        SharedPref.getInstance(context).putString(KEY_CUP_NUM, num);
    }

    public String getDeviceId() {
        return SharedPref.getInstance(context).getString(KEY_CUP_NUM, null);
    }

    public void clearDeviceId() {
        SharedPref.getInstance(context).putString(KEY_CUP_NUM, null);
    }
}
