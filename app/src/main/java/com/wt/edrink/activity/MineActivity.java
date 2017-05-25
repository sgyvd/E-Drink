package com.wt.edrink.activity;

import android.os.Bundle;
import android.view.View;

import com.wt.edrink.R;
import com.wt.edrink.base.BaseActivity;
import com.wt.edrink.utils.Intents;
import com.wt.edrink.utils.UserPrefs;

import butterknife.OnClick;
import cn.droidlover.xdroid.kit.ExitApp;

/**
 * Created by 美时美课 on 2017/5/24.
 */

public class MineActivity extends BaseActivity {

    UserPrefs userPrefs = new UserPrefs(context);

    @Override
    public void initData(Bundle savedInstanceState) {
        setToolbarTitle("我的");
        setShowBack(true);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_mine;
    }


    @OnClick({R.id.tv_mine_quit})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_mine_quit:
                userPrefs.clearAuthKey();
                userPrefs.clearCupnum();

                Intents.getIntents().Intent(context,LoginActivity.class,null);
                ExitApp.getInstance().exit();
                break;
        }
    }
}
