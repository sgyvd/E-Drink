package com.wt.edrink.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.wt.edrink.R;
import com.wt.edrink.base.BaseActivity;
import com.wt.edrink.utils.Intents;
import com.wt.edrink.utils.UserPrefs;

import java.lang.ref.WeakReference;

/**
 * Created by 美时美课 on 2017/5/24.
 */

public class SplashActivity extends BaseActivity{
    private final static int JUMP_TO_NEXT = 1;

    private MyHandler handler = new MyHandler(SplashActivity.this);

    @Override
    public void initData(Bundle savedInstanceState) {
        handler.sendEmptyMessageDelayed(JUMP_TO_NEXT, 2000);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    private class MyHandler extends Handler {
        private WeakReference<BaseActivity> weakReference;

        MyHandler(BaseActivity activity) {
            this.weakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case JUMP_TO_NEXT:
                    if (weakReference.get() == null) {
                        return;
                    }
                    BaseActivity activity = weakReference.get();
                    UserPrefs userPrefs = new UserPrefs(activity);
                    if (!TextUtils.isEmpty(userPrefs.getUserName())) {
                        Intents.getIntents().Intent(activity, MainActivity.class, null);
                    } else {
                        Intents.getIntents().Intent(activity, LoginActivity.class, null);
                    }
                    activity.finish();
            }
            super.handleMessage(msg);
        }
    }
}
