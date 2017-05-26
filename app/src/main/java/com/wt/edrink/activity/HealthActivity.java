package com.wt.edrink.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.wt.edrink.Constants;
import com.wt.edrink.R;
import com.wt.edrink.base.BaseActivity;
import com.wt.edrink.bean.CommonBean;
import com.wt.edrink.bean.HttpDrinkBean;
import com.wt.edrink.http.HttpManage;
import com.wt.edrink.http.JavaBeanRequest;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroid.kit.DateUtils;
import cn.droidlover.xdroid.kit.ToastUtils;

/**
 * Created by admin on 2017/5/25.
 */

public class HealthActivity extends BaseActivity {
    private static String TAG = "HealthActivity";

    @BindView(R.id.tv_health_today_total)
    TextView tvTodayTotal;
    @BindView(R.id.tv_health_status)
    TextView tvStatus;

    @BindView(R.id.ll_height_table)
    LinearLayout llTable;
    @BindView(R.id.btn_height_update)
    Button btnUpdate;
    @BindView(R.id.tv_health_time_1)
    TextView tvHealthTime1;
    @BindView(R.id.tv_health_time_2)
    TextView tvHealthTime2;
    @BindView(R.id.tv_health_time_3)
    TextView tvHealthTime3;
    @BindView(R.id.tv_health_time_4)
    TextView tvHealthTime4;
    @BindView(R.id.tv_health_time_5)
    TextView tvHealthTime5;
    @BindView(R.id.tv_health_time_6)
    TextView tvHealthTime6;
    @BindView(R.id.tv_health_time_7)
    TextView tvHealthTime7;
    @BindView(R.id.tv_health_time_8)
    TextView tvHealthTime8;

    @BindView(R.id.tv_health_1)
    TextView tvHealth1;
    @BindView(R.id.tv_health_2)
    TextView tvHealth2;
    @BindView(R.id.tv_health_3)
    TextView tvHealth3;
    @BindView(R.id.tv_health_4)
    TextView tvHealth4;
    @BindView(R.id.tv_health_5)
    TextView tvHealth5;
    @BindView(R.id.tv_health_6)
    TextView tvHealth6;
    @BindView(R.id.tv_health_7)
    TextView tvHealth7;
    @BindView(R.id.tv_health_8)
    TextView tvHealth8;

    private TimePickerView pvTime;


    @Override
    public void initData(Bundle savedInstanceState) {
        setToolbarTitle("健康计划");
        setShowBack(true);

        httpTodayTotal();
        httpHealthStatus();
        httpTable();

        initTimePike();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_health;
    }

    private void initTimePike() {
        pvTime = new TimePickerView.Builder(this, onTimeSelectListener)
                .setType(new boolean[]{false, false, false, true, true, false})
                .setDate(Calendar.getInstance())
                .setOutSideCancelable(true)
                .build();
    }

    private TimePickerView.OnTimeSelectListener onTimeSelectListener = new TimePickerView.OnTimeSelectListener() {
        @Override
        public void onTimeSelect(Date date, View v) {
            btnUpdate.setVisibility(View.VISIBLE);
            switch (v.getId()) {
                case R.id.tv_health_time_1:
                    tvHealthTime1.setText(DateUtils.formatDate(date, "HH:mm"));
                    break;
                case R.id.tv_health_time_2:
                    tvHealthTime2.setText(DateUtils.formatDate(date, "HH:mm"));
                    break;
                case R.id.tv_health_time_3:
                    tvHealthTime3.setText(DateUtils.formatDate(date, "HH:mm"));
                    break;
                case R.id.tv_health_time_4:
                    tvHealthTime4.setText(DateUtils.formatDate(date, "HH:mm"));
                    break;
                case R.id.tv_health_time_5:
                    tvHealthTime5.setText(DateUtils.formatDate(date, "HH:mm"));
                    break;
                case R.id.tv_health_time_6:
                    tvHealthTime6.setText(DateUtils.formatDate(date, "HH:mm"));
                    break;
                case R.id.tv_health_time_7:
                    tvHealthTime7.setText(DateUtils.formatDate(date, "HH:mm"));
                    break;
                case R.id.tv_health_time_8:
                    tvHealthTime8.setText(DateUtils.formatDate(date, "HH:mm"));
                    break;
            }
        }
    };

    @OnClick({R.id.tv_health_time_1, R.id.tv_health_time_2, R.id.tv_health_time_3, R.id.tv_health_time_4,
            R.id.tv_health_time_5, R.id.tv_health_time_6, R.id.tv_health_time_7, R.id.tv_health_time_8})
    public void onClick(View view) {
        pvTime.show(view);
    }

