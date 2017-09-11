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
import com.agluseek.farsoon.farsoononline.activity.FS_17_4PH_Metal_detailsActivity;
import com.agluseek.farsoon.farsoononline.activity.FS_18Ni300_Metal_detailsActivity;
import com.agluseek.farsoon.farsoononline.activity.FS_316L_Metal_detailsActivity;
import com.agluseek.farsoon.farsoononline.activity.FS_3250MF_Nylon_detailsActivity;
import com.agluseek.farsoon.farsoononline.activity.FS_3300PA_Nylon_detailsActivity;
import com.agluseek.farsoon.farsoononline.activity.FS_3400CF_Nylon_detailsActivity;
import com.agluseek.farsoon.farsoononline.activity.FS_3400GF_Nylon_detailsActivity;
import com.agluseek.farsoon.farsoononline.activity.FS_420_Metal_detailsActivity;
import com.agluseek.farsoon.farsoononline.activity.FS_6028PA_Nylon_detailsActivity;
import com.agluseek.farsoon.farsoononline.activity.FS_AlSi10Mg_Metal_detailsActivity;
import com.agluseek.farsoon.farsoononline.activity.FS_CoCrMoW_Metal_detailsActivity;
import com.agluseek.farsoon.farsoononline.activity.FS_Cu90Sn10_Metal_detailsActivity;
import com.agluseek.farsoon.farsoononline.activity.FS_GH3536_Metal_detailsActivity;
import com.agluseek.farsoon.farsoononline.activity.FS_IN718_Metal_detailsActivity;
import com.agluseek.farsoon.farsoononline.activity.FS_Ta_Metal_detailsActivity;
import com.agluseek.farsoon.farsoononline.activity.FS_Ti6AL4V_Metal_detailsActivity;
import com.agluseek.farsoon.farsoononline.activity.FS_W_Metal_detailsActivity;
import com.agluseek.farsoon.farsoononline.activity.TPU_X92A_2_Nylon_detailsActivity;
import com.agluseek.farsoon.farsoononline.adapter.MetalmeterialAdapter;
import com.agluseek.farsoon.farsoononline.adapter.NylonmeterialAdapter;
import com.agluseek.farsoon.farsoononline.model.Metal_meterial;
import com.agluseek.farsoon.farsoononline.model.Nylon_meterial;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by X on 2017/4/17.
 */


