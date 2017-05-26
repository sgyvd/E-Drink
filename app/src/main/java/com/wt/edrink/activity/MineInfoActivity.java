package com.wt.edrink.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.wt.edrink.Constants;
import com.wt.edrink.R;
import com.wt.edrink.base.BaseActivity;
import com.wt.edrink.bean.CommonBean;
import com.wt.edrink.bean.HttpInfoBean;
import com.wt.edrink.http.HttpManage;
import com.wt.edrink.http.JavaBeanRequest;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroid.kit.DateUtils;
import cn.droidlover.xdroid.kit.ToastUtils;

/**
 * Created by admin on 2017/5/25.
 */

public class MineInfoActivity extends BaseActivity {
    private static String TAG = "MineInfoActivity";


    @BindView(R.id.tv_info_birthday)
    TextView tvBirthday;
    @BindView(R.id.tv_info_sex)
    TextView tvSex;
    @BindView(R.id.tv_info_height)
    TextView tvHeight;
    @BindView(R.id.tv_info_weight)
    TextView tvWeight;
    @BindView(R.id.tv_info_status)
    TextView tvStatus;
    @BindView(R.id.btn_info_save)
    Button btnSave;

    private int intSex;
    private ArrayList<String> sexList = new ArrayList<>();
    private ArrayList<Integer> heightList = new ArrayList<>();
    private ArrayList<Integer> weightList = new ArrayList<>();
    private ArrayList<String> statusList = new ArrayList<>();

    private TimePickerView pvBirthday;
    private OptionsPickerView pvSex;
    private OptionsPickerView pvHeight;
    private OptionsPickerView pvWeight;
    private OptionsPickerView pvStatus;

    @Override
    public void initData(Bundle savedInstanceState) {
        setToolbarTitle("我的信息");
        setShowBack(true);

        httpInfo();

        init();
        initPickview();

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_mine_info;
    }

    private void init() {
        sexList.add("男");
        sexList.add("女");
        sexList.add("保密");

        for (int i = 20; i <= 250; i++) {
            heightList.add(i);
        }

        for (int i = 20; i <= 200; i++) {
            weightList.add(i);
        }

        statusList.add("亚健康");
        statusList.add("健康");
    }


