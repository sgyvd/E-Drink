package com.wt.edrink.activity;

import android.os.Bundle;
import android.view.View;

import com.wt.edrink.R;
import com.wt.edrink.base.BaseActivity;
import com.wt.edrink.utils.Intents;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }


    @OnClick({R.id.rl_main_rank, R.id.rl_main_me})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_main_rank:
                Intents.getIntents().Intent(this, RankActivity.class, null);
                break;
            case R.id.rl_main_me:
                Intents.getIntents().Intent(this, MineActivity.class, null);
                break;
        }
    }
}
