package com.agluseek.farsoon.farsoononline.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.agluseek.farsoon.farsoononline.R;

/**
 * Created by Farsoon on 2017/5/4.
 */

public class FS_IN718_Metal_detailsActivity extends AppCompatActivity {
    private TextView feature_content;
    private TextView apply_content;
    private TextView title_tv;
    private ImageView iv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meterial_detail);

        Toolbar bar = (Toolbar) findViewById(R.id.metal_detail_toolbar);
        setSupportActionBar(bar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("镍基高温合金粉末FS IN718");

        iv = (ImageView) findViewById(R.id.meterial_iv_fs3250);
        feature_content = (TextView) findViewById(R.id.feature_content);
        apply_content = (TextView) findViewById(R.id.apply_content);
        title_tv = (TextView) findViewById(R.id.title_tv);

        iv.setImageResource(R.mipmap.fs_in718);
        title_tv.setText("3D打印镍基高温合金叶轮");
        feature_content.setText("在高温和高应力环境下，具有良好的力学性能、抗氧化、耐热腐蚀性能。");
        apply_content.setText("发电机涡轮零件、飞机紧固件、汽车歧管螺栓、柴油发动机排气阀以及各种用于热加工的工模具。" );

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
