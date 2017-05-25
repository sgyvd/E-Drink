package com.wt.edrink.activity;

import android.os.Bundle;
import android.view.View;

import com.wt.edrink.R;
import com.wt.edrink.base.BaseActivity;

import butterknife.OnClick;

/**
 * Created by admin on 2017/5/25.
 */

public class MineInfoActivity extends BaseActivity {
    @Override
    public void initData(Bundle savedInstanceState) {
        setToolbarTitle("我的信息");
        setShowBack(true);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_mine_info;
    }

    @OnClick({R.id.ll_info_birthday, R.id.ll_info_sex, R.id.ll_info_height, R.id.ll_info_weight, R.id.btn_info_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_info_birthday:
                break;
            case R.id.ll_info_sex:
                break;
            case R.id.ll_info_height:
                break;
            case R.id.ll_info_weight:
                break;
            case R.id.btn_info_save:
                break;
        }
    }
}
