package com.wt.edrink.activity;

import android.os.Bundle;
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

    private final static int HTTP_HOME_PAGE = 01;

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
        if (userPrefs.getCupnum() == null) {
            tvWaterTemp.setText("- -");
            tvWaterLevel.setText("- -");
            tvAirTemp.setText("- -");
            tvAirHumidity.setText("- -");
            ToastUtils.showLong(context, "请先添加水杯");
        }
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
        Request<HomeBean> request = new JavaBeanRequest<HomeBean>(Constants.URL_HOME_PAGE, RequestMethod.POST, HomeBean.class);
        // 添加到请求队列
        HttpManage.httpRequest(HTTP_HOME_PAGE, request, onResponseListener);
    }

    private OnResponseListener onResponseListener = new OnResponseListener() {
        @Override
        public void onStart(int what) {

        }

        @Override
        public void onSucceed(int what, Response response) {

        }

        @Override
        public void onFailed(int what, Response response) {

        }

        @Override
        public void onFinish(int what) {

        }
    };

}
