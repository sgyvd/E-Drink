package com.wt.edrink.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


/**
 * Intent跳转封装
 * Created by win7 on 2017/3/15.
 */

public class Intents {
    private static Intents intents;

    public static Intents getIntents() {
        if (intents == null)
            intents = new Intents();
        return intents;
    }

    // context this, cs跳转对象 bundle 传递参数
    public void Intent(Activity activity, Class<?> cs, Bundle bundle) {
        Intent i = new Intent(activity, cs);
        if (bundle != null) {
            i.putExtras(bundle);
        }
        activity.startActivity(i);
    }
}
