package com.wt.edrink.activity;

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
import cn.droidlover.xdroid.kit.ExitApp;
import cn.droidlover.xdroid.kit.RegexUtils;
import cn.droidlover.xdroid.kit.ToastUtils;

/**
 * Created by 美时美课 on 2017/5/24.
 */

public class SignupActivity extends BaseActivity {
    private String TAG = "SignupActivity";

    @BindView(R.id.et_signup_username)
    EditText etUserName;
    @BindView(R.id.et_signup_password)
    EditText etPassword;

    @Override
    public void initData(Bundle savedInstanceState) {
        setShowBack(true);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_signup;
    }


    @OnClick({R.id.btn_signup})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_signup:
                if (!checkUserOrPassEmpty()) {
                    return;
                }
                if (!RegexUtils.isMobileNumber(getUserName())) {
                    ToastUtils.showShort(context, "手机号格式错误");
                    return;
                }
                httpPost(getUserName(), getPassWord());
                /*Intents.getIntents().Intent(context, MainActivity.class, null);
                ExitApp.getInstance().exit();*/
                break;
        }
    }

    /**
     * http请求
     */
    private void httpPost(String username, String password) {
        Request<CommonBean> request = new JavaBeanRequest<CommonBean>(Constants.URL_SIGN_UP, CommonBean.class);
        request.add(Constants.USERNAME, username);
        request.add(Constants.PASSWORD, password);
        HttpManage.httpRequest(0, request, onResponseListener);
    }

    private OnResponseListener onResponseListener = new OnResponseListener() {
        @Override
        public void onStart(int what) {

        }

        @Override
        public void onSucceed(int what, Response response) {
            CommonBean data = (CommonBean) response.get();
            Log.e(TAG, "注册error_code:" + data.getError_code() + "----reason:" + data.getReason() + "----result:" + data.getResult());
            if (data.getError_code() == 10002) {
                ToastUtils.showShort(context, data.getReason());
                UserPrefs userPrefs = new UserPrefs(context);
                userPrefs.setAuthKey(data.getResult());

                Intents.getIntents().Intent(context, MainActivity.class, null);
                ExitApp.getInstance().exit();
            } else {
                ToastUtils.showShort(context, data.getReason());
            }
        }

        @Override
        public void onFailed(int what, Response response) {
            ToastUtils.showLong(context, "网络错误");
        }

        @Override
        public void onFinish(int what) {

        }
    };


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

    private String getUserName() {
        return etUserName.getText().toString().trim();
    }

    private String getPassWord() {
        String password = etPassword.getText().toString().trim();
        return Codec.MD5.getMessageDigest(password);
    }

}
