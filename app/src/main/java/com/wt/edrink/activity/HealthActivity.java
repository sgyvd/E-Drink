package com.wt.edrink.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.wt.edrink.Constants;
import com.wt.edrink.R;
import com.wt.edrink.base.BaseActivity;
import com.wt.edrink.bean.CommonBean;
import com.wt.edrink.http.HttpManage;
import com.wt.edrink.http.JavaBeanRequest;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import butterknife.BindView;
import cn.droidlover.xdroid.kit.ToastUtils;

/**
 * Created by admin on 2017/5/25.
 */

public class HealthActivity extends BaseActivity {

    @BindView(R.id.tv_health_today_total)
    TextView tvTodayTotal;
    @BindView(R.id.tv_health_status)
    TextView tvStatus;

    @Override
    public void initData(Bundle savedInstanceState) {
        setToolbarTitle("健康计划");
        setShowBack(true);

        httpTodayTotal();
        httpHealthStatus();

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_health;
    }

    /**
     * 今日饮水量
     */
    private void httpTodayTotal() {
        Request<CommonBean> request = new JavaBeanRequest<CommonBean>(Constants.URL_TODAY_TOTAL, CommonBean.class);
        request.add(Constants.AUTH_KEY, getAuthKey());
        request.add(Constants.DEVICE_ID, getDeviceId());
        HttpManage.httpRequest(0, request, onResponseListener);
    }

    /**
     * 健康值
     */
    private void httpHealthStatus() {
        Request<CommonBean> request = new JavaBeanRequest<CommonBean>(Constants.URL_HEALTH_STATUS, CommonBean.class);
        request.add(Constants.AUTH_KEY, getAuthKey());
        request.add(Constants.DEVICE_ID, getDeviceId());
        HttpManage.httpRequest(1, request, onResponseListener);
    }


    private OnResponseListener onResponseListener = new OnResponseListener() {
        @Override
        public void onStart(int what) {

        }

        @Override
        public void onSucceed(int what, Response response) {
            switch (what) {
                case 0: //今日饮水量
                    CommonBean data1 = (CommonBean) response.get();
                    if (data1.getError_code() == 10023) {
                        tvTodayTotal.setText(data1.getResult());
                    }
                    break;
                case 1: //健康值
                    CommonBean data2 = (CommonBean) response.get();
                    if (data2.getError_code() == 10025) {
                        tvStatus.setText(data2.getResult() + "");
                    }
                    break;
            }
        }

        @Override
        public void onFailed(int what, Response response) {
            ToastUtils.showShort(context, "网络请求失败");
        }

        @Override
        public void onFinish(int what) {

        }
    };
}
