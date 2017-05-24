package com.wt.edrink.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.wt.edrink.Constants;
import com.wt.edrink.R;
import com.wt.edrink.base.BaseActivity;
import com.wt.edrink.bean.HomeBean;
import com.wt.edrink.http.HttpManage;
import com.wt.edrink.http.JavaBeanRequest;
import com.wt.edrink.utils.Intents;
import com.wt.edrink.utils.UserPrefs;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroid.kit.ToastUtils;


public class MainActivity extends BaseActivity {
    private String TAG = "MainActivity";

    private final static int HTTP_HOME_PAGE = 01;

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


    private UserPrefs userPrefs;


    @Override
    public void initData(Bundle savedInstanceState) {
        userPrefs = new UserPrefs(context);
    }

    @Override
    public void setListener() {
        super.setListener();
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                httpHomeInfo();
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }


    @OnClick({R.id.rl_main_rank, R.id.rl_main_me, R.id.rl_main_scan})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_main_rank:
                Intents.getIntents().Intent(this, RankActivity.class, null);
                break;
            case R.id.rl_main_me:
                Intents.getIntents().Intent(this, MineActivity.class, null);
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

        String url = Constants.URL_HOME_PAGE + userPrefs.getCupnum() + "/latest.json";
        Log.e(TAG, "--------------" + url);

        final Request<HomeBean> request = new JavaBeanRequest<HomeBean>(url, RequestMethod.GET, HomeBean.class);
        // 添加到请求队列
        HttpManage.httpRequest(HTTP_HOME_PAGE, request, new OnResponseListener<HomeBean>() {
            @Override
            public void onStart(int what) {
                swipeLayout.setRefreshing(true);
            }

            @Override
            public void onSucceed(int what, Response<HomeBean> response) {
                HomeBean data = response.get();
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
                String time = data.getMonth() + "-" + data.getDay() + " " + data.getHour() + "-" + data.getMinute();
                tvTime.setText(time);
                swipeLayout.setRefreshing(false);
            }

            @Override
            public void onFailed(int what, Response<HomeBean> response) {
                ToastUtils.showLong(context, "网络错误");
            }

            @Override
            public void onFinish(int what) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (userPrefs.getCupnum() == null) {
            ToastUtils.showLong(context, "请先添加水杯");
        } else {
            httpHomeInfo();
        }
    }
}
