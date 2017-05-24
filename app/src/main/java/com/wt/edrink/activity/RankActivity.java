package com.wt.edrink.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.wt.edrink.R;
import com.wt.edrink.adapter.RankAdapter;
import com.wt.edrink.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.droidlover.xdroid.kit.ToastUtils;
import cn.droidlover.xrecyclerview.RecyclerItemCallback;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import cn.droidlover.xrecyclerview.XRecyclerView;

/**
 * Created by 美时美课 on 2017/5/24.
 */

public class RankActivity extends BaseActivity {

    @BindView(R.id.xrv_rank)
    XRecyclerContentLayout rvRankLayout;

    private XRecyclerView rvRank;
    private RankAdapter adapter;
    private List<String> dataList = new ArrayList<>();

    @Override
    public void initData(Bundle savedInstanceState) {
        setShowBack(true);
        setToolbarTitle("排行榜");

        initDataList();

        initRecycler();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_rank;
    }

    private void initDataList() {
        dataList.add("15706844661");
        dataList.add("15706844662");
        dataList.add("15706844663");
        dataList.add("15706844664");
        dataList.add("15706844665");
        dataList.add("15706844666");
        dataList.add("15706844667");
        dataList.add("15706844668");
        dataList.add("15706844669");
        dataList.add("15706844670");
    }

    private void initRecycler() {
        adapter = new RankAdapter(context);
        adapter.setData(dataList);
        adapter.setRecItemClick(recyclerItemCallback);
        rvRank = rvRankLayout.getRecyclerView();
        rvRank.verticalLayoutManager(context);
        rvRank.setAdapter(adapter);
        rvRank.setOnRefreshAndLoadMoreListener(new XRecyclerView.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                rvRankLayout.refreshState(false);
            }

            @Override
            public void onLoadMore(int page) {

            }
        });
    }

    private RecyclerItemCallback<String, RecyclerView.ViewHolder> recyclerItemCallback =
            new RecyclerItemCallback<String, RecyclerView.ViewHolder>() {
                @Override
                public void onItemClick(int position, String model, int tag, RecyclerView.ViewHolder holder) {
                    super.onItemClick(position, model, tag, holder);
                    switch (tag) {
                        case R.id.tv_rank_like:
                            ToastUtils.showShort(context, "点赞");
                            break;
                    }
                }

            };
}
