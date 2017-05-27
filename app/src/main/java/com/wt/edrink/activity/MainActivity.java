package com.wt.edrink.activity;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.wt.edrink.Constants;
import com.wt.edrink.R;
import com.wt.edrink.base.BaseActivity;
import com.wt.edrink.bean.HomeBean;
import com.wt.edrink.http.HttpManage;
import com.wt.edrink.http.JavaBeanRequest;
import com.wt.edrink.utils.Intents;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroid.kit.ToastUtils;
import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;


public class MainActivity extends BaseActivity {
    private String TAG = "MainActivity";

    @BindView(R.id.swipe_main)
    SwipeRefreshLayout swipeLayout;
    @BindView(R.id.tv_main_time)
    TextView tvTime;
    @BindView(R.id.tv_water_temp)
    TextView tvWaterTemp;
    @BindView(R.id.tv_water_level)
    TextView tvWaterLevel;
    @BindView(R.id.tv_air_temp)
    TextView tvAirTemp;
    @BindView(R.id.tv_air_humidity)
    TextView tvAirHumidity;

    @Override
    public void initData(Bundle savedInstanceState) {
        BasicPushNotificationBuilder builder = new BasicPushNotificationBuilder(this);
        builder.statusBarDrawable = R.drawable.jpush_notification_icon;
        builder.notificationFlags = Notification.FLAG_AUTO_CANCEL
                | Notification.FLAG_SHOW_LIGHTS;  //设置为自动消失和呼吸灯闪烁
        builder.notificationDefaults = Notification.DEFAULT_SOUND
                | Notification.DEFAULT_VIBRATE
                | Notification.DEFAULT_LIGHTS;  // 设置为铃声、震动、呼吸灯闪烁都要
        JPushInterface.setPushNotificationBuilder(1, builder);
    }


    @Override
    public void setListener() {
        super.setListener();
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (userPrefs.getDeviceId() == null) {
                    ToastUtils.showLong(context, "请先添加水杯");
                    swipeLayout.setRefreshing(false);
                } else {
                    httpHomeInfo();
                }
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }


    @OnClick({R.id.rl_main_rank, R.id.rl_main_me, R.id.rl_main_health, R.id.rl_main_scan})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_main_rank:
                Intents.getIntents().Intent(this, RankActivity.class, null);
                break;
            case R.id.rl_main_me:
                Intents.getIntents().Intent(this, MineActivity.class, null);
                break;
            case R.id.rl_main_health:
                Intents.getIntents().Intent(this, HealthActivity.class, null);
                break;
            case R.id.rl_main_scan:
                Intents.getIntents().Intent(this, ScanActivity.class, null);
                break;
        }
    }

    /**
     * http首页数据
     */
    private void httpHomeInfo() {
        String url = Constants.URL_HOME_PAGE + userPrefs.getDeviceId() + "/latest.json";
        Log.e(TAG, "--------------" + url);
        Request<HomeBean> request = new JavaBeanRequest<HomeBean>(url, RequestMethod.GET, HomeBean.class);
        // 添加到请求队列
        HttpManage.httpRequest(0, request, onResponseListener);
    }

    private OnResponseListener onResponseListener = new OnResponseListener() {
        @Override
        public void onStart(int what) {

        }

        @Override
        public void onSucceed(int what, Response response) {
            switch (what) {
                case 0:
                    HomeBean data = (HomeBean) response.get();
                    if (data == null) {
                        tvWaterTemp.setText("- -");
                        tvWaterLevel.setText("- -");
                        tvAirTemp.setText("- -");
                        tvAirHumidity.setText("- -");
                        ToastUtils.showShort(context, "无数据");
                    } else {
                        if (!TextUtils.isEmpty(data.getWater_temperature())) {
                            tvWaterTemp.setText(data.getWater_temperature());
                        }
                        if (!TextUtils.isEmpty(data.getWater_level())) {
                            tvWaterLevel.setText(data.getWater_level());
                        }
                        if (!TextUtils.isEmpty(data.getAir_temperature())) {
                            tvAirTemp.setText(data.getAir_temperature());
                        }
                        if (!TextUtils.isEmpty(data.getAir_humidity())) {
                            tvAirHumidity.setText(data.getAir_humidity());
                        }
                        String time = data.getMonth() + "-" + data.getDay() + " " + data.getHour() + ":" + data.getMinute();
                        tvTime.setText(time);
                    }
                    swipeLayout.setRefreshing(false);
                    break;
            }
        }

        @Override
        public void onFailed(int what, Response response) {
            switch (what) {
                case 0:
                    ToastUtils.showLong(context, "网络请求失败");
                    break;
            }
        }

        @Override
        public void onFinish(int what) {

        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        if (userPrefs.getDeviceId() == null) {
            tvWaterTemp.setText("- -");
            tvWaterLevel.setText("- -");
            tvAirTemp.setText("- -");
            tvAirHumidity.setText("- -");
            ToastUtils.showLong(context, "请先添加水杯");
        } else {
            httpHomeInfo();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
