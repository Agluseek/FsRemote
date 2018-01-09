package com.agluseek.farsoon.farsoononline.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;

import com.agluseek.farsoon.farsoononline.R;
import com.agluseek.farsoon.farsoononline.utils.Config;
import com.agluseek.farsoon.farsoononline.utils.Globals;
import com.agluseek.farsoon.farsoononline.utils.HttpUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class AlertSettings extends AppCompatActivity {
    private SwitchCompat NormalMessage, YellowMessage, RedMessage;
    private static final int NORMALMSG_SUCCESS = 1;
    private static final int NORMALMSG_FAILURE = 2;
    private static final int WARNINGMSG_SUCCESS = 3;
    private static final int WARNINGMSG_FAILURE = 4;
    private static final int ALERTMSG_SUCCESS = 5;
    private static final int ALERTMSG_FAILURE = 6;

    private boolean normalmsg;
    private boolean warningmsg;
    private boolean alarmmsg;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case NORMALMSG_SUCCESS:
                    if (NormalMessage.isChecked()) {
                        System.out.println(Globals.deviceId);
                        String api = "http://" + Config.address + Config.loginAPI + Config.getDeviceAPI + Globals.deviceId + Config.SystemSettingsNormal + Config.Success;

                        HttpUtils.doGet(api, new okhttp3.Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {

                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                System.out.println("普通消息打开-------->>" + response.body().string());

                            }

                        });

                    } else {
                        String api = "http://" + Config.address + Config.loginAPI + Config.getDeviceAPI + Globals.deviceId + Config.SystemSettingsNormal + Config.Failure;
                        HttpUtils.doGet(api, new okhttp3.Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {

                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {

                                System.out.println("普通消息关闭-------->>" + response.body().string());

                            }
                        });
                    }
                    break;
                case WARNINGMSG_SUCCESS:

                    if (YellowMessage.isChecked()) {
                        System.out.println(Globals.deviceId);
                        String api = "http://" + Config.address + Config.loginAPI + Config.getDeviceAPI + Globals.deviceId + Config.SystemSettingsRed + Config.Success;
                        HttpUtils.doGet(api, new okhttp3.Callback() {

                            @Override
                            public void onFailure(Call call, IOException e) {

                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                System.out.println("黄色报警打开-------->>" + response.body().string());
                            }
                        });
                    } else {
                        String api = "http://" + Config.address + Config.loginAPI + Config.getDeviceAPI + Globals.deviceId + Config.SystemSettingsRed + Config.Failure;
                        HttpUtils.doGet(api, new okhttp3.Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {

                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {

                                System.out.println("黄色报警关闭-------->>" + response.body().string());

                            }
                        });
                    }
                    break;

                case ALERTMSG_SUCCESS:
                    if (RedMessage.isChecked()) {
                        System.out.println(Globals.deviceId);
                        String api = "http://" + Config.address + Config.loginAPI + Config.getDeviceAPI + Globals.deviceId + Config.SystemSettingsYel + Config.Success;
                        HttpUtils.doGet(api, new okhttp3.Callback() {

                            @Override
                            public void onFailure(Call call, IOException e) {

                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                System.out.println("红色报警打开-------->>" + response.body().string());
                            }
                        });
                    } else {
                        String api = "http://" + Config.address + Config.loginAPI + Config.getDeviceAPI + Globals.deviceId + Config.SystemSettingsYel + Config.Failure;
                        HttpUtils.doGet(api, new okhttp3.Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {

                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {

                                System.out.println("红色报警关闭-------->>" + response.body().string());

                            }
                        });
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_include);
        Toolbar toolbar = (Toolbar) findViewById(R.id.alert_include_toolbar);

        NormalMessage = (SwitchCompat) findViewById(R.id.switch_NormalMessage);
        YellowMessage = (SwitchCompat) findViewById(R.id.switch_YellowMessage);
        RedMessage = (SwitchCompat) findViewById(R.id.switch_RedMessage);

        if (toolbar != null) {
            setSupportActionBar(toolbar);

        }

        ActionBar bar = getSupportActionBar();

        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled(true);
        }

        bar.setTitle(Globals.deviceName + "   警报设置");

        if (Globals.getNormalMsg == 1) {
            SharedPreferences settings = getSharedPreferences(Globals.deviceId, Context.MODE_PRIVATE);
            NormalMessage.setChecked(settings.getBoolean("NormalMessage", true));
        }
        if (Globals.getNormalMsg == 0) {
            SharedPreferences settings = getSharedPreferences(Globals.deviceId, Context.MODE_PRIVATE);
            NormalMessage.setChecked(settings.getBoolean("NormalMessage", false));
        }
        if (Globals.getWarningMsg == 1) {
            SharedPreferences settings = getSharedPreferences(Globals.deviceId, Context.MODE_PRIVATE);
            YellowMessage.setChecked(settings.getBoolean("YellowMessage", true));
        }
        if (Globals.getWarningMsg == 0) {
            SharedPreferences settings = getSharedPreferences(Globals.deviceId, Context.MODE_PRIVATE);
            YellowMessage.setChecked(settings.getBoolean("YellowMessage", false));
        }
        if (Globals.getAlarmMsg == 1) {
            SharedPreferences settings = getSharedPreferences(Globals.deviceId, Context.MODE_PRIVATE);
            System.out.println("红色警报的默认值----------->>" + settings.getBoolean("RedMessage", true));
            RedMessage.setChecked(settings.getBoolean("RedMessage", true));
        }

        if (Globals.getAlarmMsg == 0) {
            SharedPreferences settings = getSharedPreferences(Globals.deviceId, Context.MODE_PRIVATE);
            RedMessage.setChecked(settings.getBoolean("RedMessage", false));
        }

        NormalMessage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences settings = getSharedPreferences(Globals.deviceId, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean("NormalMessage", isChecked).commit();
                NormalMessage.setChecked(isChecked);
                handler.sendEmptyMessage(NORMALMSG_SUCCESS);

            }
        });

        YellowMessage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences settings = getSharedPreferences(Globals.deviceId, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean("YellowMessage", isChecked).commit();
                YellowMessage.setChecked(isChecked);
                handler.sendEmptyMessage(WARNINGMSG_SUCCESS);

            }

        });
        RedMessage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences settings = getSharedPreferences(Globals.deviceId, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean("RedMessage", isChecked).commit();
                RedMessage.setChecked(isChecked);
                handler.sendEmptyMessage(ALERTMSG_SUCCESS);
            }
        });

    }

    public static void actionStart(Context context) {
        Intent i = new Intent(context, AlertSettings.class);

        context.startActivity(i);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
