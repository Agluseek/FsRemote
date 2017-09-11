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

public class FS_Ti6AL4V_Metal_detailsActivity extends AppCompatActivity {
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
        getSupportActionBar().setTitle("钛合金粉末FS Ti6AL4V");

        iv = (ImageView) findViewById(R.id.meterial_iv_fs3250);
        feature_content = (TextView) findViewById(R.id.feature_content);
        apply_content = (TextView) findViewById(R.id.apply_content);
        title_tv = (TextView) findViewById(R.id.title_tv);

        iv.setImageResource(R.mipmap.fs_ti6al4v);
        title_tv.setText("3D打印钛合金髋臼杯");
        feature_content.setText("比强度高，可制造出单位强度高、刚性好、质量轻的零部件；热强度高，可在450℃～500℃的温度下长期工作；抗蚀性好，远优于不锈钢，对点蚀、酸蚀、应力腐蚀的抵抗力特别强；低温性能好，在低温下仍能保持其力学性能。");
        apply_content.setText("是人工关节、骨创伤、脊柱矫形内固定系统、手术器械等医用产品的首选材料；在航空航天领域，钛合金是当代飞机和发动机的主要结构材料之一；在汽车领域，钛及钛合金制造的汽车部件可以达到节油、降低发动机噪声及提高使用寿命的作用。");

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
