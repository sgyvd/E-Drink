package cn.droidlover.xdroid.base;

import android.os.Bundle;

/**
 * Created by wangtao on 2016/12/1.
 */

public interface UiCallback {
    void initData(Bundle savedInstanceState);

    void setListener();

    int getLayoutId();

    boolean useEventBus();
}
