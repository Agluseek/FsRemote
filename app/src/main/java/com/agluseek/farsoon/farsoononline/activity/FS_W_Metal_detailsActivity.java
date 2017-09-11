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

public class FS_W_Metal_detailsActivity extends AppCompatActivity {
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
        getSupportActionBar().setTitle("钨粉末FS W");

        iv = (ImageView) findViewById(R.id.meterial_iv_fs3250);
        feature_content = (TextView) findViewById(R.id.feature_content);
        apply_content = (TextView) findViewById(R.id.apply_content);
        title_tv = (TextView) findViewById(R.id.title_tv);

        iv.setImageResource(R.mipmap.fs_w);
        title_tv.setText("3D打印离子散热器固定件");
        feature_content.setText("硬度高，熔点高，常温下不受空气侵蚀，化学性质也比较稳定。");
        apply_content.setText("用途广泛，可以制造枪械、火箭推进器的喷嘴、切削金属的刀片、钻头、超硬模具、拉丝模等。");

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
