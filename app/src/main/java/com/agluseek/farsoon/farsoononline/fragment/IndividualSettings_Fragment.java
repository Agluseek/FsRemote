package com.agluseek.farsoon.farsoononline.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.agluseek.farsoon.farsoononline.Interface.RecyclerViewOnItemClickListener;
import com.agluseek.farsoon.farsoononline.R;
import com.agluseek.farsoon.farsoononline.activity.PasswordActivity;
import com.agluseek.farsoon.farsoononline.model.Individual_Settings;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Farsoon Wu'Ang
 *         个性设置Fragment
 */
public class IndividualSettings_Fragment extends android.app.Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static View rootView;
    private RecyclerView mRecyclerView;
    private List<Individual_Settings> mIndividual;
    private Individual_Adapter mAdapter;
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    public IndividualSettings_Fragment() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter f2.
     * @return A new instance of fragment IndividualSettings_Fragment.
     */

    public static IndividualSettings_Fragment newInstance(String param1, String param2) {
        IndividualSettings_Fragment fragment = new IndividualSettings_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_individual_settings_, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.individual_recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);
        initmIndividual();
        mAdapter = new Individual_Adapter();
        mAdapter.setOnItemClickListener(new RecyclerViewOnItemClickListener() {
            @Override
            public void setOnItemClick(View view, int position, int id) {
                PasswordActivity.actionStart(getActivity());
            }


            @Override
            public void setOnItemLongClick(View view, int position) {

            }
        });

        mRecyclerView.setAdapter(mAdapter);
        return rootView;

    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    private void initmIndividual() {
        mIndividual = new ArrayList<Individual_Settings>();
        Individual_Settings ss = new Individual_Settings(R.mipmap.edit, "修改密码");
//        Individual_Settings forget = new Individual_Settings(R.mipmap.edit, "找回密码");
        mIndividual.add(ss);
//        mIndividual.add(forget);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /*
    初始化接口
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    class Individual_Adapter extends RecyclerView.Adapter<Individual_Adapter.MyViewHolder> {
        private RecyclerViewOnItemClickListener onItemClickListener;

        public void setOnItemClickListener(RecyclerViewOnItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;
        }

        @Override
        public Individual_Adapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.individual_fragment_item, viewGroup, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(final Individual_Adapter.MyViewHolder mViewHolder, int i) {
            Individual_Settings individual = mIndividual.get(i);

            mViewHolder.iv.setImageResource(individual.getIv());
            mViewHolder.tv.setText(individual.getMsg());

            if (onItemClickListener != null) {
                mViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = mViewHolder.getLayoutPosition();
                        onItemClickListener.setOnItemClick(v, pos,1);
                    }
                });
                mViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        int pos = mViewHolder.getLayoutPosition();
                        onItemClickListener.setOnItemLongClick(v, pos);
                        return true;

                    }
                });
            }

        }

        @Override
        public int getItemCount() {
            return mIndividual.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            ImageView iv;
            TextView tv;

            public MyViewHolder(View itemView) {
                super(itemView);
                iv = (ImageView) itemView.findViewById(R.id.individual_iv);
                tv = (TextView) itemView.findViewById(R.id.individual_tv);
            }
        }
    }

}
