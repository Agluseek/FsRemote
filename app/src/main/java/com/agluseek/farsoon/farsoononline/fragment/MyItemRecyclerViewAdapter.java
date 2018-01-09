package com.agluseek.farsoon.farsoononline.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.agluseek.farsoon.farsoononline.R;
import com.agluseek.farsoon.farsoononline.fragment.DevicesItem_Fragment.OnListFragmentInteractionListener;
import com.agluseek.farsoon.farsoononline.fragment.dummy.DummyContent.DummyItem;
import com.agluseek.farsoon.farsoononline.model.Device;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 *
 */

public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<Device> mValues;
    private OnListFragmentInteractionListener mListener;

    public MyItemRecyclerViewAdapter(List<Device> items, OnListFragmentInteractionListener listener) {
        this.mValues = items;
        this.mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_devicesitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.mItem = mValues.get(position);

        final String id = mValues.get(position).getID();

        if (id.contains("121")) {
            holder.mImageView.setImageResource(R.mipmap.f121);
        } else if (id.contains("271")) {
            holder.mImageView.setImageResource(R.mipmap.f271);
        } else if (id.contains("251")) {
            holder.mImageView.setImageResource(R.mipmap.f251);
        } else if (id.contains("252")) {
            holder.mImageView.setImageResource(R.mipmap.f252);
        } else if (id.contains("401")) {
            holder.mImageView.setImageResource(R.mipmap.f402);
        } else if (id.contains("402")) {
            holder.mImageView.setImageResource(R.mipmap.f402);
        } else if (id.contains("403")) {
            holder.mImageView.setImageResource(R.mipmap.f403);
        } else if (id.contains("eform")) {
            holder.mImageView.setImageResource(R.mipmap.eform);
        }

        holder.mIdView.setText(mValues.get(position).getName());
        holder.mContentView.setText(mValues.get(position).getType());
        holder.mView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (null != mListener) {

                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    int p = holder.getLayoutPosition();

                    //  接口保存数据并不做具体行为
                    mListener.onClickListFragmentInteraction(v, p, id);

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final ImageView mImageView;
        public Device mItem;

        public ViewHolder(View view) {

            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.InfoName_tv);
            mContentView = (TextView) view.findViewById(R.id.InfoType_tv);
            mImageView = (ImageView) view.findViewById(R.id.devicesitem_iv);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
