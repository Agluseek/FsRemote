package com.agluseek.farsoon.farsoononline.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.agluseek.farsoon.farsoononline.R;
import com.agluseek.farsoon.farsoononline.Service.MyService;
import com.agluseek.farsoon.farsoononline.adapter.MyDeviceInfoItemRecyclerView;
import com.agluseek.farsoon.farsoononline.fragment.DevicesItem_Fragment;
import com.agluseek.farsoon.farsoononline.fragment.DividerGridItemDecoration;
import com.agluseek.farsoon.farsoononline.fragment.SystemSettings_Fragment;
import com.agluseek.farsoon.farsoononline.model.Device;
import com.agluseek.farsoon.farsoononline.model.DeviceStatus;
import com.agluseek.farsoon.farsoononline.utils.Config;
import com.agluseek.farsoon.farsoononline.utils.Functions;
import com.agluseek.farsoon.farsoononline.utils.Globals;
import com.agluseek.farsoon.farsoononline.utils.HttpUtils;
import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class DevicesInfoActivity extends AppCompatActivity {
    private int normalMsg;
    private int warningMsg;
    private int alarmMsg;


    private RecyclerView recyclerView;
    private ImageView devicesInfo_iv;

    private static List<DeviceStatus> deviceStatisList;
    private String DeviceStatusInfo_JSON;
    private MyDeviceInfoItemRecyclerView myDeviceInfoItemRecyclerView;
    public static final int UPDATE_DEVICESTATUS = 0;
    private Timer timer = null;
    private TimerTask task = null;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == UPDATE_DEVICESTATUS) {
                DeviceStatusInfo_JSON = (String) msg.obj;
                DeviceStatus deviceStatus = JSON.parseObject(DeviceStatusInfo_JSON, DeviceStatus.class);
                System.out.println(deviceStatus.getID());
                System.out.println(deviceStatus.getRemainPowder());

                deviceStatisList = new ArrayList<DeviceStatus>();
                deviceStatisList.add(deviceStatus);

                System.out.println(deviceStatisList.size());
                System.out.println("Handler回传的JSON值----->>" + DeviceStatusInfo_JSON);
                myDeviceInfoItemRecyclerView = new MyDeviceInfoItemRecyclerView(deviceStatisList, new MyDeviceInfoItemRecyclerView.OnListInteractionListener() {
                    @Override
                    public void onClickListInteraction(View view, int position) {

                    }
                });

                myDeviceInfoItemRecyclerView.notifyDataSetChanged();
                recyclerView.setAdapter(myDeviceInfoItemRecyclerView);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devices_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.devicesinfo_toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.devices_status_recyclerView);
        devicesInfo_iv = (ImageView) findViewById(R.id.devices_iv);
        Toast.makeText(this, "正在加载实时数据，请稍候", Toast.LENGTH_LONG).show();

        if (toolbar != null) {
            setSupportActionBar(toolbar);

        }

        ActionBar bar = getSupportActionBar();

        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled(true);
        }

        bar.setTitle(Globals.deviceId);

        if (Globals.deviceId.contains("121")) {
            devicesInfo_iv.setImageResource(R.mipmap.f121);
        } else if (Globals.deviceId.contains("271")) {
            devicesInfo_iv.setImageResource(R.mipmap.f271);
        } else if (Globals.deviceId.contains("251")) {
            devicesInfo_iv.setImageResource(R.mipmap.f251);
        } else if (Globals.deviceId.contains("252")) {
            devicesInfo_iv.setImageResource(R.mipmap.f252);
        } else if (Globals.deviceId.contains("401")) {
            devicesInfo_iv.setImageResource(R.mipmap.f402);
        } else if (Globals.deviceId.contains("402")) {
            devicesInfo_iv.setImageResource(R.mipmap.f402);
        } else if (Globals.deviceId.contains("403")) {
            devicesInfo_iv.setImageResource(R.mipmap.f403);
        } else if (Globals.deviceId.contains("eform")) {
            devicesInfo_iv.setImageResource(R.mipmap.eform);
        }
        System.out.println("这是点击的位置的ID--------->>" + Globals.deviceId);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.addItemDecoration(new DividerGridItemDecoration(this));


        startTimer();
        startService(new Intent(this, MyService.class));

    }

    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
            task.cancel();

            timer = null;
            task = null;
        }
    }

    private void startTimer() {
        if (timer == null) {
            timer = new Timer();
            task = new TimerTask() {
                @Override
                public void run() {
                    /**
                     *      此处轮询网络请求实时更新设备信息
                     */
                    attempGetDeviceInfo();
                }
            };
            timer.schedule(task, 5000, 2000);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.devicesetting, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_settings:
                AlertSettings.actionStart(this);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static void actionStart(Context context) {
        Intent i = new Intent(context, DevicesInfoActivity.class);
        context.startActivity(i);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopTimer();
    }

    private void attempGetDeviceInfo() {

        String api = "http://" + Config.address + Config.loginAPI + Config.getDeviceAPI + Globals.deviceId + "asked=1";
        HttpUtils.doGet(api, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(e);

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Message s = Message.obtain();
                s.what = UPDATE_DEVICESTATUS;
                s.obj = result;
                System.out.println("DeviceInfoActivity实时设备信息返回值------>>>" + result);
                handler.sendMessage(s);
            }
        });

    }

    private static void parseDeviceInfo(List<Map<String, String>> devices) {
        deviceStatisList = new ArrayList<DeviceStatus>();
        for (int i = 0; i < devices.size(); i++) {
            Map<String, String> deviceMap = devices.get(i);

            DeviceStatus device = new DeviceStatus();
            device.setID(deviceMap.get("ID"));
            device.setCurHeight(deviceMap.get("CurHeight"));
            device.setCurState(deviceMap.get("CurState"));
            device.setMaterialName(deviceMap.get("MaterialName"));
            device.setPackageHeight(deviceMap.get("PackageHeight"));
            device.setPackageName(deviceMap.get("PackageName"));
            device.setRemainPowder(deviceMap.get("RemainPowder"));
            device.setRemainTime(deviceMap.get("RemainTime"));
            device.setTemp(deviceMap.get("Temp"));
            deviceStatisList.add(device);

        }
    }
}
