package com.wt.edrink;

/**
 * Created by 美时美课 on 2017/5/24.
 */

public class Constants {

    public static final String AUTH_KEY = "authkey";
    public static final String USERNAME = "userName";
    public static final String PASSWORD = "password";

    public static final String DEVICE_ID = "device_id";
    public static final String DEVICE_PWD = "device_pwd";

    public static final String SWITCH = "switch";

    private static String BASE_URL = "http://182.254.159.215";

    //注册
    public static final String URL_SIGN_UP = BASE_URL + "/user/register";

    //登录
    public static final String URL_LOGIN = BASE_URL + "/user/login";

    //首页
    public static final String URL_HOME_PAGE = "https://dddddooo.wilddogio.com/";

    //绑定设备
    public static final String URL_BING = BASE_URL + "/cup/bind";
    //已绑定设备列表
    public static final String URL_DEVICE_LIST = BASE_URL + "/cup/device_list";

    //排行榜
    public static final String URL_RANK = BASE_URL + "/data/rank";
    //排行榜状态
    public static final String URL_RANK_STATUS = BASE_URL + "/data/ShowRankStatus";
    //排行榜开关
    public static final String URL_RANK_SWITCH = BASE_URL + "/data/SwitchRank";

}


