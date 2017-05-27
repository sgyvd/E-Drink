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

    public static final String BIRTHDAY = "birthday";
    public static final String SEX = "sex";
    public static final String HEIGHT = "height";
    public static final String WEIGHT = "weight";
    public static final String HEALTHYSTATUS = "HealthyStatus";


    private static String BASE_URL = "http://182.254.159.215";

    //注册
    public static final String URL_SIGN_UP = BASE_URL + "/user/register";

    //登录
    public static final String URL_LOGIN = BASE_URL + "/user/login";

    //JPUSH ID上传
    public static final String URL_ID_UPDATE = BASE_URL +"/cup/updateJpush";

    //首页
    public static final String URL_HOME_PAGE = "https://dddddooo.wilddogio.com/";


    //绑定设备
    public static final String URL_BING = BASE_URL + "/cup/bind";
    //解绑设备
    public static final String URL_UN_BIND = BASE_URL + "/cup/unbind";
    //已绑定设备列表
    public static final String URL_DEVICE_LIST = BASE_URL + "/cup/device_list";


    //排行榜
    public static final String URL_RANK = BASE_URL + "/data/rank";

    //全部状态
    public static final String URL_ALL_STATUS = BASE_URL + "/notice/getNoticeStatus";

    //排行榜开关
    public static final String URL_RANK_SWITCH = BASE_URL + "/data/SwitchRank";
    //手机提醒开关
    public static final String URL_MOBILE_SWITCH = BASE_URL + "/notice/switchMobileNotice";
    //水杯提醒开关
    public static final String URL_CUP_SWITCH = BASE_URL + "/notice/switchCupNotice";


    //今日饮水量
    public static final String URL_TODAY_TOTAL = BASE_URL + "/data/todayTotal";
    //健康值
    public static final String URL_HEALTH_STATUS = BASE_URL + "/data/healthyStatus";
    //饮水时间表
    public static final String URL_DRINK_TIME = BASE_URL + "/cup/drinkTime";
    //饮水时间修改上传
    public static final String URL_DRINK_TIME_UPDATE = BASE_URL + "/cup/changeTime";


    //上传info
    public static final String URL_UPDATE_INFO = BASE_URL + "/cup/device2userinfo";
    //请求info
    public static final String URL_SHOW_INFO = BASE_URL + "/cup/showUserinfo";

}


