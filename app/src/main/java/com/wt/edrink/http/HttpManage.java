package com.wt.edrink.http;

import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;

/**
 * http请求管理
 * Created by 美时美课 on 2017/3/22.
 */

public class HttpManage {
    private static String TAG = "HttpManage";


    /*首页*/
    public static final String SIGN_HOME_PAGE = "sign_page_request";



    public static <T> void httpRequest(int what, Request<T> request, OnResponseListener<T> listener) {
        // 这里设置一个sign给这个请求。
        request.setCancelSign(SIGN_HOME_PAGE);
        CallServer.getInstance().add(what, request, listener);
    }


}
