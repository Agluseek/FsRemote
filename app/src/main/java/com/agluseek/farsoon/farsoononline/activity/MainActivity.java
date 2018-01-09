package com.agluseek.farsoon.farsoononline.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Toast;

import com.agluseek.farsoon.farsoononline.BottomNavigationViewHelper;
import com.agluseek.farsoon.farsoononline.R;
import com.agluseek.farsoon.farsoononline.Service.MyService;
import com.agluseek.farsoon.farsoononline.fragment.DevicesInfo_Fragment;
import com.agluseek.farsoon.farsoononline.fragment.DevicesItem_Fragment;
import com.agluseek.farsoon.farsoononline.fragment.IndividualSettings_Fragment;
import com.agluseek.farsoon.farsoononline.fragment.SystemSettings_Fragment;


    /**
    * @author Farsoon Wu'Ang
     *
     *         登陆后主界面
    */

public class MainActivity extends AppCompatActivity  {
    private static String Receive;
    private MyService serviceMainActivity;
    private MyService.EchoServiceBinder binder;
    private long FirstTime = 0;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {

                case R.id.navigation_systemSettings:
                    // 服务设置
                    setFragment(new SystemSettings_Fragment());
                    return true;
                case R.id.navigation_individualSettings:
                    //个性设置
                    setFragment(new IndividualSettings_Fragment());
                    return true;
                case R.id.navigation_deviceInfo:
                    //new DevicesInfo_Fragment()
                    // 设备信息
                    setFragment(DevicesItem_Fragment.newInstance(1, Receive));
                    getSupportActionBar().setTitle("设备信息");
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled(true);
        }

        bar.setTitle(R.string.title_deviceInfo);

//        Receive = getIntent().getStringExtra("loginInfo");
//        System.out.println("这是传递过来的参数--------->>>" + Receive);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.main_navigation);
        BottomNavigationViewHelper.disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        setFragment(DevicesItem_Fragment.newInstance(1, Receive));


        // 设备具体信息
//        setFragment(new DevicesInfo_Fragment());
//        getIntent().getStringExtra("loginInfo");

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case android.R.id.home:
                long currentTime = System.currentTimeMillis();
                if (currentTime - FirstTime > 2000) {
                    Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                    FirstTime = currentTime;
                } else {
                    MainActivity.this.finish();
                }
                break;

            case R.id.app_exit:
                AboutActivity.actionStart(MainActivity.this);

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public static void actionStart(Context context, String loginInfo) {
        Intent i = new Intent(context, MainActivity.class);
//        i.putExtra("loginInfo", loginInfo);
        if (loginInfo != null) {
            Receive = loginInfo;
        }
        context.startActivity(i);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                long currentTime = System.currentTimeMillis();
                if (currentTime - FirstTime > 2000) {
                    Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                    FirstTime = currentTime;
                    return true;
                } else {
                    MainActivity.this.finish();
//                    System.exit(0);
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }

    private void setFragment(Fragment fragment) {
        String fragmentTag = fragment.getClass().toString();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.Frame_layout_main, fragment, fragmentTag);
        transaction.commit();
        fragmentManager.executePendingTransactions();

        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            if (fragmentTag.equals(DevicesInfo_Fragment.class.toString())) {
                bar.setTitle(R.string.title_deviceInfo);
            } else if (fragmentTag.equals(IndividualSettings_Fragment.class.toString())) {
                bar.setTitle(R.string.title_individualsettings);
            } else if (fragmentTag.equals(SystemSettings_Fragment.class.toString())) {
                bar.setTitle(R.string.title_systemsettings);
            }
        }
    }
}