    @OnClick(R.id.btn_height_update)
    public void onUpdateClick(View view) {
        httpUpdate();
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

    /**
     * 饮水表
     */
    private void httpTable() {
        Request<HttpDrinkBean> request = new JavaBeanRequest<HttpDrinkBean>(Constants.URL_DRINK_TIME, HttpDrinkBean.class);
        request.add(Constants.AUTH_KEY, getAuthKey());
        request.add(Constants.DEVICE_ID, getDeviceId());
        HttpManage.httpRequest(2, request, onResponseListener);
    }

    /**
     * 上传时间表
     */
    private void httpUpdate() {
        Request<CommonBean> request = new JavaBeanRequest<CommonBean>(Constants.URL_DRINK_TIME_UPDATE, CommonBean.class);
        request.add(Constants.AUTH_KEY, getAuthKey());
        request.add(Constants.DEVICE_ID, getDeviceId());
        request.add("firstTime", tvHealthTime1.getText().toString());
        request.add("secondTime", tvHealthTime2.getText().toString());
        request.add("thirdTime", tvHealthTime3.getText().toString());
        request.add("forthTime", tvHealthTime4.getText().toString());
        request.add("fifthTime", tvHealthTime5.getText().toString());
        request.add("sixthTime", tvHealthTime6.getText().toString());
        request.add("seventhTime", tvHealthTime7.getText().toString());
        request.add("eighthTime", tvHealthTime8.getText().toString());
        HttpManage.httpRequest(3, request, onResponseListener);
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
                    Log.e(TAG, "今日饮水量error_code:" + data1.getError_code() + "----reason:" + data1.getReason() + "----result:" + data1.getResult());
                    if (data1.getError_code() == 10023) {
                        tvTodayTotal.setText(data1.getResult());
                    }
                    break;
                case 1: //健康值
                    CommonBean data2 = (CommonBean) response.get();
                    Log.e(TAG, "健康值error_code:" + data2.getError_code() + "----reason:" + data2.getReason() + "----result:" + data2.getResult());
                    if (data2.getError_code() == 10025) {
                        tvStatus.setText(data2.getResult() + "");
                    }
                    break;
                case 2:
                    HttpDrinkBean data3 = (HttpDrinkBean) response.get();
                    Log.e(TAG, "饮水表error_code:" + data3.getError_code() + "----reason:" + data3.getReason() + "----result:" + data3.getResult());
                    if (data3.getError_code() == 10019) {
                        llTable.setVisibility(View.VISIBLE);
                        tvHealthTime1.setText(data3.getResult().getFirstTime());
                        tvHealthTime2.setText(data3.getResult().getSecondTime());
                        tvHealthTime3.setText(data3.getResult().getThirdTime());
                        tvHealthTime4.setText(data3.getResult().getForthTime());
                        tvHealthTime5.setText(data3.getResult().getFifthTime());
                        tvHealthTime6.setText(data3.getResult().getSixthTime());
                        tvHealthTime7.setText(data3.getResult().getSeventhTime());
                        tvHealthTime8.setText(data3.getResult().getEighthTime());

                        tvHealth1.setText(data3.getResult().getFirstWaterValue());
                        tvHealth2.setText(data3.getResult().getSecondWaterValue());
                        tvHealth3.setText(data3.getResult().getThirdWaterValue());
                        tvHealth4.setText(data3.getResult().getForthWaterValue());
                        tvHealth5.setText(data3.getResult().getFifthWaterValue());
                        tvHealth6.setText(data3.getResult().getSixthWaterValue());
                        tvHealth7.setText(data3.getResult().getSeventhWaterValue());
                        tvHealth8.setText(data3.getResult().getEighthWaterValue());
                    }
                    break;
                case 3:
                    CommonBean data4 = (CommonBean) response.get();
                    Log.e(TAG, "饮水表error_code:" + data4.getError_code() + "----reason:" + data4.getReason() + "----result:" + data4.getResult());
                    if (data4.getError_code() == 10020) {
                        ToastUtils.showShort(context, "修改成功");
                        btnUpdate.setVisibility(View.GONE);
                    }
                    break;
            }
        }

        @Override
        public void onFailed(int what, Response response) {
            if (what == 2) {
                llTable.setVisibility(View.GONE);
            } else {
                ToastUtils.showShort(context, "网络请求失败");
            }
        }

        @Override
        public void onFinish(int what) {

        }
    };
}
