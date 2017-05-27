package com.wt.edrink.http;

import android.util.Log;

import com.wt.edrink.Constants;
import com.wt.edrink.bean.CommonBean;
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

    /**
     * 极光DeviceId上传
     */
    public static void httpUpdateJpush(String authkey, String deviceid, String jpushid, OnResponseListener listener) {
        Log.e("----------jpushId-----", jpushid);
        Request<CommonBean> request = new JavaBeanRequest<CommonBean>(Constants.URL_ID_UPDATE, CommonBean.class);
        request.add(Constants.AUTH_KEY, authkey);
        request.add(Constants.DEVICE_ID, deviceid);
        request.add("jpush_id", jpushid);
        httpRequest(0x001, request, listener);
    }


}
