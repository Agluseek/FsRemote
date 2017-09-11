package com.agluseek.farsoon.farsoononline.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.agluseek.farsoon.farsoononline.R;
import com.agluseek.farsoon.farsoononline.model.Nylon_device;

import java.util.List;

/**
 * Created by Wu'Ang on 2017/4/18.
 */

public class NylonAdapter extends ArrayAdapter<Nylon_device> {
    private Context context;
    private int subLayout;

    public NylonAdapter(@NonNull Context context, @LayoutRes int subLayout, @NonNull List objects) {
        super(context, subLayout, objects);
        this.context = context;
        this.subLayout = subLayout;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Nylon_device item = getItem(position);
        class ViewHolder {
            TextView mNylon_device_name;
            ImageView iv_tag;
        }

        View view;
        ViewHolder vh;
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(subLayout, null);
            vh = new ViewHolder();
            vh.mNylon_device_name = (TextView) view.findViewById(R.id.nylon_device_id);
            vh.iv_tag = (ImageView) view.findViewById(R.id.nylon_device_iv);
            view.setTag(vh);
        } else {
            view = convertView;
            vh = (ViewHolder) view.getTag();
        }
        vh.mNylon_device_name.getPaint().setFakeBoldText(true);
        vh.mNylon_device_name.setTextSize(16);
        vh.mNylon_device_name.setText(item.getNylon_device_name());
        vh.iv_tag.setImageResource(R.mipmap.more);
        return view;
    }

}
