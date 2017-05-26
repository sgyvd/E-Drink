package com.wt.edrink.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;

import com.google.gson.Gson;
import com.wt.edrink.Constants;
import com.wt.edrink.R;
import com.wt.edrink.base.BaseActivity;
import com.wt.edrink.bean.CommonBean;
import com.wt.edrink.bean.ScanBean;
import com.wt.edrink.http.HttpManage;
import com.wt.edrink.http.JavaBeanRequest;
import com.wt.edrink.utils.UserPrefs;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import butterknife.BindView;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.droidlover.xdroid.kit.ToastUtils;

/**
 * Created by 美时美课 on 2017/5/24.
 */

public class ScanActivity extends BaseActivity {
    private static String TAG = "ScanActivity";

    @BindView(R.id.zxingview_scan)
    QRCodeView qrCodeView;
    @BindView(R.id.switch_scan)
    SwitchCompat switchLight;

    private Toolbar toolbar;
    private UserPrefs userPrefs;
    private ProgressDialog progressDialog;

    @Override
    public void initData(Bundle savedInstanceState) {
        toolbar = getToolbar();
        toolbar.setBackgroundColor(0x00);
        toolbar.setNavigationIcon(R.drawable.ic_back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        userPrefs = new UserPrefs(context);
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("正在绑定...");

        initScan();
    }

    @Override
    public void setListener() {
        super.setListener();
        switchLight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    qrCodeView.openFlashlight();
                } else {
                    qrCodeView.closeFlashlight();
                }
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_scan;
    }

    /**
     * 初始化扫码器
     */
    private void initScan() {
        qrCodeView.setDelegate(delegate);
        qrCodeView.startCamera();//打开摄像头
        qrCodeView.showScanRect();//显示扫描框
        qrCodeView.startSpot();//开始识别
    }

    private QRCodeView.Delegate delegate = new QRCodeView.Delegate() {
        @Override
        public void onScanQRCodeSuccess(String result) {
            vibrator();
            Gson gson = new Gson();
            ScanBean data = gson.fromJson(result, ScanBean.class);
            if (data.getDevice_pwd() == null || data.getDevice_id() == null) {
                ToastUtils.showShort(context, "无法识别二维码");
                qrCodeView.startSpot();//开始识别
            } else {
                Log.e(TAG, data.getDevice_id() + "-----" + data.getDevice_pwd());
                httpPost(data.getDevice_id(), data.getDevice_pwd());
            }

        }

        @Override
        public void onScanQRCodeOpenCameraError() {
            ToastUtils.showShort(context, "无法打开相机");
            context.finish();
        }
    };

    //绑定设备
    private void httpPost(final String deviceId, String devicePwd) {
        Request<CommonBean> request = new JavaBeanRequest<CommonBean>(Constants.URL_BING, CommonBean.class);
        request.add(Constants.AUTH_KEY, userPrefs.getAuthKey());
        request.add(Constants.DEVICE_ID, deviceId);
        request.add(Constants.DEVICE_PWD, devicePwd);
        HttpManage.httpRequest(0, request, new OnResponseListener<CommonBean>() {
            @Override
            public void onStart(int what) {
                progressDialog.show();
            }

            @Override
            public void onSucceed(int what, Response<CommonBean> response) {
                CommonBean data = response.get();
                if (data.getError_code() == 10009) {
                    ToastUtils.showShort(context, "水杯绑定成功");
                    userPrefs.setDeviceId(deviceId);
                    context.finish();
                } else {
                    ToastUtils.showShort(context, data.getReason());
                    qrCodeView.startSpot();//开始识别
                }
            }

            @Override
            public void onFailed(int what, Response<CommonBean> response) {
                ToastUtils.showShort(context, "网络请求失败");
            }

            @Override
            public void onFinish(int what) {
                progressDialog.dismiss();
            }
        });
    }

    private void vibrator() {
        //获取系统震动服务
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

    @Override
    protected void onStart() {
        super.onStart();
        qrCodeView.startCamera();//打开摄像头
        qrCodeView.showScanRect();//显示扫描框
        qrCodeView.startSpot();//开始识别
    }

    @Override
    protected void onStop() {
        qrCodeView.stopSpotAndHiddenRect();
        qrCodeView.stopCamera();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        qrCodeView.onDestroy();
        super.onDestroy();
    }

}
