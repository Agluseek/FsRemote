package com.agluseek.farsoon.farsoononline.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.agluseek.farsoon.farsoononline.R;
import com.agluseek.farsoon.farsoononline.model.PushInfo;

import java.util.List;

/**
 * Created by Farsoon on 2018/1/9.
 * 报警及消息推送在设备实时信息中adapter
 */

public class Alert_info_RecyclerView extends RecyclerView.Adapter<Alert_info_RecyclerView.ViewHolder> {
    private List<PushInfo> infos;
    private MyDeviceInfoItemRecyclerView.OnListInteractionListener mListener;

    public Alert_info_RecyclerView(List<PushInfo> infos, MyDeviceInfoItemRecyclerView.OnListInteractionListener mListener) {
        this.infos = infos;
        this.mListener = mListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.alert_info_recyclerview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.alert_info_tv.setText(infos.get(position).getContent());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    int p = holder.getLayoutPosition();
                    mListener.onClickListInteraction(v, p);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return infos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //       报警消息或普通消息
        public TextView alert_info_tv;

        public ViewHolder(View itemView) {
            super(itemView);
            alert_info_tv = (TextView) itemView.findViewById(R.id.alert_info_tv);
        }
    }
}
