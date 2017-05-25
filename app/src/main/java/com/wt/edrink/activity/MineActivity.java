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

    @BindView(R.id.switch_mine_rank)
    SwitchCompat switchRank;
    @BindView(R.id.tv_mine_device_id)
    TextView tvDeviceId;

    @Override
    public void initData(Bundle savedInstanceState) {
        setToolbarTitle("我的");
        setShowBack(true);

        httpRankStatus();
    }

    @Override
    public void setListener() {
        super.setListener();

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_mine;
    }


    @OnClick({R.id.ll_mine_select_device, R.id.tv_mine_quit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_mine_select_device:
                Intents.getIntents().Intent(context, MyDevicesActivity.class, null);
                break;
            case R.id.tv_mine_quit:
                userPrefs.clearAuthKey();
                userPrefs.clearDeviceId();

                Intents.getIntents().Intent(context, LoginActivity.class, null);
                ExitApp.getInstance().exit();
                break;
        }
    }

    /**
     * 获取rank展示状态
     */
    private void httpRankStatus() {
        Request<CommonBean> request = new JavaBeanRequest<CommonBean>(Constants.URL_RANK_STATUS, CommonBean.class);
        request.add(Constants.AUTH_KEY, getAuthKey());
        request.add(Constants.DEVICE_ID, getDeviceId());
        HttpManage.httpRequest(0, request, onResponseListener);
    }

    /**
     * Rank开关
     */
    private void httpRankSwitch(int isShow) {
        Request<CommonBean> request = new JavaBeanRequest<CommonBean>(Constants.URL_RANK_SWITCH, CommonBean.class);
        request.add(Constants.AUTH_KEY, getAuthKey());
        request.add(Constants.DEVICE_ID, getDeviceId());
        request.add(Constants.SWITCH, isShow);
        HttpManage.httpRequest(1, request, onResponseListener);
    }

    private OnResponseListener onResponseListener = new OnResponseListener() {
        @Override
        public void onStart(int what) {

        }

        @Override
        public void onSucceed(int what, Response response) {
            CommonBean data = (CommonBean) response.get();
            switch (what) {
                case 0:
                    Log.e(TAG, "排行榜状态error_code:" + data.getError_code() + "----reason:" + data.getReason() + "----result:" + data.getResult());
                    if (data.getError_code() == 10036) {
                        switchRank.setChecked(false);
                    } else if (data.getError_code() == 10035) {
                        switchRank.setChecked(true);
                    } else {
                        switchRank.setEnabled(false);
                    }

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
                    break;
                case 1:
                    Log.e(TAG, "选择排行榜error_code:" + data.getError_code() + "----reason:" + data.getReason() + "----result:" + data.getResult());
                    if (data.getError_code() == 10036) {
                        ToastUtils.showShort(context, "关闭");
                    } else if (data.getError_code() == 10037) {
                        ToastUtils.showShort(context, "打开");
                    } else {
                        ToastUtils.showShort(context, "失败");
                    }
                    break;
            }
        }

        @Override
        public void onFailed(int what, Response response) {

        }

        @Override
        public void onFinish(int what) {

        }
    };


    @Override
    protected void onResume() {
        super.onResume();
        if (userPrefs.getDeviceId() != null) {
            tvDeviceId.setText(userPrefs.getDeviceId());
        }
    }
}
