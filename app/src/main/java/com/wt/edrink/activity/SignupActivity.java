package com.wt.edrink.activity;

import android.os.Bundle;

import com.wt.edrink.R;
import com.wt.edrink.base.BaseActivity;

/**
 * Created by 美时美课 on 2017/5/24.
 */

public class SignupActivity extends BaseActivity {
    @Override
    public void initData(Bundle savedInstanceState) {
        setShowBack(true);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_signup;
    }


}
