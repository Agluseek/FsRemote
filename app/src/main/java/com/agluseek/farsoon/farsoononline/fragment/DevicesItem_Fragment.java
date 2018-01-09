package com.agluseek.farsoon.farsoononline.fragment;

import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.agluseek.farsoon.farsoononline.R;
import com.agluseek.farsoon.farsoononline.Service.MyService;
import com.agluseek.farsoon.farsoononline.activity.DevicesInfoActivity;
import com.agluseek.farsoon.farsoononline.model.Device;
import com.agluseek.farsoon.farsoononline.model.PushInfo;
import com.agluseek.farsoon.farsoononline.utils.Functions;
import com.agluseek.farsoon.farsoononline.utils.Globals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * 登录后用户设备列表
 */
public class DevicesItem_Fragment extends android.app.Fragment implements ServiceConnection {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final String ARG_DEVICE_INFO = "device-info";
    private int mColumnCount = 1;
    private String mDeviceInfo;
    private MyService.EchoServiceBinder binder;
    public static MyService service1 = null;
    private Intent intentService;

    private static List<Device> deviceList;
    private OnListFragmentInteractionListener mListener;

    public DevicesItem_Fragment() {

    }

    @SuppressWarnings("unused")
    public static DevicesItem_Fragment newInstance(int columnCount, String ReceiveInfo) {
        DevicesItem_Fragment fragment = new DevicesItem_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_DEVICE_INFO, ReceiveInfo);
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            mDeviceInfo = getArguments().getString(ARG_DEVICE_INFO);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_item_deviceslist, container, false);

        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;

            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setHasFixedSize(true);
            recyclerView.addItemDecoration(new DividerGridItemDecoration(getActivity()));

            try {

                parseDeviceInfo(Functions.readJson(mDeviceInfo));

                System.out.println("设备数量------->>" + deviceList.size());

            } catch (Exception e) {
                e.printStackTrace();
            }

            MyItemRecyclerViewAdapter myItemRecyclerViewAdapter = new MyItemRecyclerViewAdapter(deviceList, new OnListFragmentInteractionListener() {

                @Override
                public void onClickListFragmentInteraction(View view, int position, String id) {
                    Device device = deviceList.get(position);
                    System.out.println("这是不同ID的设备消息--------->>" + device.getNormalMsg());
                    System.out.println("这是不同ID的黄色报警--------->>" + device.getWarningMsg());
                    System.out.println("这是不同ID的红色报警--------->>" + device.getAlarmMsg());

                    if (device.getID() == id) {
                        Globals.deviceName = device.getName();
                        Globals.deviceId = id;
                        Globals.getNormalMsg = device.getNormalMsg();
                        Globals.getWarningMsg = device.getWarningMsg();
                        Globals.getAlarmMsg = device.getAlarmMsg();
                        List<PushInfo> infoList = service1.GetPushInfoList();

                        // 设备实时信息Activity
                        DevicesInfoActivity.actionStart(getActivity(), infoList);

                    }
                }
            });

            recyclerView.setAdapter(myItemRecyclerViewAdapter);
        }

        /*
         * 开启服务器推送
         */

        intentService = new Intent(getActivity(), MyService.class);
        getActivity().bindService(intentService, this, Context.BIND_AUTO_CREATE);

        return view;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        getActivity().unbindService(this);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        binder = (MyService.EchoServiceBinder) service;
        service1 = binder.getService();
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
    }

    public interface OnListFragmentInteractionListener {
        void onClickListFragmentInteraction(View view, int position, String Devicesid);
    }

    private static void parseDeviceInfo(List<Map<String, String>> devices) {
        deviceList = new ArrayList<Device>();
        for (int i = 0; i < devices.size(); i++) {
            Map<String, String> deviceMap = devices.get(i);
            //         Set<Map.Entry<String,String>> entrySet = deviceMap.entrySet();
            //         for(Map.Entry<String,String> each : entrySet){
            //         LogUtil.d("DeviceFragment",each.getKey()+"->"+each.getValue());
            //         }
            Device device = new Device();
            device.setName(deviceMap.get("Name"));
            device.setType(deviceMap.get("Type"));
            device.setAlarmSum(Integer.parseInt(deviceMap.get("AlarmSum")));
            device.setID(deviceMap.get("ID"));
            device.setSoftwareVar(deviceMap.get("SoftwareVar"));
            device.setWarnSum(Integer.parseInt(deviceMap.get("WarnSum")));
            device.setNormalMsg(Integer.parseInt(deviceMap.get("NormalMsg")));
            device.setWarningMsg(Integer.parseInt(deviceMap.get("WarningMsg")));
            device.setAlarmMsg(Integer.parseInt(deviceMap.get("AlarmMsg")));
            deviceList.add(device);
        }
    }

    public void sendNotifation(int id, String title, String text) {
        NotificationCompat.Builder nBuilder =
                new NotificationCompat.Builder(getActivity());
        nBuilder.setSmallIcon(R.mipmap.ic_launcher);
        nBuilder.setContentTitle(title);
        nBuilder.setContentText(text);
        nBuilder.setVibrate(new long[]{100, 100, 100});
        nBuilder.setLights(Color.RED, 1000, 1000);

        NotificationManager notifyMgr = (NotificationManager) getActivity().getSystemService(NOTIFICATION_SERVICE);
        notifyMgr.notify(id, nBuilder.build());

    }
}
