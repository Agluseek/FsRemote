package com.agluseek.farsoon.farsoononline.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.agluseek.farsoon.farsoononline.R;
import com.agluseek.farsoon.farsoononline.model.Metal_meterial;

import java.util.List;

/**
 * Created by Farsoon on 2017/4/19.
 */

public class MetalmeterialAdapter extends ArrayAdapter<Metal_meterial> {
    private Context context;
    private int subLayout;
//    private List<Metal_meterial> list;

    public MetalmeterialAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Metal_meterial> objects) {
        super(context, resource, objects);
        this.context = context;
        this.subLayout = resource;
//        this.list = objects;
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Metal_meterial item = getItem(position);
        class ViewHolder {
            TextView mMetal_meterial_name;
            ImageView iv_tag;
        }
        View view = null;
        ViewHolder vh;
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(subLayout, null);
            vh = new ViewHolder();
            vh.mMetal_meterial_name = (TextView) view.findViewById(R.id.metal_meterial_id);
            vh.iv_tag = (ImageView) view.findViewById(R.id.metal_meterial_iv);
            view.setTag(vh);
        } else {
            view = convertView;
                    vh = (ViewHolder) view.getTag();
        }
        vh.mMetal_meterial_name.setText(item.getMetal_meterial_name());
//        Toast.makeText(context, metal_meterial.getMetal_meterial_name(), Toast.LENGTH_SHORT).show();
        vh.mMetal_meterial_name.setTextSize(16);
        vh.mMetal_meterial_name.getPaint().setFakeBoldText(true);
        vh.iv_tag.setImageResource(R.mipmap.more);

        return view;
    }
}
