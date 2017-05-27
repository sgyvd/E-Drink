package com.wt.edrink.activity;

import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.wt.edrink.Constants;
import com.wt.edrink.R;
import com.wt.edrink.base.BaseActivity;
import com.wt.edrink.bean.CommonBean;
import com.wt.edrink.bean.HttpStatusBean;
import com.wt.edrink.http.HttpManage;
import com.wt.edrink.http.JavaBeanRequest;
import com.wt.edrink.utils.Intents;
import com.wt.edrink.utils.UserPrefs;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroid.kit.ExitApp;
import cn.droidlover.xdroid.kit.ToastUtils;

/**
 * Created by 美时美课 on 2017/5/24.
 */

public class MineActivity extends BaseActivity {
    private static String TAG = "MineActivity";

    UserPrefs userPrefs = new UserPrefs(context);

    @BindView(R.id.tv_mine_username)
    TextView tvUserName;
    @BindView(R.id.tv_mine_device_id)
    TextView tvDeviceId;
    @BindView(R.id.switch_mine_rank)
    SwitchCompat switchRank;
    @BindView(R.id.switch_mine_mobile)
    SwitchCompat switchMobile;
    @BindView(R.id.switch_mine_cup)
    SwitchCompat switchCup;

    @Override
    public void initData(Bundle savedInstanceState) {
        setToolbarTitle("我的");
        setShowBack(true);

        initView();
    }

    @Override
    public void setListener() {
        super.setListener();
        switchRank.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    httpRankSwitch(1);
                } else {
                    httpRankSwitch(0);
                }
            }
        });
        switchMobile.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    httpMobileSwitch(1);
                } else {
                    httpMobileSwitch(0);
                }
            }
        });
        switchCup.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    httpCupSwitch(1);
                } else {
                    httpCupSwitch(0);
                }
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_mine;
    }

    private void initView() {
        tvUserName.setText(userPrefs.getUserName());
    }


    @OnClick({R.id.ll_mine_info, R.id.ll_mine_select_device, R.id.tv_mine_quit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_mine_info:
                if (userPrefs.getDeviceId() != null) {
                    Intents.getIntents().Intent(context, MineInfoActivity.class, null);
                } else {
                    ToastUtils.showShort(context, "请先选择水杯");
                }
                break;
            case R.id.ll_mine_select_device:
                Intents.getIntents().Intent(context, MyDevicesActivity.class, null);
                break;
            case R.id.tv_mine_quit:
                userPrefs.clearAuthKeyAndUserName();
                userPrefs.clearDeviceId();

                Intents.getIntents().Intent(context, LoginActivity.class, null);
                ExitApp.getInstance().exit();
                break;
        }
    }

    /**
     * All状态
     */
    private void httpRankStatus() {
        Request<HttpStatusBean> request = new JavaBeanRequest<HttpStatusBean>(Constants.URL_ALL_STATUS, HttpStatusBean.class);
        request.add(Constants.AUTH_KEY, getAuthKey());
        request.add(Constants.DEVICE_ID, getDeviceId());
        HttpManage.httpRequest(0, request, onResponseListener);
    }

    /**
     * 排行榜开关
     */
    private void httpRankSwitch(int isShow) {
        Request<CommonBean> request = new JavaBeanRequest<CommonBean>(Constants.URL_RANK_SWITCH, CommonBean.class);
        request.add(Constants.AUTH_KEY, getAuthKey());
        request.add(Constants.DEVICE_ID, getDeviceId());
        request.add(Constants.SWITCH, isShow);
        HttpManage.httpRequest(1, request, onResponseListener);
    }

    /**
     * 手机提醒开关
     *
     * @param isShow
     */
    private void httpMobileSwitch(int isShow) {
        Request<CommonBean> request = new JavaBeanRequest<CommonBean>(Constants.URL_MOBILE_SWITCH, CommonBean.class);
        request.add(Constants.AUTH_KEY, getAuthKey());
        request.add(Constants.DEVICE_ID, getDeviceId());
        request.add(Constants.SWITCH, isShow);
        HttpManage.httpRequest(2, request, onResponseListener);
    }

    /**
     * 水杯提醒开关
     *
     * @param isShow
     */
    private void httpCupSwitch(int isShow) {
        Request<CommonBean> request = new JavaBeanRequest<CommonBean>(Constants.URL_CUP_SWITCH, CommonBean.class);
        request.add(Constants.AUTH_KEY, getAuthKey());
        request.add(Constants.DEVICE_ID, getDeviceId());
        request.add(Constants.SWITCH, isShow);
        HttpManage.httpRequest(3, request, onResponseListener);
    }

    private OnResponseListener onResponseListener = new OnResponseListener() {
        @Override
        public void onStart(int what) {

        }

        @Override
        public void onSucceed(int what, Response response) {

            switch (what) {
                case 0:
                    HttpStatusBean data = (HttpStatusBean) response.get();
                    switchRank.setEnabled(true);
                    Log.e(TAG, "状态error_code:" + data.getError_code() + "----reason:" + data.getReason() + "----result:" + data.getResult());
                    if (data.getError_code() == 10049) {
                        switchRank.setEnabled(true);
                        switchMobile.setEnabled(true);
                        switchCup.setEnabled(true);
                        if (data.getResult().getShowRank().equals("1")) {
                            switchRank.setChecked(true);
                        } else {
                            switchRank.setChecked(false);
                        }
                        if (data.getResult().getMobileNotice().equals("1")) {
                            switchMobile.setChecked(true);
                        } else {
                            switchMobile.setChecked(false);
                        }
                        if (data.getResult().getCupNotice().equals("1")) {
                            switchCup.setChecked(true);
                        } else {
                            switchCup.setChecked(false);
                        }
                    } else {
                        switchRank.setEnabled(false);
                        switchMobile.setEnabled(false);
                        switchCup.setEnabled(false);
                    }

                    break;
                case 1:
                    CommonBean data1 = (CommonBean) response.get();
                    Log.e(TAG, "排行榜开关error_code:" + data1.getError_code() + "----reason:" + data1.getReason() + "----result:" + data1.getResult());
                    if (data1.getError_code() == 10037) {

                    } else if (data1.getError_code() == 10038) {

                    } else {
                        ToastUtils.showShort(context, "失败");
                    }
                    break;
                case 2:
                    CommonBean data2 = (CommonBean) response.get();
                    Log.e(TAG, "手机提醒开关error_code:" + data2.getError_code() + "----reason:" + data2.getReason() + "----result:" + data2.getResult());
                    if (data2.getError_code() == 10041) {

                    } else if (data2.getError_code() == 10042) {

                    } else {
                        ToastUtils.showShort(context, "失败");
                    }
                    break;
                case 3:
                    CommonBean data3 = (CommonBean) response.get();
                    Log.e(TAG, "水杯提醒开关error_code:" + data3.getError_code() + "----reason:" + data3.getReason() + "----result:" + data3.getResult());
                    if (data3.getError_code() == 10045) {

                    } else if (data3.getError_code() == 10046) {

                    } else {
                        ToastUtils.showShort(context, "失败");
                    }
                    break;
            }
        }

        @Override
        public void onFailed(int what, Response response) {
            if (what == 0) {
                switchRank.setEnabled(false);
                switchMobile.setEnabled(false);
                switchCup.setEnabled(false);
            } else {
                ToastUtils.showShort(context, "网络请求失败");
            }
        }

        @Override
        public void onFinish(int what) {

        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        httpRankStatus();
        if (userPrefs.getDeviceId() != null) {
            tvDeviceId.setText(userPrefs.getDeviceId());
        } else {
            tvDeviceId.setText("");
        }
    }
}
