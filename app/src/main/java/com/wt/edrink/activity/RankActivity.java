package com.wt.edrink.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.wt.edrink.Constants;
import com.wt.edrink.R;
import com.wt.edrink.adapter.RankAdapter;
import com.wt.edrink.base.BaseActivity;
import com.wt.edrink.bean.RankBean;
import com.wt.edrink.bean.RankListBean;
import com.wt.edrink.http.HttpManage;
import com.wt.edrink.http.JavaBeanRequest;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

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
    private List<RankListBean> dataList = new ArrayList<>();

    @Override
    public void initData(Bundle savedInstanceState) {
        setShowBack(true);
        setToolbarTitle("排行榜");

        initRecycler();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_rank;
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

    private RecyclerItemCallback<RankListBean, RecyclerView.ViewHolder> recyclerItemCallback =
            new RecyclerItemCallback<RankListBean, RecyclerView.ViewHolder>() {
                @Override
                public void onItemClick(int position, RankListBean model, int tag, RecyclerView.ViewHolder holder) {
                    super.onItemClick(position, model, tag, holder);
                    switch (tag) {
                        case R.id.tv_rank_like:
                            ToastUtils.showShort(context, "点赞");
                            break;
                    }
                }

            };

    private void httpPost(){
        Request<RankBean> request = new JavaBeanRequest<RankBean>(Constants.URL_RANK, RankBean.class);
        request.add(Constants.AUTHKEY,getAuthKey());
        HttpManage.httpRequest(0, request, onResponseListener);
    }

    private OnResponseListener onResponseListener = new OnResponseListener() {
        @Override
        public void onStart(int what) {

        }

        @Override
        public void onSucceed(int what, Response response) {
            RankBean data = (RankBean) response.get();
            if (data.getError_code() == 10025){

            }
        }

        @Override
        public void onFailed(int what, Response response) {
            ToastUtils.showLong(context, "网络请求失败");
        }

        @Override
        public void onFinish(int what) {

        }
    };
}