    private void initPickview() {
        Calendar calendar = Calendar.getInstance();
        pvBirthday = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                tvBirthday.setText(DateUtils.formatDate(date, "yyyy-MM-dd"));
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false})
                .setRange(1900, calendar.get(Calendar.YEAR))
                .setDate(Calendar.getInstance())
                .setOutSideCancelable(true)
                .build();

        pvSex = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tvSex.setText(sexList.get(options1));
                intSex = options1 + 1;
            }
        })
                .setOutSideCancelable(true)
                .setTitleText("性别")
                .build();
        pvSex.setPicker(sexList);


        pvHeight = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tvHeight.setText(heightList.get(options1) + "");
            }
        })
                .setOutSideCancelable(true)
                .setLabels("CM", "", "")
                .setTitleText("身高")
                .setSelectOptions(155)
                .build();
        pvHeight.setPicker(heightList);


        pvWeight = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tvWeight.setText(weightList.get(options1) + "");
            }
        })
                .setOutSideCancelable(true)
                .setLabels("KG", "", "")
                .setTitleText("体重")
                .setSelectOptions(40)
                .build();
        pvWeight.setPicker(weightList);

        pvStatus = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                tvStatus.setText(statusList.get(options1));
            }
        })
                .setOutSideCancelable(true)
                .setTitleText("体重")
                .setSelectOptions(2)
                .build();
        pvStatus.setPicker(statusList);

    }

    @OnClick({R.id.ll_info_birthday, R.id.ll_info_sex, R.id.ll_info_height, R.id.ll_info_weight, R.id.btn_info_save, R.id.ll_info_status})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_info_birthday:
                pvBirthday.show();
                break;
            case R.id.ll_info_sex:
                pvSex.show();
                break;
            case R.id.ll_info_height:
                pvHeight.show();
                break;
            case R.id.ll_info_weight:
                pvWeight.show();
                break;
            case R.id.ll_info_status:
                pvStatus.show();
                break;
            case R.id.btn_info_save:
                if (!checkEmpty()) {
                    return;
                }
                httpUpdate();
                break;
        }
    }

    /**
     * 上传信息
     */
    private void httpUpdate() {
        Request<CommonBean> request = new JavaBeanRequest<CommonBean>(Constants.URL_UPDATE_INFO, CommonBean.class);
        request.add(Constants.AUTH_KEY, getAuthKey());
        request.add(Constants.DEVICE_ID, getDeviceId());
        request.add(Constants.BIRTHDAY, getBirthday());
        request.add(Constants.SEX, intSex);
        request.add(Constants.HEIGHT, getHeight());
        request.add(Constants.WEIGHT, getWeight());
        request.add(Constants.HEALTHYSTATUS, getStatus());
        HttpManage.httpRequest(0, request, onResponseListener);
    }

    /**
     * 请求信息
     */
    private void httpInfo() {
        Request<HttpInfoBean> request = new JavaBeanRequest<HttpInfoBean>(Constants.URL_SHOW_INFO, HttpInfoBean.class);
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
                case 0:
                    CommonBean data = (CommonBean) response.get();
                    Log.e(TAG, "上传信息error_code:" + data.getError_code() + "----reason:" + data.getReason() + "----result:" + data.getResult());
                    if (data.getError_code() == 10016) {
                        ToastUtils.showLong(context, "设置成功");
                        btnSave.setVisibility(View.GONE);
                    } else {
                        ToastUtils.showLong(context, data.getReason());
                    }
                    break;
                case 1:
                    HttpInfoBean data2 = (HttpInfoBean) response.get();
                    Log.e(TAG, "请求信息error_code:" + data2.getError_code() + "----reason:" + data2.getReason() + "----result:" + data2.getResult());
                    if (data2.getError_code() == 10018) {
                        if (data2.getResult().getBirthday() != null) {
                            btnSave.setVisibility(View.GONE);
                            tvBirthday.setText(data2.getResult().getBirthday());
                            tvSex.setText(data2.getResult().getSex());
                            tvHeight.setText(data2.getResult().getHeight());
                            tvWeight.setText(data2.getResult().getWeight());
                            tvStatus.setText(data2.getResult().getHealthyStatus());
                        } else {
                            btnSave.setVisibility(View.VISIBLE);
                        }
                    } else {
                        ToastUtils.showLong(context, data2.getReason());
                    }
                    break;
            }
        }

        @Override
        public void onFailed(int what, Response response) {
            ToastUtils.showLong(context, "网络请求失败");
        }

        @Override
        public void onFinish(int what) {

        }
    };

    private boolean checkEmpty() {
        if (TextUtils.isEmpty(getBirthday())) {
            ToastUtils.showShort(context, "请选择生日");
            return false;
        }
        if (TextUtils.isEmpty(getSex())) {
            ToastUtils.showShort(context, "请选择性别");
            return false;
        }
        if (TextUtils.isEmpty(getHeight())) {
            ToastUtils.showShort(context, "请选择身高");
            return false;
        }
        if (TextUtils.isEmpty(getWeight())) {
            ToastUtils.showShort(context, "请选择体重");
            return false;
        }
        if (TextUtils.isEmpty(getStatus())) {
            ToastUtils.showShort(context, "请选择健康状态");
            return false;
        }
        return true;
    }

    private String getBirthday() {
        return tvBirthday.getText().toString();
    }

    private String getSex() {
        return tvSex.getText().toString();
    }

    private String getHeight() {
        return tvHeight.getText().toString();
    }

    private String getWeight() {
        return tvWeight.getText().toString();
    }

    private String getStatus() {
        return tvStatus.getText().toString();
    }
}
