package com.wt.edrink;

import android.app.Application;

import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.OkHttpNetworkExecutor;

/**
 * Created by 美时美课 on 2017/5/23.
 */

public class MyApp extends Application {
    private static MyApp INSTANCE;


    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;



        NoHttp.initialize(this, new NoHttp.Config()
                // 设置全局连接超时时间，单位毫秒，默认10s。
                .setConnectTimeout(30 * 1000)
                // 设置全局服务器响应超时时间，单位毫秒，默认10s。
                .setReadTimeout(10 * 1000)
                // 配置网络层，默认使用URLConnection，如果想用OkHttp：OkHttpNetworkExecutor。
                .setNetworkExecutor(new OkHttpNetworkExecutor())
        );

        /*Logger.setDebug(true); // 开启NoHttp调试模式。
        Logger.setTag("NoHttpSample----"); // 设置NoHttp打印Log的TAG。*/
    }

    public static MyApp getInstance() {
        return INSTANCE;
    }
}
