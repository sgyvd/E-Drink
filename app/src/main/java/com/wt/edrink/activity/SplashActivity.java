package com.wt.edrink.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.TextView;

import com.wt.edrink.R;
import com.wt.edrink.base.BaseActivity;
import com.wt.edrink.utils.Intents;
import com.wt.edrink.utils.UserPrefs;

import java.lang.ref.WeakReference;

import butterknife.BindView;

/**
 * Created by 美时美课 on 2017/5/24.
 */

public class SplashActivity extends BaseActivity {
    private final static int JUMP_TO_NEXT = 1;

    @BindView(R.id.tv_splash)
    TextView tvSplash;

    private MyHandler handler = new MyHandler(SplashActivity.this);

    @Override
    public void initData(Bundle savedInstanceState) {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/a.ttf");
        tvSplash.setTypeface(typeface);

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
                    if (!TextUtils.isEmpty(userPrefs.getAuthKey())) {
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
