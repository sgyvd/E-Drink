package com.wt.edrink.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.wt.edrink.Constants;
import com.wt.edrink.R;
import com.wt.edrink.adapter.RankAdapter;
import com.wt.edrink.base.BaseActivity;
import com.wt.edrink.bean.CommonBean;
import com.wt.edrink.bean.RankBean;
import com.wt.edrink.bean.RankListBean;
import com.wt.edrink.http.HttpManage;
import com.wt.edrink.http.JavaBeanRequest;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import butterknife.BindView;
import cn.droidlover.xdroid.kit.ToastUtils;
import cn.droidlover.xrecyclerview.RecyclerItemCallback;
import cn.droidlover.xrecyclerview.XRecyclerContentLayout;
import cn.droidlover.xrecyclerview.XRecyclerView;

/**
 * Created by 美时美课 on 2017/5/24.
 */

public class RankActivity extends BaseActivity {
    private static String TAG = "RankActivity";

    @BindView(R.id.xrv_rank)
    XRecyclerContentLayout rvRankLayout;

    private XRecyclerView rvRank;
    private RankAdapter adapter;

    @Override
    public void initData(Bundle savedInstanceState) {
        setShowBack(true);
        setToolbarTitle("排行榜");

        initRecycler();

        httpRankisOpen();

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_rank;
    }

    private void initRecycler() {
        adapter = new RankAdapter(context);
        adapter.setRecItemClick(recyclerItemCallback);
        rvRank = rvRankLayout.getRecyclerView();
        rvRank.verticalLayoutManager(context);
        rvRank.setAdapter(adapter);
        rvRank.setOnRefreshAndLoadMoreListener(new XRecyclerView.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                httpRankisOpen();
            }

            @Override
            public void onLoadMore(int page) {

            }
        });
    }

    private RecyclerItemCallback<RankBean, RecyclerView.ViewHolder> recyclerItemCallback =
            new RecyclerItemCallback<RankBean, RecyclerView.ViewHolder>() {
                @Override
                public void onItemClick(int position, RankBean model, int tag, RecyclerView.ViewHolder holder) {
                    super.onItemClick(position, model, tag, holder);
                    switch (tag) {
                        case R.id.tv_rank_like:
                            ToastUtils.showShort(context, "点赞");
                            break;
                    }
                }

            };

    /**
     * 排行榜数据请求
     */
    private void httpPost() {
        Request<RankListBean> request = new JavaBeanRequest<RankListBean>(Constants.URL_RANK, RankListBean.class);
        request.add(Constants.AUTH_KEY, getAuthKey());
        HttpManage.httpRequest(0, request, onResponseListener);
    }

    /**
     * 判断排行榜是否打开
     */
    private void httpRankisOpen() {
        Request<CommonBean> request = new JavaBeanRequest<CommonBean>(Constants.URL_RANK_STATUS, CommonBean.class);
        request.add(Constants.AUTH_KEY, getAuthKey());
        request.add(Constants.DEVICE_ID, getDeviceId());
        HttpManage.httpRequest(1, request, onResponseListener);
    }

    private OnResponseListener onResponseListener = new OnResponseListener() {
        @Override
        public void onStart(int what) {

        }

        @Override
        public void onSucceed(int what, Response response) {
            switch (what) {
                case 0:
                    RankListBean data = (RankListBean) response.get();
                    Log.e(TAG, "注册error_code:" + data.getError_code() + "----reason:" + data.getReason() + "----result:" + data.getResult());
                    if (data.getError_code() == 10026) {
                        if (data == null) {
                            ToastUtils.showShort(context, "当前无排行榜数据");
                        } else {
//                            ToastUtils.showShort(context, data.getReason());
                            adapter.setData(data.getResult());
                        }
                    } else {
                        ToastUtils.showShort(context, data.getReason());
                    }
                    rvRankLayout.refreshState(false);
                    break;
                case 1:
                    CommonBean data1 = (CommonBean) response.get();
                    Log.e(TAG, "排行榜状态error_code:" + data1.getError_code() + "----reason:" + data1.getReason() + "----result:" + data1.getResult());
                    if (data1.getError_code() == 10036) { //关闭状态
                        ToastUtils.showShort(context, "请先公开您的数据");
                    } else if (data1.getError_code() == 10035) { //打开状态
                        httpPost();
                    } else {
                        ToastUtils.showShort(context, data1.getReason());
                    }
                    break;
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
