package com.agluseek.farsoon.farsoononline.activity;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.agluseek.farsoon.farsoononline.BottomNavigationViewHelper;
import com.agluseek.farsoon.farsoononline.R;
import com.agluseek.farsoon.farsoononline.fragment.Company_device_Fragment;
import com.agluseek.farsoon.farsoononline.fragment.Company_meterial_Fragment;
import com.agluseek.farsoon.farsoononline.fragment.Company_news_Fragment;
import com.agluseek.farsoon.farsoononline.fragment.My_Fragment;
import com.agluseek.farsoon.farsoononline.utils.NetworkAvailableUtils;
import com.alibaba.fastjson.JSON;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Wu'Ang on 2017/4/17.
 */

public class BaseActivity extends AppCompatActivity {
    private static String Receive;
    private long firstTime = 0;
    private BottomNavigationView navigation;
    private OkHttpClient mOkHttpClient;
    private String  Json_news;
    public static final int UPDATE_NUMBER = 0;

    private static String NewsUrl = "http://192.168.4.49/WebLearn/GetNewsList.aspx";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_company_news:
                    // 公司新闻
                    setFragment(new Company_news_Fragment());
                    return true;
                case R.id.navigation_company_device:
                    // 设备
                    setFragment(new Company_device_Fragment());
                    return true;
                case R.id.navigation_company_meterial:
                    // 材料
                    setFragment(new Company_meterial_Fragment());
                    return true;
                case R.id.navigation_my_device:
                    // 我
                    setFragment(new My_Fragment());
                    //在此做判断用户是否已经登录
//                    Intent i = new Intent(BaseActivity.this, LoginActivity.class);
//                    startActivity(i);
                    return true;
            }

            return false;

        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_base);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
//        }

        inittoolbar();
        navigation = (BottomNavigationView) findViewById(R.id.base_navigation);
        BottomNavigationViewHelper.disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if (NetworkAvailableUtils.isNetworkAvailable(this)) {
            System.out.println("网络已连接...");
            setFragment(new Company_news_Fragment());
        } else {
            setFragment(new Company_news_Fragment());
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
                    setFragment(new Company_news_Fragment());
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
//        Login_News();
//        attempConnect2News();

    }

    private void inittoolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.base_toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        ActionBar actionBar = getSupportActionBar();
//        actionBar.setTitle("华曙高科");
    }

    public static void actionStart(Context context) {
        Intent i = new Intent(context, BaseActivity.class);
        context.startActivity(i);

    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                long currentTime = System.currentTimeMillis();
                if (currentTime - firstTime > 2000) {
                    Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                    firstTime = currentTime;
                    return true;

                } else {
                    System.exit(0);
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.app_exit:
                AboutActivity.actionStart(BaseActivity.this);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setFragment(Fragment fragment) {
        String fragmentTag = fragment.getClass().toString();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout_base, fragment, fragmentTag);
        //LogUtil.d("MainActivity", fragment.getClass().toString());// class com.ctcc.zlwcamera.DeviceFragment
        transaction.commit();
        fragmentManager.executePendingTransactions();

        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            if (fragmentTag.equals(Company_news_Fragment.class.toString())) {
                bar.setTitle(R.string.company_about);
            } else if (fragmentTag.equals(Company_device_Fragment.class.toString())) {
                bar.setTitle(R.string.company_device);
            } else if (fragmentTag.equals(Company_meterial_Fragment.class.toString())) {
                bar.setTitle(R.string.company_meterial);
            } else if (fragmentTag.equals(My_Fragment.class.toString())) {
                bar.setTitle("我");
            }

        }
    }



    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == UPDATE_NUMBER) {
                Receive = (String) msg.obj;
                Json_news = JSON.toJSONString(Receive);
                System.out.println("这是Handler打印--------->>>"+Json_news);
            }
//            Bundle data = msg.getData();
//            String result = data.getString("result");
//            if (result != null) {
//                Receive = result;
//            }
//
//            System.out.println(result);

        }
    };

    private void Login_News() {
        mOkHttpClient = new OkHttpClient();
        Request request = new Request.Builder().get().url("http://192.168.4.111/AsyncConnection.ashx?sessionid=ncyha").build();
        Call call = mOkHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                System.out.println("推送消息 -------->"+result);

//                List<News_Large> news_larges = JSON.parseArray(result, News_Large.class);
//                Message s = new Message();
//                s.what = UPDATE_NUMBER;
//                s.obj = result;
//                handler.sendMessage(s);

            }
        });
    }
}
