package com.agluseek.farsoon.farsoononline.adapter;

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

        viewHolder.CurState.setText(mValues.get(position).getCurState());
        viewHolder.PackageName.setText(mValues.get(position).getPackageName());
        viewHolder.MaterialName.setText(mValues.get(position).getMaterialName());
        viewHolder.PackageHeight.setText(mValues.get(position).getPackageHeight()+" (mm)");
        viewHolder.CurHeight.setText(mValues.get(position).getCurHeight()+" (mm)");
        viewHolder.Temp.setText(mValues.get(position).getTemp()+" ℃");
        viewHolder.RemainPowder.setText(mValues.get(position).getRemainPowder()+" (mm)");
        viewHolder.RemainTime.setText(mValues.get(position).getRemainTime()+" (h:m)");
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
        public TextView CurState;
        public TextView PackageName;
        public TextView MaterialName;
        public TextView PackageHeight;
        public TextView CurHeight;
        public TextView Temp;
        public TextView RemainPowder;
        public TextView RemainTime;

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

        }

    }

}
