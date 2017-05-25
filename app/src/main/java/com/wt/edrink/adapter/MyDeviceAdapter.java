package com.wt.edrink.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wt.edrink.R;
import com.wt.edrink.bean.DeviceBean;
import com.wt.edrink.utils.UserPrefs;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.droidlover.xdroid.base.SimpleRecAdapter;

/**
 * Created by 美时美课 on 2017/5/25.
 */

public class MyDeviceAdapter extends SimpleRecAdapter<DeviceBean, RecyclerView.ViewHolder> {
    private Context context;

    public MyDeviceAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder newViewHolder(View itemView) {
        return new mViewHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_mydevice;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DeviceBean item = data.get(position);

        UserPrefs userPrefs = new UserPrefs(context);
        if (userPrefs.getDeviceId() != null && userPrefs.getDeviceId().equals(item.getDevice_id())) {
            ((mViewHolder) holder).tvDeviceId.setTextColor(0xFFF46767);
        }
        ((mViewHolder) holder).rlDevice.setOnClickListener(onClickListener(position, item, holder));
        ((mViewHolder) holder).tvDeviceId.setText(item.getDevice_id());
        ((mViewHolder) holder).btnUnBind.setOnClickListener(onClickListener(position, item, holder));
    }

    private View.OnClickListener onClickListener(final int position, final DeviceBean model, RecyclerView.ViewHolder holder) {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRecItemClick().onItemClick(position, model, v.getId(), null);
            }
        };
        return onClickListener;
    }

    public class mViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.rl_mydevice)
        RelativeLayout rlDevice;
        @BindView(R.id.tv_mydevice_id)
        TextView tvDeviceId;
        @BindView(R.id.btn_mydevice_unbind)
        Button btnUnBind;

        public mViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