public class Company_meterial_Fragment extends Fragment {
    private static View rootView;
    private ListView Metal_meterial_lv;
    private ListView Nylon_meterial_lv;
    private List<Metal_meterial> Metal_meterial_list = new ArrayList<>();
    private List<Nylon_meterial> nylon_meterial_list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.company_meterial_fragment, null);
        Metal_meterial_lv = (ListView) rootView.findViewById(R.id.metal_meterial_lv);
        Nylon_meterial_lv = (ListView) rootView.findViewById(R.id.nylon_meterial_lv);

        final Metal_meterial metal_meterial = new Metal_meterial();
        metal_meterial.setMetal_meterial_name("不锈钢粉末材料FS 316L");
        final Metal_meterial metal_meterial1 = new Metal_meterial();
        metal_meterial1.setMetal_meterial_name("硬质不锈钢粉末FS 17-4PH");
        final Metal_meterial metal_meterial2 = new Metal_meterial();
        metal_meterial2.setMetal_meterial_name("钴铬合金粉末 FS_CoCrMoW");
        final Metal_meterial metal_meterial3 = new Metal_meterial();
        metal_meterial3.setMetal_meterial_name("钛合金粉末FS Ti6AL4V");
        final Metal_meterial metal_meterial4 = new Metal_meterial();
        metal_meterial4.setMetal_meterial_name("铝合金粉末FS AlSi10Mg");
        final Metal_meterial metal_meterial5 = new Metal_meterial();
        metal_meterial5.setMetal_meterial_name("模具钢粉末FS 18Ni300");
        final Metal_meterial metal_meterial6 = new Metal_meterial();
        metal_meterial6.setMetal_meterial_name("模具钢粉末FS 420");
        final Metal_meterial metal_meterial7 = new Metal_meterial();
        metal_meterial7.setMetal_meterial_name("青铜粉末FS Cu90Sn10");
        final Metal_meterial metal_meterial8 = new Metal_meterial();
        metal_meterial8.setMetal_meterial_name("镍基高温合金粉末FS GH3536");
        final Metal_meterial metal_meterial9 = new Metal_meterial();
        metal_meterial9.setMetal_meterial_name("镍基高温合金粉末FS IN718");
        final Metal_meterial metal_meterial10 = new Metal_meterial();
        metal_meterial10.setMetal_meterial_name("钽粉末FS Ta");
        final Metal_meterial metal_meterial11 = new Metal_meterial();
        metal_meterial11.setMetal_meterial_name("钨粉末FS W");


        Metal_meterial_list.add(metal_meterial);
        Metal_meterial_list.add(metal_meterial1);
        Metal_meterial_list.add(metal_meterial2);
        Metal_meterial_list.add(metal_meterial3);
        Metal_meterial_list.add(metal_meterial4);
        Metal_meterial_list.add(metal_meterial5);
        Metal_meterial_list.add(metal_meterial6);
        Metal_meterial_list.add(metal_meterial7);
        Metal_meterial_list.add(metal_meterial8);
        Metal_meterial_list.add(metal_meterial9);
        Metal_meterial_list.add(metal_meterial10);
        Metal_meterial_list.add(metal_meterial11);
        MetalmeterialAdapter metalmeterialAdapter = new MetalmeterialAdapter(getActivity(), R.layout.metal_meterial, Metal_meterial_list);
        Metal_meterial_lv.setAdapter(metalmeterialAdapter);
        Metal_meterial_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (Metal_meterial_list.get(position).equals(metal_meterial)) {
                    Intent i = new Intent(getActivity(), FS_316L_Metal_detailsActivity.class);
                    startActivityForResult(i, 0);
                } else if (Metal_meterial_list.get(position).equals(metal_meterial1)) {
                    Intent i = new Intent(getActivity(), FS_17_4PH_Metal_detailsActivity.class);
                    startActivityForResult(i, 1);
                } else if (Metal_meterial_list.get(position).equals(metal_meterial2)) {
                    Intent i = new Intent(getActivity(), FS_CoCrMoW_Metal_detailsActivity.class);
                    startActivityForResult(i, 2);
                } else if (Metal_meterial_list.get(position).equals(metal_meterial3)) {
                    Intent i = new Intent(getActivity(), FS_Ti6AL4V_Metal_detailsActivity.class);
                    startActivityForResult(i, 3);
                } else if (Metal_meterial_list.get(position).equals(metal_meterial4)) {
                    Intent i = new Intent(getActivity(), FS_AlSi10Mg_Metal_detailsActivity.class);
                    startActivityForResult(i, 4);
                } else if (Metal_meterial_list.get(position).equals(metal_meterial5)) {
                    Intent i = new Intent(getActivity(), FS_18Ni300_Metal_detailsActivity.class);
                    startActivityForResult(i, 5);
                } else if (Metal_meterial_list.get(position).equals(metal_meterial6)) {
                    Intent i = new Intent(getActivity(), FS_420_Metal_detailsActivity.class);
                    startActivityForResult(i, 6);
                } else if (Metal_meterial_list.get(position).equals(metal_meterial7)) {
                    Intent i = new Intent(getActivity(), FS_Cu90Sn10_Metal_detailsActivity.class);
                    startActivityForResult(i, 7);
                } else if (Metal_meterial_list.get(position).equals(metal_meterial8)) {
                    Intent i = new Intent(getActivity(), FS_GH3536_Metal_detailsActivity.class);
                    startActivityForResult(i, 8);
                } else if (Metal_meterial_list.get(position).equals(metal_meterial9)) {
                    Intent i = new Intent(getActivity(), FS_IN718_Metal_detailsActivity.class);
                    startActivityForResult(i, 9);
                } else if (Metal_meterial_list.get(position).equals(metal_meterial10)) {
                    Intent i = new Intent(getActivity(), FS_Ta_Metal_detailsActivity.class);
                    startActivityForResult(i, 10);
                } else if (Metal_meterial_list.get(position).equals(metal_meterial11)) {
                    Intent i = new Intent(getActivity(), FS_W_Metal_detailsActivity.class);
                    startActivityForResult(i, 11);
                }

            }
        });

        final Nylon_meterial nylon_meterial = new Nylon_meterial();
        nylon_meterial.setNylon_meterial_name("FS 3300PA");
        final Nylon_meterial nylon_meterial1 = new Nylon_meterial();
        nylon_meterial1.setNylon_meterial_name("FS 3250MF");
        final Nylon_meterial nylon_meterial2 = new Nylon_meterial();
        nylon_meterial2.setNylon_meterial_name("FS 3400CF");
        final Nylon_meterial nylon_meterial3 = new Nylon_meterial();
        nylon_meterial3.setNylon_meterial_name("FS 3400GF");
        final Nylon_meterial nylon_meterial4 = new Nylon_meterial();
        nylon_meterial4.setNylon_meterial_name("FS 6028PA");
        final Nylon_meterial nylon_meterial5 = new Nylon_meterial();
        nylon_meterial5.setNylon_meterial_name("TPU X92A-f2");

        nylon_meterial_list.add(nylon_meterial);
        nylon_meterial_list.add(nylon_meterial1);
        nylon_meterial_list.add(nylon_meterial2);
        nylon_meterial_list.add(nylon_meterial3);
        nylon_meterial_list.add(nylon_meterial4);
        nylon_meterial_list.add(nylon_meterial5);


        final NylonmeterialAdapter nylonmeterialAdapter = new NylonmeterialAdapter(getActivity(), R.layout.metal_meterial, nylon_meterial_list);
        Nylon_meterial_lv.setAdapter(nylonmeterialAdapter);
        Nylon_meterial_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (nylon_meterial_list.get(position).equals(nylon_meterial)) {
                    Intent i = new Intent(getActivity(), FS_3300PA_Nylon_detailsActivity.class);
                    startActivityForResult(i, 0);
                } else if (nylon_meterial_list.get(position).equals(nylon_meterial1)) {
                    Intent i = new Intent(getActivity(), FS_3250MF_Nylon_detailsActivity.class);
                    startActivityForResult(i, 1);
                } else if (nylon_meterial_list.get(position).equals(nylon_meterial2)) {
                    Intent i = new Intent(getActivity(), FS_3400CF_Nylon_detailsActivity.class);
                    startActivityForResult(i, 2);
                } else if (nylon_meterial_list.get(position).equals(nylon_meterial3)) {
                    Intent i = new Intent(getActivity(), FS_3400GF_Nylon_detailsActivity.class);
                    startActivityForResult(i, 3);
                } else if (nylon_meterial_list.get(position).equals(nylon_meterial4)) {
                    Intent i = new Intent(getActivity(), FS_6028PA_Nylon_detailsActivity.class);
                    startActivityForResult(i, 4);
                } else if (nylon_meterial_list.get(position).equals(nylon_meterial5)) {
                    Intent i = new Intent(getActivity(), TPU_X92A_2_Nylon_detailsActivity.class);
                    startActivityForResult(i, 5);
                }

            }
        });


        return rootView;
    }
}
