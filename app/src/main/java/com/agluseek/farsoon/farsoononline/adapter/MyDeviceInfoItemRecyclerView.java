package com.agluseek.farsoon.farsoononline.adapter;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.agluseek.farsoon.farsoononline.R;
import com.agluseek.farsoon.farsoononline.model.DeviceStatus;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Farsoon on 2017/8/7.
 * 实时信息adapter
 */

public class MyDeviceInfoItemRecyclerView extends RecyclerView.Adapter<MyDeviceInfoItemRecyclerView.ViewHolder> {
    private List<DeviceStatus> mValues;
    private OnListInteractionListener mListener;

    public MyDeviceInfoItemRecyclerView(List<DeviceStatus> mValues, OnListInteractionListener mListener) {
        this.mValues = mValues;
        this.mListener = mListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.devicestatus_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {
        //      System.out.println("当前状态------>>"+mValues.get(position).getCurState());
        int state = Integer.parseInt(mValues.get(position).getCurState());
        switch (state) {
            case 270:
                viewHolder.CurState.setText("读取PLC参数");
                break;
            case 257:
                viewHolder.CurState.setText("对工作腔充保护气体");
                break;
            case 309:
                viewHolder.CurState.setText("按下SYSTEM ON按钮");
                break;
            case 259:
                viewHolder.CurState.setText("等待温度");
                break;
            case 262:
                viewHolder.CurState.setText("铺粉");
                break;
            case 261:
                viewHolder.CurState.setText("烧结中");
                break;
            case 28101:
                viewHolder.CurState.setText("等待切片");
                break;
            case 2416:
                viewHolder.CurState.setText("成功进入建造状态");
                break;
            case 443:
                viewHolder.CurState.setText("请稍等");
                break;
            case 1:
                viewHolder.CurState.setText("冷却阶段");
                break;
            case 2:
                viewHolder.CurState.setText("建造阶段");
                break;
            case 3:
                viewHolder.CurState.setText(null);
                break;
            case 4:
                viewHolder.CurState.setText("请等待");
                break;
            case 0:
                viewHolder.CurState.setText("预热阶段");
                break;
            case -1:
                viewHolder.CurState.setText("建造准备阶段");
                break;
        }

        if (mValues.get(position).getPackageName().contains("")) {
            viewHolder.PackageName.setText(null);
        } else {
            viewHolder.PackageName.setText(mValues.get(position).getPackageName());
        }
        if (mValues.get(position).getMaterialName().contains("")) {
            viewHolder.MaterialName.setText(null);
        } else {
            viewHolder.MaterialName.setText(mValues.get(position).getMaterialName());
        }

        viewHolder.PackageHeight.setText(mValues.get(position).getPackageHeight().substring(0,6) + " (mm)");
        viewHolder.CurHeight.setText(mValues.get(position).getCurHeight().substring(0,6) + " (mm)");
        viewHolder.Temp.setText(mValues.get(position).getTemp() + " ℃");
        viewHolder.RemainPowder.setText(mValues.get(position).getRemainPowder() + " (mm)");
        viewHolder.RemainTime.setText((Long.parseLong(mValues.get(position).getRemainTime()) % (60 * 60 * 24)) / 3600 + " h " + (Long.parseLong(mValues.get(position).getRemainTime()) % (60 * 60)) / 60 + " m");
        viewHolder.Percentage.setText(mValues.get(position).getO2Percentage() + " %");

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    int p = viewHolder.getLayoutPosition();
                    mListener.onClickListInteraction(v, p);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public interface OnListInteractionListener {
        void onClickListInteraction(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // 当前状态
        public TextView CurState;
        // 包名
        public TextView PackageName;
        // 材料名
        public TextView MaterialName;
        // 建造高度
        public TextView PackageHeight;
        // 当前高度
        public TextView CurHeight;
        // 温度
        public TextView Temp;
        //剩余粉末
        public TextView RemainPowder;
        //剩余时间
        public TextView RemainTime;
        //氧含量百分比
        public TextView Percentage;

        public ViewHolder(View itemView) {
            super(itemView);
            CurState = (TextView) itemView.findViewById(R.id.CurState_tv);
            PackageName = (TextView) itemView.findViewById(R.id.PackageName_tv);
            MaterialName = (TextView) itemView.findViewById(R.id.MaterialName_tv);
            PackageHeight = (TextView) itemView.findViewById(R.id.PackageHeight_tv);
            CurHeight = (TextView) itemView.findViewById(R.id.CurHeight_tv);
            Temp = (TextView) itemView.findViewById(R.id.Temp_tv);
            RemainPowder = (TextView) itemView.findViewById(R.id.RemainPowder_tv);
            RemainTime = (TextView) itemView.findViewById(R.id.RemainTime_tv);
            Percentage = (TextView) itemView.findViewById(R.id.Percentage_tv);

        }
    }
}
