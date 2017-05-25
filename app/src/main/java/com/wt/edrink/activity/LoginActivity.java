package com.wt.edrink.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.wt.edrink.Constants;
import com.wt.edrink.R;
import com.wt.edrink.base.BaseActivity;
import com.wt.edrink.bean.CommonBean;
import com.wt.edrink.http.HttpManage;
import com.wt.edrink.http.JavaBeanRequest;
import com.wt.edrink.utils.Intents;
import com.wt.edrink.utils.UserPrefs;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import butterknife.BindView;
import butterknife.OnClick;
import cn.droidlover.xdroid.kit.Codec;
import cn.droidlover.xdroid.kit.RegexUtils;
import cn.droidlover.xdroid.kit.ToastUtils;

/**
 * Created by 美时美课 on 2017/5/24.
 */

public class LoginActivity extends BaseActivity {
    private static String TAG = "LoginActivity";

    @BindView(R.id.et_login_username)
    EditText etUserName;
    @BindView(R.id.et_login_password)
    EditText etPassWord;

    private ProgressDialog progressDialog;

    @Override
    public void initData(Bundle savedInstanceState) {

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("正在登录...");

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }


    @OnClick({R.id.btn_login, R.id.tv_login_signup})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                if (!checkUserOrPassEmpty()) {
                    return;
                }
                if (!RegexUtils.isMobileNumber(getUserName())) {
                    ToastUtils.showShort(context, "手机号格式错误");
                    return;
                }
                progressDialog.show();
                httpPost(getUserName(), getPassWord());
//                Intents.getIntents().Intent(this, MainActivity.class, null);
//                this.finish();
                break;
            case R.id.tv_login_signup:
                Intents.getIntents().Intent(this, SignupActivity.class, null);
                break;
        }
    }

    /**
     * 判断用户民、密码是否为空
     *
     * @return
     */
    private boolean checkUserOrPassEmpty() {
        if (TextUtils.isEmpty(getUserName())) {
            ToastUtils.showShort(context, "请输入手机号");
            return false;
        }
        if (TextUtils.isEmpty(getPassWord())) {
            ToastUtils.showShort(context, "请输入密码");
            return false;
        }
        return true;
    }

    private void httpPost(final String username, String password) {
        Log.e(TAG, "-----密码-" + password);
        Request<CommonBean> request = new JavaBeanRequest<CommonBean>(Constants.URL_LOGIN, CommonBean.class);
        request.add(Constants.USERNAME, username);
        request.add(Constants.PASSWORD, password);
        HttpManage.httpRequest(0, request, new OnResponseListener<CommonBean>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<CommonBean> response) {
                CommonBean data = response.get();
                Log.e(TAG, "注册error_code:" + data.getError_code() + "----reason:" + data.getReason() + "----result:" + data.getResult());
                if (data.getError_code() == 10004) {
                    ToastUtils.showShort(context, data.getReason());
                    UserPrefs userPrefs = new UserPrefs(context);
                    userPrefs.setAuthKeyAndUserName(data.getResult(), username);

                    Intents.getIntents().Intent(context, MainActivity.class, null);
                    context.finish();
                } else {
                    ToastUtils.showShort(context, data.getReason());
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailed(int what, Response<CommonBean> response) {
                ToastUtils.showLong(context, "网络错误");
            }

            @Override
            public void onFinish(int what) {

            }
        });
    }


    private String getUserName() {
        return etUserName.getText().toString().trim();
    }

    private String getPassWord() {
        String password = etPassWord.getText().toString().trim();
        return Codec.MD5.getMessageDigest(password);
    }
}
