package com.wt.edrink.base;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.wt.edrink.R;

import cn.droidlover.xdroid.base.XActivity;
import cn.droidlover.xdroid.kit.ExitApp;
import cn.droidlover.xdroid.kit.NetWorkUtils;
import cn.droidlover.xdroid.kit.ToastUtils;

/**
 * Created by 美时美课 on 2017/5/23.
 */

public abstract class BaseActivity extends XActivity {

    private Toolbar mToolbar;
    private TextView mToolbarTitle;
    private TextView mToolbarSubTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ExitApp.getInstance().addActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!NetWorkUtils.isNetworkConnected(context)){
            ToastUtils.showShort(context,"网络异常，请检查网络状态");
        }
    }

    @Override
    public void setListener() {

    }

    public void setToolbarTitle(String title) {
        getToolbarTitle().setText(title);
    }

    public void setToolbarSubTitle(String subTitle) {
        getToolbarSubTitle().setText(subTitle);
    }

    public void setShowBack(boolean isShow) {
        if (isShow) {
            getToolbar().setNavigationIcon(R.drawable.ic_back_black);
            getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
    }

    public Toolbar getToolbar() {
        if (mToolbar == null) {
            mToolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(mToolbar);
            //设置默认的标题不显示
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        return mToolbar;
    }

    public TextView getToolbarTitle() {
        return (TextView) findViewById(R.id.toolbar_title);
    }

    public TextView getToolbarSubTitle() {
        return (TextView) findViewById(R.id.toolbar_subtitle);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ExitApp.getInstance().delActivity(this);
    }
}
