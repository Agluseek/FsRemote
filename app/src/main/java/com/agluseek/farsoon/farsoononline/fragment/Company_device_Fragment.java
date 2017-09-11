package com.agluseek.farsoon.farsoononline.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.agluseek.farsoon.farsoononline.R;
import com.agluseek.farsoon.farsoononline.activity.Metal_FS121MD_detailsActivity;
import com.agluseek.farsoon.farsoononline.activity.Metal_FS121M_detailsActivity;
import com.agluseek.farsoon.farsoononline.activity.Metal_detailActivity;
import com.agluseek.farsoon.farsoononline.activity.Nylon_252P_detailsActivity;
import com.agluseek.farsoon.farsoononline.activity.Nylon_430P_detailsActivity;
import com.agluseek.farsoon.farsoononline.activity.Nylon_eForm_detailsActivity;
import com.agluseek.farsoon.farsoononline.adapter.MetalAdapter;
import com.agluseek.farsoon.farsoononline.adapter.NylonAdapter;
import com.agluseek.farsoon.farsoononline.model.Metal_device;
import com.agluseek.farsoon.farsoononline.model.Nylon_device;

import java.util.ArrayList;
import java.util.List;

    /**
     * Created by Wu'Ang on 2017/4/17.
     */


public class Company_device_Fragment extends Fragment {
    private static View rootView;
    private MetalAdapter metal_adapter;

    private List<Metal_device> metal_list;
    private List<Nylon_device> nylon_list;

    private ListView metal_lv;
    private ListView nylon_lv;
    private NylonAdapter nylon_adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        rootView = inflater.inflate(R.layout.company_device_fragment, container, false);
        metal_lv = (ListView) rootView.findViewById(R.id.metal_device_list);
        nylon_lv = (ListView) rootView.findViewById(R.id.nylon_device_list);

        final Metal_device metal = new Metal_device();
        metal.setMetal_device_name("MLS FS-271M");
        final Metal_device metal1 = new Metal_device();
        metal1.setMetal_device_name("MLS FS-121M");
        final Metal_device metal2 = new Metal_device();
        metal2.setMetal_device_name("MLS FS-121M-D");
        metal_list = new ArrayList<>();
        metal_list.add(metal);
        metal_list.add(metal1);
        metal_list.add(metal2);

        metal_adapter = new MetalAdapter(getActivity(), R.layout.metal_device, metal_list);
        metal_lv.setAdapter(metal_adapter);

        metal_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (metal_list.get(position).equals(metal)) {
                    Intent i = new Intent(getActivity(), Metal_detailActivity.class);
                    startActivityForResult(i, 0);
                } else if (metal_list.get(position).equals(metal1)) {
                    Intent i = new Intent(getActivity(), Metal_FS121M_detailsActivity.class);
                    startActivityForResult(i, 1);
                } else if (metal_list.get(position).equals(metal2)) {
                    Intent i = new Intent(getActivity(), Metal_FS121MD_detailsActivity.class);
                    startActivityForResult(i, 2);

                }
            }
        });
                        
        final Nylon_device nylon = new Nylon_device();
        nylon.setNylon_device_name("PLS 252P系列");
        final Nylon_device nylon1 = new Nylon_device();
        nylon1.setNylon_device_name("PLS 403P系列");
        final Nylon_device nylon2 = new Nylon_device();
        nylon2.setNylon_device_name("PLS eFrom");
        nylon_list = new ArrayList<Nylon_device>();
        nylon_list.add(nylon);
        nylon_list.add(nylon1);
        nylon_list.add(nylon2);


        nylon_adapter = new NylonAdapter(getActivity(), R.layout.nylon_device, nylon_list);
        nylon_lv.setAdapter(nylon_adapter);

        nylon_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (nylon_list.get(position).equals(nylon)) {
                    Intent i = new Intent(getActivity(), Nylon_252P_detailsActivity.class);
                    startActivity(i);
                } else if (nylon_list.get(position).equals(nylon1)) {
                    Intent i = new Intent(getActivity(), Nylon_430P_detailsActivity.class);
                    startActivity(i);
                } else if (nylon_list.get(position).equals(nylon2)) {
                    Intent i = new Intent(getActivity(), Nylon_eForm_detailsActivity.class);
                    startActivity(i);

                }

            }
        });

        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}
