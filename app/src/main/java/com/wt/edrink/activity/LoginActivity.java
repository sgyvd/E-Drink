package com.wt.edrink.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.wt.edrink.R;
import com.wt.edrink.base.BaseActivity;
import com.wt.edrink.utils.Intents;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 美时美课 on 2017/5/24.
 */

public class LoginActivity extends BaseActivity {

    @BindView(R.id.et_login_username)
    EditText etUserName;
    @BindView(R.id.et_login_password)
    EditText etPassWord;

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @OnClick({R.id.btn_login, R.id.tv_login_signup})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                Intents.getIntents().Intent(this, MainActivity.class, null);
                this.finish();
                break;
            case R.id.tv_login_signup:
                Intents.getIntents().Intent(this, SignupActivity.class, null);
                break;
        }
    }


    private String getUserName() {
        return etUserName.getText().toString().trim();
    }

    private String getPassWord() {
        return etPassWord.getText().toString().trim();
    }
}
