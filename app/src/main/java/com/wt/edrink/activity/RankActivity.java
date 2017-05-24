package com.wt.edrink.activity;

import android.os.Bundle;

import com.wt.edrink.R;
import com.wt.edrink.base.BaseActivity;

import butterknife.BindView;
import cn.droidlover.xrecyclerview.XRecyclerView;

/**
 * Created by 美时美课 on 2017/5/24.
 */

public class RankActivity extends BaseActivity {

    @BindView(R.id.xrv_rank)
    XRecyclerView rvRank;

    @Override
    public void initData(Bundle savedInstanceState) {
        setShowBack(true);
        setToolbarTitle("排行榜");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_rank;
    }
}
