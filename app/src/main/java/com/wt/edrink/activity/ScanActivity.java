package com.wt.edrink.activity;

import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;

import com.wt.edrink.R;
import com.wt.edrink.base.BaseActivity;

import butterknife.BindView;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.droidlover.xdroid.kit.ToastUtils;

/**
 * Created by 美时美课 on 2017/5/24.
 */

public class ScanActivity extends BaseActivity {

    @BindView(R.id.zxingview_scan)
    QRCodeView qrCodeView;
    @BindView(R.id.switch_scan)
    SwitchCompat switchLight;

    private Toolbar toolbar;

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
            ToastUtils.showShort(context, result);
            qrCodeView.startSpot();//开始识别
        }

        @Override
        public void onScanQRCodeOpenCameraError() {
            ToastUtils.showShort(context, "无法打开相机");
            context.finish();
        }
    };

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
