package com.agluseek.farsoon.farsoononline.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.agluseek.farsoon.farsoononline.R;
import com.agluseek.farsoon.farsoononline.model.Metal_device;

import java.util.List;

/**
 * Created by Wu'Ang on 2017/4/18.
 */

public class MetalAdapter extends ArrayAdapter<Metal_device> {
    private int subLayoutId;
    private Context context;

    public MetalAdapter(@NonNull Context context, @LayoutRes int subLayoutId, @NonNull List<Metal_device> objects) {
        super(context, subLayoutId, objects);
        this.subLayoutId = subLayoutId;
        this.context = context;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Metal_device item = getItem(position);

        class ViewHolder {
            TextView mMetal_device_name;
            ImageView iv_tag;
        }

        View view;
        ViewHolder vh;

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(subLayoutId, null);
            vh = new ViewHolder();
            vh.mMetal_device_name = (TextView) view.findViewById(R.id.metal_device_id);
            vh.iv_tag = (ImageView) view.findViewById(R.id.metal_device_iv);
            view.setTag(vh);
        } else {
            view = convertView;
            vh = (ViewHolder) view.getTag();
        }
        vh.mMetal_device_name.getPaint().setFakeBoldText(true);
        vh.mMetal_device_name.setTextSize(16);
        vh.mMetal_device_name.setText(item.getMetal_device_name());
        vh.iv_tag.setImageResource(R.mipmap.more);
        return view;
    }
}
