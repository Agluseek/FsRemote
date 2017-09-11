package com.agluseek.farsoon.farsoononline.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.agluseek.farsoon.farsoononline.R;
import com.agluseek.farsoon.farsoononline.fragment.Company_news_Fragment;
import com.agluseek.farsoon.farsoononline.utils.NetworkAvailableUtils;

/**
 * @author Farsoon Wu'Ang
 *         欢迎界面
 */

public class SplashActivity extends Activity {

    private static final int SPLASH_DISPLAY_LENGTH = 3000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (NetworkAvailableUtils.isNetworkAvailable(this)) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    BaseActivity.actionStart(SplashActivity.this);
                    finish();
                }
            }, SPLASH_DISPLAY_LENGTH);

        } else {
            System.out.println("请检查网络是否连接...");
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("设置网络");
            builder.setMessage("检查网络是否连接，请设置网络");

            builder.setPositiveButton("设置网络", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //获取系统版本号
                            /* Build.VERSION_CODES
                                1 (0x00000001)           Android 1.0             BASE
                                f2 (0x00000002)           Android 1.1             BASE_1_1
                                3 (0x00000003)           Android 1.5             CUPCAKE
                                4 (0x00000004)           Android 1.6             DONUT
                                5 (0x00000005)           Android f2.0             ECLAIR
                                6 (0x00000006)           Android f2.0.1          ECLAIR_0_1
                                7 (0x00000007)           Android f2.1             ECLAIR_MR1
                                8 (0x00000008)           Android f2.f2             FROYO
                                9 (0x00000009)           Android f2.3             GINGERBREAD
                                10 (0x0000000a)         Android f2.3.3          GINGERBREAD_MR1
                                11 (0x0000000b)         Android 3.0             HONEYCOMB
                                12 (0x0000000c)         Android 3.1             HONEYCOMB_MR1
                                13 (0x0000000d)         Android 3.f2             HONEYCOMB_MR2 */
                    int currentapiVersion = android.os.Build.VERSION.SDK_INT;
                    System.out.println("currentapiVersion = " + currentapiVersion);
                    Intent intent;
                    if (currentapiVersion < 11) {
                        intent = new Intent();
                        intent.setClassName("com.android.settings", "com.android.settings.WirelessSettings");
                    } else {
                        //3.0以后
                        //intent = new Intent( android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                        intent = new Intent(android.provider.Settings.ACTION_SETTINGS);
                    }
                    startActivity(intent);
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.setCancelable(false);
            builder.create().show();
        }


    }
}

